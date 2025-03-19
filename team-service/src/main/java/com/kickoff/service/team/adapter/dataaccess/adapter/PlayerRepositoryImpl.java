package com.kickoff.service.team.adapter.dataaccess.adapter;

import com.kickoff.service.team.adapter.dataaccess.repository.PlayerJpaRepository;
import com.kickoff.service.team.domain.entity.Player;
import com.kickoff.service.team.domain.port.output.repository.PlayerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class PlayerRepositoryImpl implements PlayerRepository {

  private final PlayerJpaRepository playerJpaRepository;

  @Override
  public Optional<Player> findByApiFootballPlayerId(Long apiFootballPlayerId) {
    return playerJpaRepository.findByApiFootballPlayerId(apiFootballPlayerId);
  }
}
