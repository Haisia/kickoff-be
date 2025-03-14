package com.kickoff.service.team.domain.port.input.command;

import com.kickoff.service.common.dto.InitTeamsCommand;

public interface TeamCommandService {
  void initTeamsAndSeasons();
  void initTeams(InitTeamsCommand initTeamsCommand);
}
