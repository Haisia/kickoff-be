package com.kickoff.service.league.application;

import com.kickoff.service.league.domain.port.input.command.LeagueCommandService;
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
public class LeagueController {

  private final LeagueCommandService leagueCommandService;
  private final LeagueBatch leagueBatch;

  @PostMapping("/init")
  public ResponseEntity<?> initLeague() {
    leagueCommandService.initLeague();

    return ResponseEntity.status(HttpStatus.OK).body(null);
  }

  @PostMapping("/standings/init")
  public ResponseEntity<?> initStandings() {
    leagueBatch.updateLeagueStandings();
    return ResponseEntity.status(HttpStatus.OK).body(null);
  }
}
