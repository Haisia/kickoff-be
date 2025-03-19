package com.kickoff.service.team.domain.service;

import com.kickoff.service.common.dto.InitFixturesCommand;
import com.kickoff.service.common.dto.InitTeamsCommand;
import com.kickoff.service.team.domain.port.input.command.TeamCommandService;
import com.kickoff.service.team.domain.service.usecase.team.InitTeamsUseCase;
import com.kickoff.service.team.domain.service.usecase.fixture.InitFixturesUseCase;
import com.kickoff.service.team.domain.service.usecase.team.player.UpdateTeamSquadsUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Service
public class TeamCommandServiceImpl implements TeamCommandService {

  private final InitTeamsUseCase initTeamsUseCase;
  private final InitFixturesUseCase initFixturesUseCase;
  private final UpdateTeamSquadsUseCase updateTeamSquadsUseCase;

  @Override
  public void initTeams() {
    initTeamsUseCase.initTeams();
  }

  @Override
  public void persistTeamsForInitTeams(InitTeamsCommand initTeamsCommand) {
    initTeamsUseCase.persistTeams(initTeamsCommand);
  }

  @Override
  public void fetchTeamsForInitFixtures(InitFixturesCommand initFixturesCommand) {
    initFixturesUseCase.fetchTeams(initFixturesCommand);
  }

  @Override
  public void updateTeamSquads(Long apiFootballTeamId) {
    updateTeamSquadsUseCase.updateOrPersistSquads(apiFootballTeamId);
  }
}
