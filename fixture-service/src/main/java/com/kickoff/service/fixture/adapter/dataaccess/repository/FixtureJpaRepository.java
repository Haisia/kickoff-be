package com.kickoff.service.fixture.adapter.dataaccess.repository;

import com.kickoff.service.common.domain.vo.FixtureId;
import com.kickoff.service.fixture.domain.entity.Fixture;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface FixtureJpaRepository extends JpaRepository<Fixture, FixtureId> {
  Optional<Fixture> findByApiFootballFixtureId(Long apiFootballFixtureId);
  List<Fixture> findByApiFootballLeagueIdIn(List<Long> apiFootballLeagueId);
  List<Fixture> findByFixtureDateTime_DateBetween(LocalDateTime fixtureDateTimeDateAfter, LocalDateTime fixtureDateTimeDateBefore);
}
