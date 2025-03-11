package com.kickoff.service.league.domain.service;

import com.kickoff.service.league.domain.entity.League;
import com.kickoff.service.league.domain.entity.Season;
import com.kickoff.service.league.domain.port.input.command.LeagueCommandService;
import com.kickoff.service.league.domain.port.output.externalapi.LeagueExternalApiService;
import com.kickoff.service.league.domain.port.output.repository.LeagueRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Transactional
@Service
public class LeagueCommandServiceImpl implements LeagueCommandService {

  private final LeagueExternalApiService leagueExternalApiService;
  private final LeagueRepository leagueRepository;

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
}
