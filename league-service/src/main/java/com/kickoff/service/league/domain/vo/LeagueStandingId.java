package com.kickoff.service.league.domain.vo;

import com.kickoff.service.common.domain.vo.BaseId;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Objects;
import java.util.UUID;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Embeddable
public class LeagueStandingId extends BaseId {
  private UUID id;

  protected LeagueStandingId(UUID id) {
    this.id = id;
  }

  public static LeagueStandingId of(UUID id) {
    return new LeagueStandingId(id);
  }

  public static LeagueStandingId generate() {
    return of(UUID.randomUUID());
  }

  @Override
  public boolean equals(Object o) {
    if (o == null || getClass() != o.getClass()) return false;
    LeagueStandingId that = (LeagueStandingId) o;
    return Objects.equals(id, that.id);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(id);
  }
}
