package com.kickoff.service.league.domain.entity;

import com.kickoff.service.common.domain.entity.BaseEntity;
import com.kickoff.service.common.domain.vo.TeamId;
import com.kickoff.service.league.domain.vo.SeasonId;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

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

  @OneToMany(mappedBy = "season", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  private List<SeasonMapTeams> affiliatedTeams = new ArrayList<>();

  @OneToMany(mappedBy = "season", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  private List<SeasonMapFixtures> fixtures = new ArrayList<>();

  @OneToMany(mappedBy = "season", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  private List<LeagueStanding> standings = new ArrayList<>();

  @Builder
  public Season(SeasonId id, League league, LocalDate startDate, LocalDate endDate) {
    if (id == null) return;
    this.id = id;
    this.league = league;
    this.startDate = startDate;
    this.endDate = endDate;
  }

  public void addTeam(TeamId teamId) {
    if (teamId == null) return;
    if (getTeam(teamId).isPresent()) return;
    affiliatedTeams.add(new SeasonMapTeams(id, teamId, this));
  }

  public Optional<SeasonMapTeams> getTeam(TeamId teamId) {
    if (teamId == null) return Optional.empty();
    return affiliatedTeams.stream()
      .filter(team -> team.getId().getTeamId().equals(teamId))
      .findFirst();
  }

  public void addFixture(Long apiFootballFixtureId) {
    if (apiFootballFixtureId == null) return;
    if (getFixture(apiFootballFixtureId).isPresent()) return;
    fixtures.add(new SeasonMapFixtures(id, apiFootballFixtureId, this));
  }

  public Optional<SeasonMapFixtures> getFixture(Long apiFootballFixtureId) {
    if (apiFootballFixtureId == null) return Optional.empty();
    return fixtures.stream()
      .filter(fixture -> fixture.getId().getApiFootballFixtureId().equals(apiFootballFixtureId))
      .findFirst();
  }

  public void updateOrAddStanding(LeagueStanding leagueStanding) {
    if (leagueStanding == null) return;
    standings.stream()
      .filter(standing -> standing.getApiFootballTeamId().equals(leagueStanding.getApiFootballTeamId()))
      .findFirst()
      .ifPresentOrElse(
        standing -> {
          if (leagueStanding.getRank() != null) standing.setRank(leagueStanding.getRank());
          if (leagueStanding.getPoints() != null) standing.setPoints(leagueStanding.getPoints());
          if (leagueStanding.getGoalsDiff() != null) standing.setGoalsDiff(leagueStanding.getGoalsDiff());
          if (leagueStanding.getForm() != null) standing.setForm(leagueStanding.getForm());
          if (leagueStanding.getStatus() != null) standing.setStatus(leagueStanding.getStatus());
          if (leagueStanding.getAllMatchHistory() != null) standing.setAllMatchHistory(leagueStanding.getAllMatchHistory());
          if (leagueStanding.getHomeMatchHistory() != null) standing.setHomeMatchHistory(leagueStanding.getHomeMatchHistory());
          if (leagueStanding.getAwayMatchHistory() != null) standing.setAwayMatchHistory(leagueStanding.getAwayMatchHistory());
        },
        () -> {
          leagueStanding.setSeason(this);
          standings.add(leagueStanding);
        }
      );
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
