package com.kickoff.service.fixture.domain.entity;

import com.kickoff.service.common.domain.entity.BaseEntity;
import com.kickoff.service.fixture.domain.vo.FixtureStatisticId;
import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter @Setter
@Table(name = "fixture_statistics")
@Entity
public class FixtureStatistic extends BaseEntity {
  @EmbeddedId
  private FixtureStatisticId id;

  @ManyToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "fixture_id")
  private Fixture fixture;

  private Long apiFootballTeamId;

  private String type;
  private String value;

  @Builder
  public FixtureStatistic(FixtureStatisticId id, Fixture fixture, Long apiFootballTeamId, String type, String value) {
    if (id == null) id = FixtureStatisticId.generate();
    this.id = id;
    this.fixture = fixture;
    this.apiFootballTeamId = apiFootballTeamId;
    this.type = type;
    this.value = value;
  }

  @Override
  public boolean equals(Object o) {
    if (o == null || getClass() != o.getClass()) return false;
    FixtureStatistic that = (FixtureStatistic) o;
    return Objects.equals(id, that.id);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(id);
  }
}
