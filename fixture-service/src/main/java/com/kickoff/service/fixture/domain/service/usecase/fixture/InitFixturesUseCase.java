package com.kickoff.service.fixture.domain.service.usecase.fixture;

import com.kickoff.service.common.application.constant.ApplicationConfigConstants;
import com.kickoff.service.common.domain.vo.TeamId;
import com.kickoff.service.common.dto.InitFixturesCommand;
import com.kickoff.service.fixture.domain.entity.Fixture;
import com.kickoff.service.fixture.domain.port.output.externalapi.FixtureExternalApiService;
import com.kickoff.service.fixture.domain.port.output.repository.FixtureRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Component;

import java.time.Year;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Component
public class InitFixturesUseCase {

  private final StreamBridge streamBridge;
  private final FixtureExternalApiService fixtureExternalApiService;
  private final FixtureRepository fixtureRepository;

  public void initFixtures() {
    ApplicationConfigConstants.AVAILABLE_LEAGUE_API_FOOTBALL_LEAGUE_IDS.stream()
      .map(apiFootballLeagueId -> InitFixturesCommand.builder().apiFootballLeagueId((long)apiFootballLeagueId).build())
      .forEach(initTeamCommand -> streamBridge.send("fetch-seasons-for-init-fixtures", initTeamCommand));
  }

  public void persistFixtures(InitFixturesCommand initFixturesCommand) {
    List<Year> years = initFixturesCommand.getYears();
    Map<Long, TeamId> teamIdMap = new HashMap<>();
    initFixturesCommand.getTeamIdMap().forEach(tim -> teamIdMap.put(tim.getApiFootballFixtureId(), tim.getTeamId()));

    for (Year year : years) {
      List<Fixture> fixtures = fixtureExternalApiService.getFixtures(initFixturesCommand.getApiFootballLeagueId(), year, teamIdMap);
      List<Long> apiFootballFixtureIds = new ArrayList<>();
      for (Fixture fixture : fixtures) {
        fixtureRepository.findByApiFootballFixtureId(fixture.getApiFootballFixtureId()).ifPresentOrElse(
          foundFixture -> apiFootballFixtureIds.add(fixture.getApiFootballFixtureId())
          , () -> {
            Fixture savedFixture = fixtureRepository.save(fixture);
            apiFootballFixtureIds.add(savedFixture.getApiFootballFixtureId());
          });
      }
      initFixturesCommand.addFixturesToSeason(year, apiFootballFixtureIds);
    }

    streamBridge.send("update-seasons-for-init-fixtures", initFixturesCommand);
  }

}
