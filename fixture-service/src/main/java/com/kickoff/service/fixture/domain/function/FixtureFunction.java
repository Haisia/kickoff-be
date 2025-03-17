package com.kickoff.service.fixture.domain.function;

import com.kickoff.service.common.dto.InitFixturesCommand;
import com.kickoff.service.fixture.domain.port.input.FixtureCommandService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Consumer;

@RequiredArgsConstructor
@Configuration
public class FixtureFunction {

  @Bean
  public Consumer<InitFixturesCommand> persistFixturesForInitFixtures(FixtureCommandService fixtureCommandService) {
    return fixtureCommandService::persistFixturesForInitFixtures;
  }

}
