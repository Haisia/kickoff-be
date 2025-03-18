package com.kickoff.service.fixture.application;

import com.kickoff.service.fixture.domain.port.input.FixtureCommandService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class FixtureBatch {

  private final FixtureCommandService fixtureCommandService;

  @Scheduled(cron = "0 * * * * *")
  public void updateLiveFixtures() {
    fixtureCommandService.updateLiveFixtures();
  }
}