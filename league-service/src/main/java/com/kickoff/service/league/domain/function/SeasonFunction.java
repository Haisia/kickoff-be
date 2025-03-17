package com.kickoff.service.league.domain.function;

import com.kickoff.service.common.dto.InitFixturesCommand;
import com.kickoff.service.common.dto.InitTeamsCommand;
import com.kickoff.service.league.domain.port.input.command.LeagueCommandService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Consumer;

@Configuration
public class SeasonFunction {

  @Bean
  public Consumer<InitTeamsCommand> fetchSeasonsForInitTeams(LeagueCommandService leagueCommandService) {
    return leagueCommandService::fetchSeasonsForInitTeams;
  }

  @Bean
  public Consumer<InitTeamsCommand> initSeasonMapTeams(LeagueCommandService leagueCommandService) {
    return leagueCommandService::initSeasonMapTeams;
  }

  @Bean
  public Consumer<InitFixturesCommand> fetchSeasonsForInitFixtures(LeagueCommandService leagueCommandService) {
    return leagueCommandService::fetchSeasonsForInitFixture;
  }

  @Bean
  public Consumer<InitFixturesCommand> updateSeasonsForInitFixtures(LeagueCommandService leagueCommandService) {
    return leagueCommandService::updateSeasonsForInitFixtures;
  }
}
