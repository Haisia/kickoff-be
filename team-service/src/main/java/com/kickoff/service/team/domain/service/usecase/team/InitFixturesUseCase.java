package com.kickoff.service.team.domain.service.usecase.team;

import com.kickoff.service.common.dto.InitFixturesCommand;
import com.kickoff.service.team.domain.port.output.repository.TeamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class InitFixturesUseCase {

  private final StreamBridge streamBridge;
  private final TeamRepository teamRepository;

  public void fetchTeams(InitFixturesCommand initFixturesCommand) {
    teamRepository.findByLeagueId(initFixturesCommand.getLeagueId()).forEach(team -> {
      initFixturesCommand.addTeamId(team.getId(), team.getApiFootballTeamId());
    });
    streamBridge.send("persist-fixtures-for-init-fixtures", initFixturesCommand);
  }
}
