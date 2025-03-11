package com.kickoff.service.league.adapter.dataaccess.adapter;

import com.kickoff.service.league.adapter.dataaccess.repository.LeagueJpaRepository;
import com.kickoff.service.league.domain.entity.League;
import com.kickoff.service.league.domain.port.output.repository.LeagueRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class LeagueRepositoryImpl implements LeagueRepository {

  private final LeagueJpaRepository leagueJpaRepository;

  @Override
  public League save(League league) {
    return leagueJpaRepository.save(league);
  }

  @Override
  public Optional<League> findByApiFootballLeagueId(Long apiFootballLeagueId) {
    return leagueJpaRepository.findByApiFootballLeagueId(apiFootballLeagueId);
  }
}
