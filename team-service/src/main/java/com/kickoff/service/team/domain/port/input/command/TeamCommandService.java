package com.kickoff.service.team.domain.port.input.command;

import com.kickoff.service.common.dto.InitFixturesCommand;
import com.kickoff.service.common.dto.InitTeamsCommand;

public interface TeamCommandService {
  void initTeams();
  void persistTeamsForInitTeams(InitTeamsCommand initTeamsCommand);
  void fetchTeamsForInitFixtures(InitFixturesCommand initFixturesCommand);
  void updateTeamSquads(Long apiFootballTeamId);
}
