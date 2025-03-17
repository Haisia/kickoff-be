package com.kickoff.service.team.domain.function;

import com.kickoff.service.common.dto.InitFixturesCommand;
import com.kickoff.service.common.dto.InitTeamsCommand;
import com.kickoff.service.team.domain.port.input.command.TeamCommandService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Consumer;

@Configuration
public class TeamFunction {

  @Bean
  public Consumer<InitTeamsCommand> initTeams(TeamCommandService teamCommandService) {
    return teamCommandService::initTeams;
  }

  @Bean
  public Consumer<InitFixturesCommand> fetchTeamsForInitFixtures(TeamCommandService teamCommandService) {
    return teamCommandService::fetchTeamsForInitFixtures;
  }

}
