package com.kickoff.service.team.domain.service;

import com.kickoff.service.common.application.constant.ApplicationConfigConstants;
import com.kickoff.service.common.domain.vo.TeamId;
import com.kickoff.service.common.dto.InitTeamsCommand;
import com.kickoff.service.team.domain.entity.Team;
import com.kickoff.service.team.domain.port.input.command.TeamCommandService;
import com.kickoff.service.team.domain.port.output.externalapi.TeamExternalApiService;
import com.kickoff.service.team.domain.port.output.repository.TeamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Year;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Transactional
@Service
public class TeamCommandServiceImpl implements TeamCommandService {

  private final StreamBridge streamBridge;
  private final TeamExternalApiService teamExternalApiService;
  private final TeamRepository teamRepository;

//  팀에서 리그대상으로할 리그id값들을 리그로보낸다
//  대상으로 할 리그-연도 값을 반환한다
//  해당 리그들의 apiId, season 값으로 외부호출한다
//  받은 팀정보에 해당하는 팀id를 리그모듈에 보낸다.
//    - 없는팀이면 저장하고 팀id를 보낸다.
//  팀id 를 해당시즌에 저장한다

  // fetchSeasons -> apiCall(fetchTeams) -> updateSeason
  @Override
  public void initTeamsAndSeasons() {
    ApplicationConfigConstants.AVAILABLE_LEAGUE_API_FOOTBALL_LEAGUE_IDS.stream()
      .map(apiFootballLeagueId -> InitTeamsCommand.builder().apiFootballLeagueId(apiFootballLeagueId).build())
      .forEach(initTeamCommand -> streamBridge.send("fetch-seasons-for-init-teams", initTeamCommand));
  }

  @Override
  public void initTeams(InitTeamsCommand initTeamsCommand) {
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
        teamIds.add(teamRepository.save(team).getId());
      }
      initTeamsCommand.addAllTeams(year, teamIds);
    }
    streamBridge.send("init-season-map-teams", initTeamsCommand);
    System.out.println();
    // api쏘고
    // 없는거 저장하고
    // command 에 팀id 체우고
    // 반환
  }
}
