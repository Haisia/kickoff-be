package com.kickoff.service.team.adapter.dataaccess.adapter;

import com.kickoff.service.team.adapter.dataaccess.repository.TeamJpaRepository;
import com.kickoff.service.team.domain.entity.Team;
import com.kickoff.service.team.domain.port.output.repository.TeamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class TeamRepositoryImpl implements TeamRepository {

  private final TeamJpaRepository teamJpaRepository;

  @Override
  public Optional<Team> findByApiFootballTeamId(Long apiFootballTeamId) {
    return teamJpaRepository.findByApiFootballTeamId(apiFootballTeamId);
  }

  @Override
  public Team save(Team team) {
    return teamJpaRepository.save(team);
  }

  @Override
  public List<Team> findByApiFootballLeagueId(Long apiFootballLeagueId) {
    return teamJpaRepository.findByApiFootballLeagueId(apiFootballLeagueId);
  }
}
