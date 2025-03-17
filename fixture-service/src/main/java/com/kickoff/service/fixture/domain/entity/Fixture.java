package com.kickoff.service.fixture.domain.entity;

import com.kickoff.service.common.domain.entity.AggregateRoot;
import com.kickoff.service.common.domain.vo.FixtureId;
import com.kickoff.service.common.domain.vo.LeagueId;
import com.kickoff.service.common.domain.vo.TeamId;
import com.kickoff.service.fixture.domain.vo.FixtureDateTime;
import com.kickoff.service.fixture.domain.vo.FixtureStatus;
import com.kickoff.service.fixture.domain.vo.Score;
import jakarta.persistence.*;
import lombok.*;

import java.time.Year;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter @Setter
@Table(name = "fixtures")
@Entity
public class Fixture extends AggregateRoot {
  @EmbeddedId
  private FixtureId id;

  @Column(unique = true)
  private Long apiFootballFixtureId;

  private LeagueId leagueId;

  private Year seasonYear;

  // 심판 이름
  private String referee;
  private FixtureDateTime fixtureDateTime;

  private String venue;

  @Embedded
  private FixtureStatus fixtureStatus;

  @AttributeOverride(name = "id", column = @Column(name = "home_team_id"))
  private TeamId homeTeam;

  @AttributeOverride(name = "id", column = @Column(name = "away_team_id"))
  private TeamId awayTeam;

  @AttributeOverrides({
    @AttributeOverride(name = "home", column = @Column(name = "half_home_score")),
    @AttributeOverride(name = "away", column = @Column(name = "half_away_score")),
  })
  @Embedded
  private Score halfTimeScore;

  @AttributeOverrides({
    @AttributeOverride(name = "home", column = @Column(name = "full_home_score")),
    @AttributeOverride(name = "away", column = @Column(name = "full_away_score")),
  })
  @Embedded
  private Score fullTimeScore;

  @AttributeOverrides({
    @AttributeOverride(name = "home", column = @Column(name = "extra_home_score")),
    @AttributeOverride(name = "away", column = @Column(name = "extra_away_score")),
  })
  @Embedded
  private Score extraTimeScore;

  @AttributeOverrides({
    @AttributeOverride(name = "home", column = @Column(name = "penalty_home_score")),
    @AttributeOverride(name = "away", column = @Column(name = "penalty_away_score")),
  })
  @Embedded
  private Score penaltyTimeScore;

  @OneToMany(mappedBy = "fixture", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  private List<FixtureStatistic> fixtureStatistics = new ArrayList<>();

  @Builder
  public Fixture(FixtureId id, Long apiFootballFixtureId, LeagueId leagueId, Year seasonYear, String referee, FixtureDateTime fixtureDateTime, String venue, FixtureStatus fixtureStatus, TeamId homeTeam, TeamId awayTeam, Score halfTimeScore, Score fullTimeScore, Score extraTimeScore, Score penaltyTimeScore) {
    if (id == null) id = FixtureId.generate();
    this.id = id;
    this.apiFootballFixtureId = apiFootballFixtureId;
    this.leagueId = leagueId;
    this.seasonYear = seasonYear;
    this.referee = referee;
    this.fixtureDateTime = fixtureDateTime;
    this.venue = venue;
    this.fixtureStatus = fixtureStatus;
    this.homeTeam = homeTeam;
    this.awayTeam = awayTeam;
    this.halfTimeScore = halfTimeScore;
    this.fullTimeScore = fullTimeScore;
    this.extraTimeScore = extraTimeScore;
    this.penaltyTimeScore = penaltyTimeScore;
  }
}
