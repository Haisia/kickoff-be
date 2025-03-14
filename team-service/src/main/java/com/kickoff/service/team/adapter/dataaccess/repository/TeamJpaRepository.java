package com.kickoff.service.team.adapter.dataaccess.repository;

import com.kickoff.service.common.domain.vo.TeamId;
import com.kickoff.service.team.domain.entity.Team;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TeamJpaRepository extends JpaRepository<Team, TeamId> {
  Optional<Team> findByApiFootballTeamId(Long apiFootballTeamId);
}
