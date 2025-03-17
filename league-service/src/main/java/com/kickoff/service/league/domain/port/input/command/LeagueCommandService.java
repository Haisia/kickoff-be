package com.kickoff.service.league.domain.port.input.command;

import com.kickoff.service.common.dto.InitFixturesCommand;
import com.kickoff.service.common.dto.InitTeamsCommand;

public interface LeagueCommandService {
  void initLeague();
  void fetchSeasonsForInitTeams(InitTeamsCommand initTeamsCommand);
  void persistSeasonMapTeamsForInitTeams(InitTeamsCommand initTeamsCommand);
  void fetchSeasonsForInitFixture(InitFixturesCommand initFixturesCommand);
  void updateSeasonsForInitFixtures(InitFixturesCommand initFixturesCommand);
}
