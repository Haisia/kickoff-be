package com.kickoff.service.fixture.domain.service.usecase.fixture;

import com.kickoff.service.fixture.domain.entity.Fixture;
import com.kickoff.service.fixture.domain.port.output.externalapi.FixtureExternalApiService;
import com.kickoff.service.fixture.domain.port.output.repository.FixtureRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Component
public class UpdateLiveFixturesUseCase {

  private final FixtureRepository fixtureRepository;
  private final FixtureExternalApiService fixtureExternalApiService;

  public void updateLiveFixtures() {
    log.info("=== 라이브 경기를 업데이트합니다. ===");
    List<Fixture> liveFixture = fixtureRepository.findLiveFixture();
    log.info("liveFixture: {}", liveFixture.size());
    for (Fixture fixture : liveFixture) {
      log.info("Fixture: {}", fixture.getId());
      fixtureExternalApiService.getFixtureStatistics(fixture.getApiFootballFixtureId())
        .forEach(fixture::updateOrAddFixtureStatistic);
    }
    log.info("=== 업데이트 종료 ===");
  }

}
