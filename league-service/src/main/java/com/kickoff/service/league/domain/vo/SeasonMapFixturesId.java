package com.kickoff.service.league.domain.vo;

import com.kickoff.service.common.domain.vo.BaseId;
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
  private Long apiFootballFixtureId;

  protected SeasonMapFixturesId(SeasonId seasonId, Long apiFootballFixtureId) {
    this.seasonId = seasonId;
    this.apiFootballFixtureId = apiFootballFixtureId;
  }

  public static SeasonMapFixturesId of(SeasonId seasonId, Long apiFootballFixtureId) {
    return new SeasonMapFixturesId(seasonId, apiFootballFixtureId);
  }

  @Override
  public boolean equals(Object o) {
    if (o == null || getClass() != o.getClass()) return false;
    SeasonMapFixturesId that = (SeasonMapFixturesId) o;
    return Objects.equals(seasonId, that.seasonId) && Objects.equals(apiFootballFixtureId, that.apiFootballFixtureId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(seasonId, apiFootballFixtureId);
  }
}
