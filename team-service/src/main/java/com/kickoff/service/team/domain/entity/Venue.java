package com.kickoff.service.team.domain.entity;

import com.kickoff.service.common.domain.entity.BaseEntity;
import com.kickoff.service.common.domain.vo.UrlInfo;
import com.kickoff.service.common.domain.vo.UrlType;
import com.kickoff.service.team.domain.vo.VenueId;
import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter @Setter
@Table(name = "venues")
@Entity
public class Venue extends BaseEntity {
  @EmbeddedId
  private VenueId id;
  private Long apiFootballVenueId;
  private String name;
  private String address;
  private String city;
  private Long capacity;
  private String surface;

  @AttributeOverrides({
    @AttributeOverride(name = "url", column = @Column(name = "image_url")),
    @AttributeOverride(name = "urlType", column = @Column(name = "image_url_type"))
  })
  private UrlInfo image;

  @ManyToOne
  @JoinColumn(name = "team_id")
  private Team team;

  @Builder
  public Venue(VenueId id, Long apiFootballVenueId, String name, String address, String city, Long capacity, String surface, UrlInfo image, Team team) {
    if (id == null) id = VenueId.generate();
    this.id = id;
    this.apiFootballVenueId = apiFootballVenueId;
    this.name = name;
    this.address = address;
    this.city = city;
    this.capacity = capacity;
    this.surface = surface;
    this.image = image;
    this.team = team;
  }

  public void addImage(String imageUrl, UrlType imageUrlType) {
    if (imageUrl == null || imageUrlType == null) return;
    this.image = new UrlInfo(imageUrl, imageUrlType);
  }

  @Override
  public boolean equals(Object o) {
    if (o == null || getClass() != o.getClass()) return false;
    Venue venue = (Venue) o;
    return Objects.equals(id, venue.id);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(id);
  }
}
