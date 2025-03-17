package com.kickoff.service.league.domain.service.usecase.fixture;

import com.kickoff.service.common.dto.InitFixturesCommand;
import com.kickoff.service.league.domain.entity.League;
import com.kickoff.service.league.domain.port.output.repository.LeagueRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Component;

import java.time.Year;

@RequiredArgsConstructor
@Component
public class InitFixturesUseCase {

  private final LeagueRepository leagueRepository;
  private final StreamBridge streamBridge;

  public void fetchSeasons(InitFixturesCommand initFixturesCommand) {
    League league = leagueRepository.findByApiFootballLeagueId(initFixturesCommand.getApiFootballLeagueId()).orElseThrow();
    initFixturesCommand.setLeagueId(league.getId());
    league.getSeasons().forEach(season -> initFixturesCommand.addSeason(season.getId().getYear()));
    streamBridge.send("fetch-teams-for-init-fixtures", initFixturesCommand);
  }

  public void updateSeasons(InitFixturesCommand initFixturesCommand) {
    League league = leagueRepository.findById(initFixturesCommand.getLeagueId()).orElseThrow();
    for (Year year : initFixturesCommand.getYears()) {
      initFixturesCommand.getFixtures(year).forEach(fixtureId -> league.addFixture(year, fixtureId));
    }
  }
}
