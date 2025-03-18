package com.kickoff.service.fixture.domain.port.output.externalapi;

import com.kickoff.service.common.domain.vo.TeamId;
import com.kickoff.service.fixture.domain.entity.Fixture;
import com.kickoff.service.fixture.domain.entity.FixtureStatistic;

import java.time.Year;
import java.util.List;
import java.util.Map;

public interface FixtureExternalApiService {
  List<Fixture> getFixtures(Long apiFootballLeagueId, Year year, Map<Long, TeamId> teamIdMap);
  List<FixtureStatistic> getFixtureStatistics(Long apiFootballFixtureId);
}
