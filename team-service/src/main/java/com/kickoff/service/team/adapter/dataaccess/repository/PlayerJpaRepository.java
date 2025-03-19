package com.kickoff.service.team.adapter.dataaccess.repository;

import com.kickoff.service.team.domain.entity.Player;
import com.kickoff.service.team.domain.vo.PlayerId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PlayerJpaRepository extends JpaRepository<Player, PlayerId> {
  Optional<Player> findByApiFootballPlayerId(Long apiFootballPlayerId);
}
