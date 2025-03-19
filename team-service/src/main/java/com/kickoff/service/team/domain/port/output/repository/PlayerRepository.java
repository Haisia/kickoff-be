package com.kickoff.service.team.domain.port.output.repository;

import com.kickoff.service.team.domain.entity.Player;

import java.util.Optional;

public interface PlayerRepository {
  Optional<Player> findByApiFootballPlayerId(Long apiFootballPlayerId);
}
