package com.kickoff.service.team.application;

import com.kickoff.service.team.domain.port.input.command.TeamCommandService;
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

  @PostMapping("/init")
  public ResponseEntity<?> initTeam() {
    teamCommandService.initTeamsAndSeasons();

    return ResponseEntity.status(HttpStatus.OK).body(null);
  }

}
