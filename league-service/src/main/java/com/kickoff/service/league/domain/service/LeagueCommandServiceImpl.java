package com.kickoff.service.league.domain.service;

import com.kickoff.service.common.dto.InitFixturesCommand;
import com.kickoff.service.common.dto.InitTeamsCommand;
import com.kickoff.service.league.domain.port.input.command.LeagueCommandService;
import com.kickoff.service.league.domain.service.usecase.fixture.InitFixturesUseCase;
import com.kickoff.service.league.domain.service.usecase.league.InitLeagueUseCase;
import com.kickoff.service.league.domain.service.usecase.team.InitTeamsUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Service
public class LeagueCommandServiceImpl implements LeagueCommandService {

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
  public void initSeasonMapTeams(InitTeamsCommand initTeamsCommand) {
    initTeamsUseCase.initSeasonMapTeams(initTeamsCommand);
  }

  @Override
  public void fetchSeasonsForInitFixture(InitFixturesCommand initFixturesCommand) {
    initFixturesUseCase.fetchSeasons(initFixturesCommand);
  }

  @Override
  public void updateSeasonsForInitFixtures(InitFixturesCommand initFixturesCommand) {
    initFixturesUseCase.updateSeasons(initFixturesCommand);
  }
}
