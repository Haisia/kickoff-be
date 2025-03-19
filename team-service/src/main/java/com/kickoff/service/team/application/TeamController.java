package com.kickoff.service.team.application;

import com.kickoff.service.common.application.constant.ApplicationConfigConstants;
import com.kickoff.service.team.domain.port.input.command.TeamCommandService;
import com.kickoff.service.team.domain.port.output.repository.TeamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@Validated
@RequestMapping(path = "/api", produces = {MediaType.APPLICATION_JSON_VALUE})
@RestController
public class TeamController {

  private final TeamCommandService teamCommandService;
  private final TeamRepository teamRepository;

  @PostMapping("/init")
  public ResponseEntity<?> initTeam() {
    teamCommandService.initTeams();

    return ResponseEntity.status(HttpStatus.OK).body(null);
  }

  @PostMapping("/squads/init")
  public ResponseEntity<?> initSquads() {
    for (Long availableLeagueApiFootballLeagueId : ApplicationConfigConstants.AVAILABLE_LEAGUE_API_FOOTBALL_LEAGUE_IDS) {
      teamRepository.findByApiFootballLeagueId(availableLeagueApiFootballLeagueId)
        .forEach(team -> teamCommandService.updateTeamSquads(team.getApiFootballTeamId()));
    }
    return ResponseEntity.status(HttpStatus.OK).body(null);
  }
}
