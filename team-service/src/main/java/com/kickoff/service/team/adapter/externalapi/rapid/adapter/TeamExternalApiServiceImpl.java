package com.kickoff.service.team.adapter.externalapi.rapid.adapter;

import com.kickoff.service.team.adapter.externalapi.rapid.client.TeamExternalApiClient;
import com.kickoff.service.team.adapter.externalapi.rapid.dto.teams.TeamsResponse;
import com.kickoff.service.team.domain.entity.Team;
import com.kickoff.service.team.domain.port.output.externalapi.TeamExternalApiService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Year;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class TeamExternalApiServiceImpl implements TeamExternalApiService {

  private final TeamExternalApiClient teamExternalApiClient;

  @Override
  public List<Team> getTeams(Long apiFootballLeagueId, Year year) {
    return teamExternalApiClient.requestTeams(apiFootballLeagueId, year)
      .stream()
      .map(TeamsResponse::toTeam)
      .collect(Collectors.toList());
  }
}
