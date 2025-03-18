package com.kickoff.service.league.domain.entity;

import com.kickoff.service.common.domain.entity.BaseEntity;
import com.kickoff.service.league.domain.vo.LeagueStandingId;
import com.kickoff.service.league.domain.vo.MatchHistory;
import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter @Setter
@Table(name = "league_standings")
@Entity
public class LeagueStanding extends BaseEntity {
  @EmbeddedId
  private LeagueStandingId id;
  private Long apiFootballTeamId;

  @ManyToOne(cascade = CascadeType.ALL)
  @JoinColumns({
    @JoinColumn(name = "league_id", nullable = false),
    @JoinColumn(name = "year", nullable = false)
  })
  private Season season;
  private Integer rank;
  private Integer points;
  private Integer goalsDiff;
  private String form;
  private String status;

  @AttributeOverrides({
    @AttributeOverride(name = "played", column = @Column(name = "all_match_history_played")),
    @AttributeOverride(name = "win", column = @Column(name = "all_match_history_win")),
    @AttributeOverride(name = "draw", column = @Column(name = "all_match_history_draw")),
    @AttributeOverride(name = "lose", column = @Column(name = "all_match_history_lose")),
    @AttributeOverride(name = "goalsFor", column = @Column(name = "all_match_history_goals_for")),
    @AttributeOverride(name = "goalsAgainst", column = @Column(name = "all_match_history_goals_against")),
  })
  @Embedded
  private MatchHistory allMatchHistory;

  @AttributeOverrides({
    @AttributeOverride(name = "played", column = @Column(name = "home_match_history_played")),
    @AttributeOverride(name = "win", column = @Column(name = "home_match_history_win")),
    @AttributeOverride(name = "draw", column = @Column(name = "home_match_history_draw")),
    @AttributeOverride(name = "lose", column = @Column(name = "home_match_history_lose")),
    @AttributeOverride(name = "goalsFor", column = @Column(name = "home_match_history_goals_for")),
    @AttributeOverride(name = "goalsAgainst", column = @Column(name = "home_match_history_goals_against")),
  })
  @Embedded
  private MatchHistory homeMatchHistory;

  @AttributeOverrides({
    @AttributeOverride(name = "played", column = @Column(name = "away_match_history_played")),
    @AttributeOverride(name = "win", column = @Column(name = "away_match_history_win")),
    @AttributeOverride(name = "draw", column = @Column(name = "away_match_history_draw")),
    @AttributeOverride(name = "lose", column = @Column(name = "away_match_history_lose")),
    @AttributeOverride(name = "goalsFor", column = @Column(name = "away_match_history_goals_for")),
    @AttributeOverride(name = "goalsAgainst", column = @Column(name = "away_match_history_goals_against")),
  })
  @Embedded
  private MatchHistory awayMatchHistory;

  @Builder
  public LeagueStanding(LeagueStandingId id, Long apiFootballTeamId, Season season, Integer rank, Integer points, Integer goalsDiff, String form, String status, MatchHistory allMatchHistory, MatchHistory homeMatchHistory, MatchHistory awayMatchHistory) {
    if (id == null) id = LeagueStandingId.generate();
    this.id = id;
    this.apiFootballTeamId = apiFootballTeamId;
    this.season = season;
    this.rank = rank;
    this.points = points;
    this.goalsDiff = goalsDiff;
    this.form = form;
    this.status = status;
    this.allMatchHistory = allMatchHistory;
    this.homeMatchHistory = homeMatchHistory;
    this.awayMatchHistory = awayMatchHistory;
  }

  @Override
  public boolean equals(Object o) {
    if (o == null || getClass() != o.getClass()) return false;
    LeagueStanding that = (LeagueStanding) o;
    return Objects.equals(id, that.id);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(id);
  }
}
