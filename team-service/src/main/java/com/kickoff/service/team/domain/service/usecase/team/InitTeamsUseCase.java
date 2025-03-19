package com.kickoff.service.team.domain.service.usecase.team;

import com.kickoff.service.common.application.constant.ApplicationConfigConstants;
import com.kickoff.service.common.domain.vo.TeamId;
import com.kickoff.service.common.dto.InitTeamsCommand;
import com.kickoff.service.team.domain.entity.Team;
import com.kickoff.service.team.domain.port.output.externalapi.TeamExternalApiService;
import com.kickoff.service.team.domain.port.output.repository.TeamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Component;

import java.time.Year;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Component
public class InitTeamsUseCase {

  private final StreamBridge streamBridge;
  private final TeamExternalApiService teamExternalApiService;
  private final TeamRepository teamRepository;

  public void initTeams() {
    ApplicationConfigConstants.AVAILABLE_LEAGUE_API_FOOTBALL_LEAGUE_IDS.stream()
      .map(apiFootballLeagueId -> InitTeamsCommand.builder().apiFootballLeagueId(apiFootballLeagueId).build())
      .forEach(initTeamCommand -> streamBridge.send("fetch-seasons-for-init-teams", initTeamCommand));
  }

  public void persistTeams(InitTeamsCommand initTeamsCommand) {
    long apiFootballLeagueId = initTeamsCommand.getApiFootballLeagueId();
    for (Year year : initTeamsCommand.getYears()) {
      List<Team> teams = teamExternalApiService.getTeams(apiFootballLeagueId, year);
      List<TeamId> teamIds = new ArrayList<>();
      for (Team team : teams) {
        Optional<Team> optionalTeam = teamRepository.findByApiFootballTeamId(team.getApiFootballTeamId());
        if (optionalTeam.isPresent()) {
          teamIds.add(optionalTeam.get().getId());
          continue;
        }
        team.setApiFootballLeagueId(initTeamsCommand.getApiFootballLeagueId());
        teamIds.add(teamRepository.save(team).getId());
      }
      initTeamsCommand.addAllTeams(year, teamIds);
    }
    streamBridge.send("persist-season-map-teams-for-init-teams", initTeamsCommand);
  }

}
