package com.kickoff.service.fixture.adapter.dataaccess.adapter;

import com.kickoff.service.fixture.adapter.dataaccess.repository.FixtureJpaRepository;
import com.kickoff.service.fixture.domain.entity.Fixture;
import com.kickoff.service.fixture.domain.port.output.repository.FixtureRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class FixtureRepositoryImpl implements FixtureRepository {

  private final FixtureJpaRepository fixtureJpaRepository;

  @Override
  public Optional<Fixture> findByApiFootballFixtureId(Long apiFootballFixtureId) {
    return fixtureJpaRepository.findByApiFootballFixtureId(apiFootballFixtureId);
  }

  @Override
  public Fixture save(Fixture fixture) {
    return fixtureJpaRepository.save(fixture);
  }

  @Override
  public List<Fixture> findAll() {
    return fixtureJpaRepository.findAll();
  }

  @Override
  public List<Fixture> findByApiFootballLeagueIdIn(List<Long> apiFootballLeagueIds) {
    return fixtureJpaRepository.findByApiFootballLeagueIdIn(apiFootballLeagueIds);
  }

  @Override
  public List<Fixture> findLiveFixture() {
    return fixtureJpaRepository.findByFixtureDateTime_DateBetween(LocalDateTime.now().minusMinutes(150), LocalDateTime.now().plusMinutes(150));
  }
}
