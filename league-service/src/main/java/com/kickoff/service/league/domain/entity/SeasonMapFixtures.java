package com.kickoff.service.league.domain.entity;

import com.kickoff.service.common.domain.entity.BaseEntity;
import com.kickoff.service.league.domain.vo.SeasonId;
import com.kickoff.service.league.domain.vo.SeasonMapFixturesId;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter @Setter
@Table(name = "season_map_fixtures")
@Entity
public class SeasonMapFixtures extends BaseEntity {

  @EmbeddedId
  private SeasonMapFixturesId id;

  @MapsId("seasonId")
  @ManyToOne(cascade = CascadeType.ALL)
  @JoinColumns({
    @JoinColumn(name = "league_id", nullable = false),
    @JoinColumn(name = "year", nullable = false)
  })
  private Season season;

  public SeasonMapFixtures(SeasonId seasonId, Long apiFootballFixtureId, Season season) {
    if (seasonId == null || apiFootballFixtureId == null) return;
    this.id = SeasonMapFixturesId.of(seasonId, apiFootballFixtureId);
    this.season = season;
  }

  @Override
  public boolean equals(Object o) {
    if (o == null || getClass() != o.getClass()) return false;
    SeasonMapFixtures that = (SeasonMapFixtures) o;
    return Objects.equals(id, that.id);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(id);
  }
}
