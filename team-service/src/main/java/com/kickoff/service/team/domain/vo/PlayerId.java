package com.kickoff.service.team.domain.vo;

import com.kickoff.service.common.domain.vo.BaseId;
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
public class PlayerId extends BaseId {
  @Column(name = "player_id")
  private UUID id;

  protected PlayerId(UUID id) {
    this.id = id;
  }

  public static PlayerId of(UUID value) {
    return new PlayerId(value);
  }

  public static PlayerId generate() {
    return new PlayerId(UUID.randomUUID());
  }

  @Override
  public boolean equals(Object o) {
    if (o == null || getClass() != o.getClass()) return false;
    PlayerId playerId = (PlayerId) o;
    return Objects.equals(id, playerId.id);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(id);
  }
}
