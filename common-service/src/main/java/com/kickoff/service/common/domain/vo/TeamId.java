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
public class TeamId extends BaseId {
  @Column(name = "team_id")
  private UUID id;

  protected TeamId(UUID id) {
    this.id = id;
  }

  public static TeamId of(UUID value) {
    return new TeamId(value);
  }

  public static TeamId generate() {
    return new TeamId(UUID.randomUUID());
  }

  @Override
  public boolean equals(Object o) {
    if (o == null || getClass() != o.getClass()) return false;
    TeamId teamId = (TeamId) o;
    return Objects.equals(id, teamId.id);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(id);
  }
}
