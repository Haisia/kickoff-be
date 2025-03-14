package com.kickoff.service.league.domain.entity;

import com.kickoff.service.common.domain.entity.AggregateRoot;
import com.kickoff.service.common.domain.vo.TeamId;
import com.kickoff.service.common.domain.vo.UrlInfo;
import com.kickoff.service.common.domain.vo.LeagueId;
import com.kickoff.service.league.domain.vo.Country;
import com.kickoff.service.league.domain.vo.LeagueType;
import com.kickoff.service.league.domain.vo.SeasonId;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter @Setter
@Table(name = "leagues")
@Entity
public class League extends AggregateRoot {
  @EmbeddedId
  private LeagueId id;
  private Long apiFootballLeagueId;
  private String name;
  @Enumerated(EnumType.STRING)
  private LeagueType type;

  @ElementCollection
  @CollectionTable(name = "league_logos", joinColumns = @JoinColumn(name = "league_id"))
  @AttributeOverrides({
    @AttributeOverride(name = "url", column = @Column(name = "logo_url")),
    @AttributeOverride(name = "urlType", column = @Column(name = "logo_type"))
  })
  private List<UrlInfo> logos = new ArrayList<>();

  @Embedded
  private Country country;

  @OneToMany(mappedBy = "league", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  private List<Season> seasons = new ArrayList<>();

  @Builder
  public League(LeagueId id, Long apiFootballLeagueId, String name, LeagueType type, Country country) {
    if (id == null) id = LeagueId.generate();
    this.id = id;
    this.apiFootballLeagueId = apiFootballLeagueId;
    this.name = name;
    this.type = type;
    this.country = country;
  }

  public void addLogo(UrlInfo logo) {
    if (logo == null) return;
    if (logos.contains(logo)) return;
    logos.add(logo);
  }

  public void addSeason(Year year, LocalDate startDate, LocalDate endDate) {
    if (year == null || startDate == null || endDate == null) return;

    SeasonId seasonId = SeasonId.of(this.id, year);
    Season createdSeason = Season.builder()
      .id(seasonId)
      .league(this)
      .startDate(startDate)
      .endDate(endDate)
      .build();

    seasons.add(createdSeason);
  }

  public void addTeam(Year year, TeamId teamId) {
    if (year == null || teamId == null) return;
    Season season = getSeason(year).orElseThrow();
    season.addTeam(teamId);
  }

  public Optional<Season> getSeason(Year year) {
    if (year == null) return Optional.empty();
    return seasons.stream()
      .filter(season -> season.getId().getYear().equals(year))
      .findFirst()
      ;
  }

  @Override
  public boolean equals(Object o) {
    if (o == null || getClass() != o.getClass()) return false;
    League league = (League) o;
    return Objects.equals(id, league.id);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(id);
  }
}
