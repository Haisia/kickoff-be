package com.kickoff.service.fixture.domain.port.input;

import com.kickoff.service.common.dto.InitFixturesCommand;

public interface FixtureCommandService {
  void initFixtures();
  void persistFixturesForInitFixtures(InitFixturesCommand initFixturesCommand);

  void initFixtureStatistics();
  void updateLiveFixtures();
}
