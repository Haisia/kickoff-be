package com.kickoff.service.fixture.domain.service.usecase.fixture.statistics;

import com.kickoff.service.common.application.constant.ApplicationConfigConstants;
import com.kickoff.service.fixture.domain.entity.Fixture;
import com.kickoff.service.fixture.domain.port.output.externalapi.FixtureExternalApiService;
import com.kickoff.service.fixture.domain.port.output.repository.FixtureRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.Year;
import java.util.List;

@RequiredArgsConstructor
@Component
public class InitFixtureStatisticsUseCase {

  private final FixtureExternalApiService fixtureExternalApiService;
  private final FixtureRepository fixtureRepository;

  public void initFixtureStatistics() {
    List<Fixture> targets = fixtureRepository.findByApiFootballLeagueIdIn(ApplicationConfigConstants.AVAILABLE_LEAGUE_API_FOOTBALL_LEAGUE_IDS)
      .stream()
      .filter(fixture -> !fixture.getSeasonYear().isBefore(Year.of(2024)) && fixture.isNotStarted())
      .toList();

    for (Fixture fixture : targets) {
      fixtureExternalApiService.getFixtureStatistics(fixture.getApiFootballFixtureId())
        .forEach(fixture::addFixtureStatistic);
    }
  }
}
