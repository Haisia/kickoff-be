package com.kickoff.service.fixture.domain.port.output.repository;

import com.kickoff.service.fixture.domain.entity.Fixture;

import java.util.Optional;

public interface FixtureRepository {
  Optional<Fixture> findByApiFootballFixtureId(Long apiFootballFixtureId);
  Fixture save(Fixture fixture);
}
