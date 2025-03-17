package com.kickoff.service.fixture.application;

import com.kickoff.service.fixture.domain.port.input.FixtureCommandService;
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
public class FixtureController {

  private final FixtureCommandService fixtureCommandService;

  @PostMapping("/init")
  public ResponseEntity<?> initFixtures() {
    fixtureCommandService.initFixtures();
    return ResponseEntity.status(HttpStatus.OK).body(null);
  }

}
