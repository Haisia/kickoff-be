package com.kickoff.service.league.domain.service.usecase.team;

import com.kickoff.service.common.dto.InitTeamsCommand;
import com.kickoff.service.league.domain.entity.League;
import com.kickoff.service.league.domain.port.output.repository.LeagueRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Component;

import java.time.Year;

@Component
@RequiredArgsConstructor
public class InitTeamsUseCase {

  private final LeagueRepository leagueRepository;
  private final StreamBridge streamBridge;

  public void fetchSeasons(InitTeamsCommand initTeamsCommand) {
    League league = leagueRepository.findByApiFootballLeagueId(initTeamsCommand.getApiFootballLeagueId())
      .orElseThrow();
    initTeamsCommand.setLeagueId(league.getId());
    league.getSeasons().forEach(season -> initTeamsCommand.addSeason(season.getId().getYear()));
    streamBridge.send("persist-teams-for-init-teams", initTeamsCommand);
  }

  public void persistSeasonMapTeamsForInitTeams(InitTeamsCommand initTeamsCommand) {
    League league = leagueRepository.findByApiFootballLeagueId(initTeamsCommand.getApiFootballLeagueId()).orElseThrow();
    for (Year year : initTeamsCommand.getYears()) {
      initTeamsCommand.getTeams(year).forEach(teamId -> league.addTeam(year, teamId));
    }
  }
}
