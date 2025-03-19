package com.kickoff.service.team.domain.port.output.repository;

import com.kickoff.service.team.domain.entity.Team;

import java.util.List;
import java.util.Optional;

public interface TeamRepository {
  Optional<Team> findByApiFootballTeamId(Long apiFootballTeamId);
  Team save(Team team);
  List<Team> findByApiFootballLeagueId(Long apiFootballLeagueId);
}
