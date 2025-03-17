package com.kickoff.service.fixture.domain.service;

import com.kickoff.service.common.application.constant.ApplicationConfigConstants;
import com.kickoff.service.common.domain.vo.FixtureId;
import com.kickoff.service.common.domain.vo.TeamId;
import com.kickoff.service.common.dto.InitFixturesCommand;
import com.kickoff.service.fixture.domain.entity.Fixture;
import com.kickoff.service.fixture.domain.port.input.FixtureCommandService;
import com.kickoff.service.fixture.domain.port.output.externalapi.FixtureExternalApiService;
import com.kickoff.service.fixture.domain.port.output.repository.FixtureRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Service;

import java.time.Year;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Service
public class FixtureCommandServiceImpl implements FixtureCommandService {

  private final StreamBridge streamBridge;
  private final FixtureExternalApiService fixtureExternalApiService;
  private final FixtureRepository fixtureRepository;

  /*
   * 1. 공통모듈에서 대상으로 하는 league ids 가져옴
   * 2. 각 리그에 존재하는 years 요청해서 받아옴(mq).
   *      - 동시에 apiFootballFixtureId에 맞는 leagueId 포함
   * 3. league, year 값으로 외부 api 호출하여 경기정보 얻음
   * 4. 해당 경기에 맞는 teamApiId -> teamId 매핑(mq)
   * 5. 해당 경기가 db에 존재하지 않으면 db에 persist
   * */
  @Override
  public void initFixtures() {
    ApplicationConfigConstants.AVAILABLE_LEAGUE_API_FOOTBALL_LEAGUE_IDS.stream()
      .map(apiFootballLeagueId -> InitFixturesCommand.builder().apiFootballLeagueId((long)apiFootballLeagueId).build())
      .forEach(initTeamCommand -> streamBridge.send("fetch-seasons-for-init-fixtures", initTeamCommand));
  }

  @Override
  public void persistFixturesForInitFixtures(InitFixturesCommand initFixturesCommand) {
    List<Year> years = initFixturesCommand.getYears();
    Map<Long, TeamId> teamIdMap = new HashMap<>();
    initFixturesCommand.getTeamIdMap().forEach(tim -> teamIdMap.put(tim.getApiFootballFixtureId(), tim.getTeamId()));

    for (Year year : years) {
      List<Fixture> fixtures = fixtureExternalApiService.getFixtures(initFixturesCommand.getLeagueId(), initFixturesCommand.getApiFootballLeagueId(), year, teamIdMap);
      List<FixtureId> fixtureIds = new ArrayList<>();
      for (Fixture fixture : fixtures) {
        fixtureRepository.findByApiFootballFixtureId(fixture.getApiFootballFixtureId()).ifPresentOrElse(
          foundFixture -> fixtureIds.add(fixture.getId())
          , () -> {
            Fixture savedFixture = fixtureRepository.save(fixture);
            fixtureIds.add(savedFixture.getId());
          });
      }
      initFixturesCommand.addFixturesToSeason(year, fixtureIds);
    }

    streamBridge.send("update-seasons-for-init-fixtures", initFixturesCommand);
  }
}
