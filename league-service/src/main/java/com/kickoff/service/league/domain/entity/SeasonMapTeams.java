package com.kickoff.service.league.domain.entity;

import com.kickoff.service.common.domain.entity.BaseEntity;
import com.kickoff.service.common.domain.vo.TeamId;
import com.kickoff.service.league.domain.vo.SeasonId;
import com.kickoff.service.league.domain.vo.SeasonMapTeamsId;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter @Setter
@Table(name = "season_map_teams")
@Entity
public class SeasonMapTeams extends BaseEntity {

  @EmbeddedId
  private SeasonMapTeamsId id;

  @MapsId("seasonId")
  @ManyToOne(cascade = CascadeType.ALL)
  @JoinColumns({
    @JoinColumn(name = "league_id", nullable = false),
    @JoinColumn(name = "year", nullable = false)
  })
  private Season season;

  public SeasonMapTeams(SeasonId seasonId, TeamId teamId, Season season) {
    if (seasonId == null || teamId == null) return;
    this.id = SeasonMapTeamsId.of(seasonId, teamId);
    this.season = season;
  }

  @Override
  public boolean equals(Object o) {
    if (o == null || getClass() != o.getClass()) return false;
    SeasonMapTeams that = (SeasonMapTeams) o;
    return Objects.equals(id, that.id);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(id);
  }
}
