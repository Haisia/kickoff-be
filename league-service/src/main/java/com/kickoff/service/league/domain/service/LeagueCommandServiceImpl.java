package com.kickoff.service.league.domain.service;

import com.kickoff.service.common.dto.InitFixturesCommand;
import com.kickoff.service.common.dto.InitTeamsCommand;
import com.kickoff.service.league.domain.entity.League;
import com.kickoff.service.league.domain.entity.Season;
import com.kickoff.service.league.domain.port.input.command.LeagueCommandService;
import com.kickoff.service.league.domain.port.output.externalapi.LeagueExternalApiService;
import com.kickoff.service.league.domain.port.output.repository.LeagueRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Year;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Transactional
@Service
public class LeagueCommandServiceImpl implements LeagueCommandService {

  private final LeagueExternalApiService leagueExternalApiService;
  private final LeagueRepository leagueRepository;
  private final StreamBridge streamBridge;

  @Override
  public void initLeague() {
    List<League> allLeagues = leagueExternalApiService.getAllLeagues();

    for (League league : allLeagues) {
      Optional<League> OptionalFoundLeague = leagueRepository.findByApiFootballLeagueId(league.getApiFootballLeagueId());
      if (OptionalFoundLeague.isPresent()) {
        League foundLeague = OptionalFoundLeague.get();
        for (Season season : league.getSeasons()) {
          boolean alreadyExist = foundLeague.getSeasons()
            .stream()
            .anyMatch(foundSeason -> foundSeason.getId().getYear().equals(season.getId().getYear()));
          if (!alreadyExist) {
            foundLeague.addSeason(season.getId().getYear(), season.getStartDate(), season.getEndDate());
          }
        }
      } else {
        boolean hasDuplicateSeason = league.getSeasons().stream()
          .map(Season::getId)
          .collect(Collectors.toSet())
          .size() < league.getSeasons().size();

        if (hasDuplicateSeason) continue;
        leagueRepository.save(league);
      }
    }
  }

  @Override
  public void fetchSeasonsForInitTeams(InitTeamsCommand initTeamsCommand) {
    League league = leagueRepository.findByApiFootballLeagueId((long) initTeamsCommand.getApiFootballLeagueId())
      .orElseThrow();
    initTeamsCommand.setLeagueId(league.getId());
    league.getSeasons().forEach(season -> initTeamsCommand.addSeason(season.getId().getYear()));
    streamBridge.send("init-teams", initTeamsCommand);
  }

  @Override
  public void initSeasonMapTeams(InitTeamsCommand initTeamsCommand) {
    League league = leagueRepository.findByApiFootballLeagueId((long) initTeamsCommand.getApiFootballLeagueId()).orElseThrow();
    for (Year year : initTeamsCommand.getYears()) {
      initTeamsCommand.getTeams(year).forEach(teamId -> league.addTeam(year, teamId));
    }
  }

  @Override
  public void fetchSeasonsForInitFixture(InitFixturesCommand initFixturesCommand) {
    League league = leagueRepository.findByApiFootballLeagueId(initFixturesCommand.getApiFootballLeagueId()).orElseThrow();
    initFixturesCommand.setLeagueId(league.getId());
    league.getSeasons().forEach(season -> initFixturesCommand.addSeason(season.getId().getYear()));
    streamBridge.send("fetch-teams-for-init-fixtures", initFixturesCommand);
  }

  @Override
  public void updateSeasonsForInitFixtures(InitFixturesCommand initFixturesCommand) {
    League league = leagueRepository.findById(initFixturesCommand.getLeagueId()).orElseThrow();
    for (Year year : initFixturesCommand.getYears()) {
      initFixturesCommand.getFixtures(year).forEach(fixtureId -> league.addFixture(year, fixtureId));
    }
  }
}
