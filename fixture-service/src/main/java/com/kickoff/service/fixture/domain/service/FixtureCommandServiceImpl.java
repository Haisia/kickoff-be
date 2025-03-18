package com.kickoff.service.fixture.domain.service;

import com.kickoff.service.common.dto.InitFixturesCommand;
import com.kickoff.service.fixture.domain.port.input.FixtureCommandService;
import com.kickoff.service.fixture.domain.service.usecase.fixture.InitFixturesUseCase;
import com.kickoff.service.fixture.domain.service.usecase.fixture.statistics.InitFixtureStatisticsUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@RequiredArgsConstructor
@Service
public class FixtureCommandServiceImpl implements FixtureCommandService {

  private final InitFixturesUseCase initFixturesUseCase;
  private final InitFixtureStatisticsUseCase initFixtureStatisticsUseCase;

  @Override
  public void initFixtures() {
    initFixturesUseCase.initFixtures();
  }

  @Override
  public void persistFixturesForInitFixtures(InitFixturesCommand initFixturesCommand) {
    initFixturesUseCase.persistFixtures(initFixturesCommand);
  }

  @Override
  public void initFixtureStatistics() {
    initFixtureStatisticsUseCase.initFixtureStatistics();
  }
}
