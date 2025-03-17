package com.kickoff.service.team.domain.entity;

import com.kickoff.service.common.domain.entity.AggregateRoot;
import com.kickoff.service.common.domain.vo.LeagueId;
import com.kickoff.service.common.domain.vo.UrlInfo;
import com.kickoff.service.common.domain.vo.TeamId;
import com.kickoff.service.common.domain.vo.UrlType;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@NoArgsConstructor
@Getter
@Setter
@Table(name = "teams")
@Entity
public class Team extends AggregateRoot {
  @EmbeddedId
  private TeamId id;
  @Column(unique = true)
  private Long apiFootballTeamId;
  private String name;
  private String code;
  private String country;
  private Integer founded;
  private Boolean national;

  private LeagueId leagueId;

  @ElementCollection
  @CollectionTable(name = "team_logos", joinColumns = @JoinColumn(name = "team_id"))
  @AttributeOverrides({
    @AttributeOverride(name = "url", column = @Column(name = "logo_url")),
    @AttributeOverride(name = "urlType", column = @Column(name = "logo_url_type"))
  })
  private List<UrlInfo> logos = new ArrayList<>();

  @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "team")
  private List<Venue> venues = new ArrayList<>();

  @OneToMany(mappedBy = "currentTeam", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  private List<Player> players = new ArrayList<>();

  @Builder
  public Team(TeamId id, Long apiFootballTeamId, String name, String code, String country, Integer founded, Boolean national) {
    if (id == null) id = TeamId.generate();
    this.id = id;
    this.apiFootballTeamId = apiFootballTeamId;
    this.name = name;
    this.code = code;
    this.country = country;
    this.founded = founded;
    this.national = national;
  }

  public void addLogo(String url, UrlType urlType) {
    UrlInfo target = new UrlInfo(url, urlType);
    if (logos.contains(target)) return;
    logos.add(target);
  }

  public void addVenue(Venue venue) {
    if (venue == null) return;
    if (venues.contains(venue)) return;
    venues.add(venue);
    venue.setTeam(this);
  }

  @Override
  public boolean equals(Object o) {
    if (o == null || getClass() != o.getClass()) return false;
    Team team = (Team) o;
    return Objects.equals(id, team.id);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(id);
  }
}
