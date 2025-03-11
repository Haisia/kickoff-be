package com.kickoff.service.league.adapter.dataaccess.repository;

import com.kickoff.service.common.domain.vo.LeagueId;
import com.kickoff.service.league.domain.entity.League;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LeagueJpaRepository extends JpaRepository<League, LeagueId> {
  Optional<League> findByApiFootballLeagueId(Long apiFootballLeagueId);
}
