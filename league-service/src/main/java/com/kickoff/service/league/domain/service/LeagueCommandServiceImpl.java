package com.kickoff.service.league.domain.service;

import com.kickoff.service.common.dto.InitFixturesCommand;
import com.kickoff.service.common.dto.InitTeamsCommand;
import com.kickoff.service.league.domain.entity.League;
import com.kickoff.service.league.domain.port.input.command.LeagueCommandService;
import com.kickoff.service.league.domain.port.output.externalapi.LeagueExternalApiService;
import com.kickoff.service.league.domain.port.output.repository.LeagueRepository;
import com.kickoff.service.league.domain.service.usecase.fixture.InitFixturesUseCase;
import com.kickoff.service.league.domain.service.usecase.league.InitLeagueUseCase;
import com.kickoff.service.league.domain.service.usecase.team.InitTeamsUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Year;

@RequiredArgsConstructor
@Transactional
@Service
public class LeagueCommandServiceImpl implements LeagueCommandService {

  private final LeagueExternalApiService leagueExternalApiService;

  private final InitLeagueUseCase initLeagueUseCase;
  private final InitTeamsUseCase initTeamsUseCase;
  private final InitFixturesUseCase initFixturesUseCase;

  @Override
  public void initLeague() {
    initLeagueUseCase.initLeague();
  }

  @Override
  public void fetchSeasonsForInitTeams(InitTeamsCommand initTeamsCommand) {
    initTeamsUseCase.fetchSeasons(initTeamsCommand);
  }

  @Override
  public void persistSeasonMapTeamsForInitTeams(InitTeamsCommand initTeamsCommand) {
    initTeamsUseCase.persistSeasonMapTeamsForInitTeams(initTeamsCommand);
  }

  @Override
  public void fetchSeasonsForInitFixture(InitFixturesCommand initFixturesCommand) {
    initFixturesUseCase.fetchSeasons(initFixturesCommand);
  }

  @Override
  public void updateSeasonsForInitFixtures(InitFixturesCommand initFixturesCommand) {
    initFixturesUseCase.updateSeasons(initFixturesCommand);
  }

  @Override
  public void updateOrPersistLeagueStandings(League league, Year year) {
    leagueExternalApiService.getLeagueStandings(league.getApiFootballLeagueId(), year)
      .forEach(leagueStanding -> league.updateOrAddStanding(year, leagueStanding));
  }
}
