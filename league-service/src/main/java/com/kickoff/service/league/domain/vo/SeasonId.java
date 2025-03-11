package com.kickoff.service.league.domain.vo;

import com.kickoff.service.common.domain.vo.BaseId;
import com.kickoff.service.common.domain.vo.LeagueId;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.Year;
import java.util.Objects;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Embeddable
public class SeasonId extends BaseId {
  private LeagueId leagueId;
  private Year year;

  protected SeasonId(LeagueId leagueId, Year year) {
    this.leagueId = leagueId;
    this.year = year;
  }

  public static SeasonId of(LeagueId leagueId, Year year) {
    return new SeasonId(leagueId, year);
  }

  @Override
  public boolean equals(Object o) {
    if (o == null || getClass() != o.getClass()) return false;
    SeasonId seasonId = (SeasonId) o;
    return Objects.equals(leagueId, seasonId.leagueId) && Objects.equals(year.toString(), seasonId.year.toString());
  }

  @Override
  public int hashCode() {
    return Objects.hash(leagueId, year.toString());
  }
}
