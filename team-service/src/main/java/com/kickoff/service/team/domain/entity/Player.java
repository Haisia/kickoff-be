package com.kickoff.service.team.domain.entity;

import com.kickoff.service.common.domain.entity.BaseEntity;
import com.kickoff.service.common.domain.vo.UrlInfo;
import com.kickoff.service.team.domain.vo.PlayerId;
import com.kickoff.service.team.domain.vo.Position;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter @Setter
@Table(name = "players")
@Entity
public class Player extends BaseEntity {
  @EmbeddedId
  private PlayerId id;
  @Column(unique = true)
  private Long apiFootballPlayerId;
  private String name;
  private Integer age;
  private Integer number;
  @Enumerated(EnumType.STRING)
  private Position position;
  @ElementCollection
  @CollectionTable(name = "player_photos", joinColumns = @JoinColumn(name = "player_id"))
  @AttributeOverrides({
    @AttributeOverride(name = "url", column = @Column(name = "photo_url")),
    @AttributeOverride(name = "urlType", column = @Column(name = "photo_url_type"))
  })
  private List<UrlInfo> photo = new ArrayList<>();

  @ManyToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "team_id")
  private Team currentTeam;

  @Builder
  public Player(PlayerId id, Long apiFootballPlayerId, String name, Integer age, Integer number, Position position, Team currentTeam) {
    if (id == null) id = PlayerId.generate();
    this.id = id;
    this.apiFootballPlayerId = apiFootballPlayerId;
    this.name = name;
    this.age = age;
    this.number = number;
    this.position = position;
    this.currentTeam = currentTeam;
  }

  public void addPhoto(UrlInfo photo) {
    if (photo == null) return;
    if (this.photo.contains(photo)) return;
    this.photo.add(photo);
  }

  @Override
  public boolean equals(Object o) {
    if (o == null || getClass() != o.getClass()) return false;
    Player player = (Player) o;
    return Objects.equals(id, player.id);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(id);
  }
}
