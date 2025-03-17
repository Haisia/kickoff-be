package com.kickoff.service.league.domain.vo;

import com.kickoff.service.common.domain.vo.BaseId;
import com.kickoff.service.common.domain.vo.FixtureId;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Embeddable
public class SeasonMapFixturesId extends BaseId {
  private SeasonId seasonId;
  private FixtureId fixtureId;

  protected SeasonMapFixturesId(SeasonId seasonId, FixtureId fixtureId) {
    this.seasonId = seasonId;
    this.fixtureId = fixtureId;
  }

  public static SeasonMapFixturesId of(SeasonId seasonId, FixtureId fixtureId) {
    return new SeasonMapFixturesId(seasonId, fixtureId);
  }

  @Override
  public boolean equals(Object o) {
    if (o == null || getClass() != o.getClass()) return false;
    SeasonMapFixturesId that = (SeasonMapFixturesId) o;
    return Objects.equals(seasonId, that.seasonId) && Objects.equals(fixtureId, that.fixtureId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(seasonId, fixtureId);
  }
}
