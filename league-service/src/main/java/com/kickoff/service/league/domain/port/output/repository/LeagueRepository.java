package com.kickoff.service.league.domain.port.output.repository;

import com.kickoff.service.league.domain.entity.League;

import java.util.Optional;

public interface LeagueRepository {
  League save(League league);
  Optional<League> findByApiFootballLeagueId(Long apiFootballLeagueId);
}
