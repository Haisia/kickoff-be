package com.kickoff.service.fixture.domain.port.output.externalapi;

import com.kickoff.service.common.domain.vo.LeagueId;
import com.kickoff.service.common.domain.vo.TeamId;
import com.kickoff.service.fixture.domain.entity.Fixture;

import java.time.Year;
import java.util.List;
import java.util.Map;

public interface FixtureExternalApiService {
  List<Fixture> getFixtures(LeagueId leagueId, Long apiFootballLeagueId, Year year, Map<Long, TeamId> teamIdMap);
}
