package com.kickoff.service.league.domain.vo;

import com.kickoff.service.common.domain.vo.BaseId;
import com.kickoff.service.common.domain.vo.TeamId;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Embeddable
public class SeasonMapTeamsId extends BaseId {
  private SeasonId seasonId;
  private TeamId teamId;

  protected SeasonMapTeamsId(SeasonId seasonId, TeamId teamId) {
    this.seasonId = seasonId;
    this.teamId = teamId;
  }

  public static SeasonMapTeamsId of(SeasonId seasonId, TeamId teamId) {
    return new SeasonMapTeamsId(seasonId, teamId);
  }

  @Override
  public boolean equals(Object o) {
    if (o == null || getClass() != o.getClass()) return false;
    SeasonMapTeamsId that = (SeasonMapTeamsId) o;
    return Objects.equals(seasonId, that.seasonId) && Objects.equals(teamId, that.teamId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(seasonId, teamId);
  }
}
