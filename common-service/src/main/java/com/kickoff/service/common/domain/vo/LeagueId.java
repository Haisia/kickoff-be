package com.kickoff.service.common.domain.vo;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Objects;
import java.util.UUID;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Embeddable
public class LeagueId extends BaseId {
  @Column(name = "league_id")
  private UUID id;

  protected LeagueId(UUID id) {
    this.id = id;
  }

  public static LeagueId of(UUID value) {
    return new LeagueId(value);
  }

  public static LeagueId generate() {
    return of(UUID.randomUUID());
  }

  @Override
  public boolean equals(Object o) {
    if (o == null || getClass() != o.getClass()) return false;
    LeagueId leagueId = (LeagueId) o;
    return Objects.equals(id, leagueId.id);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(id);
  }
}
