package com.kickoff.service.league.domain.entity;

import com.kickoff.service.common.domain.entity.BaseEntity;
import com.kickoff.service.league.domain.vo.SeasonId;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.Objects;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter @Setter
@Table(name = "seasons")
@Entity
public class Season extends BaseEntity {

  @EmbeddedId
  private SeasonId id;

  @MapsId("leagueId")
  @ManyToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "league_id", nullable = false)
  private League league;
  private LocalDate startDate;
  private LocalDate endDate;

  @Builder
  public Season(SeasonId id, League league, LocalDate startDate, LocalDate endDate) {
    if (id == null) return;
    this.id = id;
    this.league = league;
    this.startDate = startDate;
    this.endDate = endDate;
  }

  @Override
  public boolean equals(Object o) {
    if (o == null || getClass() != o.getClass()) return false;
    Season season = (Season) o;
    return Objects.equals(id, season.id);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(id);
  }
}
