package com.kickoff.service.league.application;

import com.kickoff.service.common.application.constant.ApplicationConfigConstants;
import com.kickoff.service.league.domain.entity.League;
import com.kickoff.service.league.domain.entity.Season;
import com.kickoff.service.league.domain.port.input.command.LeagueCommandService;
import com.kickoff.service.league.domain.port.output.repository.LeagueRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Transactional
@Component
public class LeagueBatch {

  private final LeagueCommandService leagueCommandService;
  private final LeagueRepository leagueRepository;

  @Scheduled(cron = "* * 0 * * *")
  public void updateLeagueStandings() {
    ApplicationConfigConstants.AVAILABLE_LEAGUE_API_FOOTBALL_LEAGUE_IDS.forEach(apiFootballLeagueId -> {
      League league = leagueRepository.findByApiFootballLeagueId(apiFootballLeagueId).orElseThrow();
      Season season = league.getCurrentSeason().orElseThrow();
      log.info("{} 리그 {} 시즌의 순위정보 업데이트를 시작합니다.", league.getName(), season.getId().getYear());
      leagueCommandService.updateOrPersistLeagueStandings(league, season.getId().getYear());
    });
  }
}
