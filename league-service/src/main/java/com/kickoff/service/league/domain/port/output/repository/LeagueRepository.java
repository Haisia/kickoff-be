package com.kickoff.service.league.domain.port.output.repository;

import com.kickoff.service.common.domain.vo.LeagueId;
import com.kickoff.service.league.domain.entity.League;

import java.util.Optional;

public interface LeagueRepository {
  Optional<League> findById(LeagueId id);
  League save(League league);
  Optional<League> findByApiFootballLeagueId(Long apiFootballLeagueId);
}
