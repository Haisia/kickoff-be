package com.kickoff.service.fixture.adapter.externalapi.rapid.dto.fixtures;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.kickoff.service.common.domain.vo.TeamId;
import com.kickoff.service.fixture.domain.entity.Fixture;
import com.kickoff.service.fixture.domain.vo.FixtureDateTime;
import com.kickoff.service.fixture.domain.vo.FixtureStatus;
import com.kickoff.service.fixture.domain.vo.FixtureStatusType;
import com.kickoff.service.fixture.domain.vo.Score;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.util.Map;

@NoArgsConstructor @AllArgsConstructor
@Data
public class FixturesResponse {
  private FixtureDto fixture;
  private LeagueDto league;
  private TeamsDto teams;

  private ScoreDto goals;
  private ScoresDto score;

  public Fixture toFixture(Map<Long, TeamId> teamIdMap) {
    Fixture createdFixture = fixture.toFixture();
    createdFixture.setHomeTeam(TeamId.of(teamIdMap.get(teams.getHome().getId()).getId()));
    createdFixture.setAwayTeam(TeamId.of(teamIdMap.get(teams.getAway().getId()).getId()));
    createdFixture.setHalfTimeScore(score.getHalfTimeScore());
    createdFixture.setFullTimeScore(score.getFullTimeScore());
    createdFixture.setExtraTimeScore(score.getExtraTimeScore());
    createdFixture.setPenaltyTimeScore(score.getPenaltyScore());
    return createdFixture;
  }

  @NoArgsConstructor @AllArgsConstructor
  @Data
  private static class FixtureDto {
    private Long id;
    private String referee;
    private String timezone;
    private OffsetDateTime date;
    private Long timestamp;

    private PeriodsDto periods;
    private VenueDto venue;
    private StatusDto status;

    public Fixture toFixture() {
      Fixture createdFixture = Fixture.builder()
        .apiFootballFixtureId(id)
        .referee(referee)
        .venue(venue.name)
        .fixtureStatus(new FixtureStatus(FixtureStatusType.valueOf(status.shortName), status.elapsed, status.extra))
        .build();
      createdFixture.setFixtureDateTime(new FixtureDateTime(ZoneId.of(timezone), date.toLocalDateTime(), timestamp, periods.first, periods.second));
      return createdFixture;
    }

    @NoArgsConstructor @AllArgsConstructor
    @Data
    private static class PeriodsDto {
      private Long first;
      private Long second;
    }

    @NoArgsConstructor @AllArgsConstructor
    @Data
    private static class StatusDto {
      @JsonProperty("long")
      private String longName;
      @JsonProperty("short")
      private String shortName;
      private Integer elapsed;
      private String extra;
    }
    
    @NoArgsConstructor
    @AllArgsConstructor
    @Data
    private static class VenueDto {
      private Long id;
      private String name;
      private String address;
      private String city;
      private Long capacity;
      private String surface;
      private String image;
    }
  }
  
  @NoArgsConstructor
  @AllArgsConstructor
  @Data
  private static class TeamsDto {
    private TeamDto home;
    private TeamDto away;
  }

  @NoArgsConstructor
  @AllArgsConstructor
  @Data
  private static class LeagueDto {
    private Long id;
    private String name;
    private String country;
    private String logo;
    private String flag;
    private Integer season;
    private String round;
    private Boolean standings;
  }

  @NoArgsConstructor
  @AllArgsConstructor
  @Data
  private static class ScoreDto {
    private Integer home;
    private Integer away;

    public Score toScore() {
      return new Score(home, away);
    }
  }

  @NoArgsConstructor
  @AllArgsConstructor
  @Data
  private static class ScoresDto {
    private ScoreDto halftime;
    private ScoreDto fulltime;
    private ScoreDto extratime;
    private ScoreDto penalty;

    public Score getHalfTimeScore() {
      return halftime.toScore();
    }
    public Score getFullTimeScore() {
      return fulltime.toScore();
    }
    public Score getExtraTimeScore() {
      return extratime.toScore();
    }
    public Score getPenaltyScore() {
      return penalty.toScore();
    }
  }

  @NoArgsConstructor @AllArgsConstructor
  @Data
  private static class TeamDto {
    private Long id;
    private String name;
    private String logo;
    private Boolean winner;
  }
}