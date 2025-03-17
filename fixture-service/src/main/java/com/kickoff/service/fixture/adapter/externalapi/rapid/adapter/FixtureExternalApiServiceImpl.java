package com.kickoff.service.fixture.adapter.externalapi.rapid.adapter;

import com.kickoff.service.common.domain.vo.LeagueId;
import com.kickoff.service.common.domain.vo.TeamId;
import com.kickoff.service.fixture.adapter.externalapi.rapid.client.FixtureExternalApiClient;
import com.kickoff.service.fixture.domain.entity.Fixture;
import com.kickoff.service.fixture.domain.port.output.externalapi.FixtureExternalApiService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Year;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class FixtureExternalApiServiceImpl implements FixtureExternalApiService {

  private final FixtureExternalApiClient fixtureExternalApiClient;

  @Override
  public List<Fixture> getFixtures(LeagueId leagueId, Long apiFootballLeagueId, Year year, Map<Long, TeamId> teamIdMap) {
    return fixtureExternalApiClient.requestFixtures(apiFootballLeagueId, year)
      .stream()
      .map(fixturesResponse -> {
        Fixture fixture = fixturesResponse.toFixture(teamIdMap);
        fixture.setSeasonYear(year);
        fixture.setLeagueId(leagueId);
        return fixture;
      })
      .collect(Collectors.toList());
  }
}
