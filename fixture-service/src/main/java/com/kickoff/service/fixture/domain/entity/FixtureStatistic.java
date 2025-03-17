package com.kickoff.service.fixture.domain.entity;

import com.kickoff.service.common.domain.entity.BaseEntity;
import com.kickoff.service.common.domain.vo.TeamId;
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

  @ManyToOne
  @JoinColumn(name = "fixture_id")
  private Fixture fixture;

  @AttributeOverride(name = "id", column = @Column(name = "team_id"))
  private TeamId teamId;

  private String type;
  private String value;

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
