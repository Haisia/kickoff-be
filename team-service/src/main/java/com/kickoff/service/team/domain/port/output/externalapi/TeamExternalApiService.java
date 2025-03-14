package com.kickoff.service.team.domain.port.output.externalapi;

import com.kickoff.service.team.domain.entity.Team;

import java.time.Year;
import java.util.List;

public interface TeamExternalApiService {
  List<Team> getTeams(Long apiFootballLeagueId, Year year);
}
