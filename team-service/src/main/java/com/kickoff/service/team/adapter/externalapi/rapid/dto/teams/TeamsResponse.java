package com.kickoff.service.team.adapter.externalapi.rapid.dto.teams;

import com.kickoff.service.common.domain.vo.UrlType;
import com.kickoff.service.team.domain.entity.Team;
import com.kickoff.service.team.domain.entity.Venue;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class TeamsResponse {
  private TeamDto team;
  private VenueDto venue;

  public Team toTeam() {
    Team createdTeam = team.toTeam();
    createdTeam.addVenue(venue.toVenue());
    return createdTeam;
  }

  @NoArgsConstructor @AllArgsConstructor
  @Data
  private static class TeamDto {
    public Long id;
    public String name;
    public String code;
    public String country;
    public Integer founded;
    public Boolean national;
    public String logo;

    public Team toTeam() {
      Team createdTeam = Team.builder()
        .apiFootballTeamId(id)
        .name(name)
        .code(code)
        .country(country)
        .founded(founded)
        .national(national)
        .build();
      createdTeam.addLogo(logo, UrlType.EXTERNAL);
      return createdTeam;
    }
  }

  @NoArgsConstructor @AllArgsConstructor
  @Data
  private static class VenueDto {
    public Long id;
    public String name;
    public String address;
    public String city;
    public Long capacity;
    public String surface;
    public String image;

    public Venue toVenue() {
      Venue createVenue = Venue.builder()
        .apiFootballVenueId(id)
        .name(name)
        .address(address)
        .city(city)
        .capacity(capacity)
        .surface(surface)
        .build();
      createVenue.addImage(image, UrlType.EXTERNAL);
      return createVenue;
    }
  }
}
