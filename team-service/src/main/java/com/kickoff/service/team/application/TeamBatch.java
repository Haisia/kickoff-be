package com.kickoff.service.team.application;

import com.kickoff.service.common.application.constant.ApplicationConfigConstants;
import com.kickoff.service.team.domain.port.input.command.TeamCommandService;
import com.kickoff.service.team.domain.port.output.repository.TeamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Component
public class TeamBatch {

  private final TeamRepository teamRepository;
  private final TeamCommandService teamCommandService;

  @Scheduled(cron = "* * 0 * * *")
  public void updateTeamSquads() {
    for (Long availableLeagueApiFootballLeagueId : ApplicationConfigConstants.AVAILABLE_LEAGUE_API_FOOTBALL_LEAGUE_IDS) {
      teamRepository.findByApiFootballLeagueId(availableLeagueApiFootballLeagueId)
        .forEach(team -> teamCommandService.updateTeamSquads(team.getApiFootballTeamId()));
    }
  }
}
