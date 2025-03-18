package com.kickoff.service.league.adapter.externalapi.rapid.dto.standings;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.kickoff.service.league.domain.entity.LeagueStanding;
import com.kickoff.service.league.domain.vo.MatchHistory;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Year;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class StandingsResponse {

  private LeagueDto league;

  public List<LeagueStanding> toLeagueStandings() {
    return league.toLeagueStandings();
  }

  @NoArgsConstructor
  @AllArgsConstructor
  @Data
  private static class LeagueDto {
    private Long id;
    private String name;
    private String logo;
    private String flag;
    private Year year;

    private List<List<StandingDto>> standings;

    public List<LeagueStanding> toLeagueStandings() {
      List<LeagueStanding> result = new ArrayList<>();

      for (List<StandingDto> standingGroup : standings) {
        for (StandingDto standing : standingGroup) {
          StandingDto.TeamDto teamDto = standing.getTeam();
          LeagueStanding leagueStanding = LeagueStanding.builder()
            .apiFootballTeamId(teamDto.getId())
            .rank(standing.getRank())
            .points(standing.getPoints())
            .goalsDiff(standing.getGoalsDiff())
            .form(standing.getForm())
            .status(standing.getStatus())
            .allMatchHistory(standing.getAll().toMatchHistory())
            .homeMatchHistory(standing.getHome().toMatchHistory())
            .awayMatchHistory(standing.getAway().toMatchHistory())
            .build();
          result.add(leagueStanding);
        }
      }
      return result;
    }

    @NoArgsConstructor
    @AllArgsConstructor
    @Data
    private static class StandingDto {
      private Integer rank;
      private TeamDto team;
      private Integer points;
      private Integer goalsDiff;
      private String group;
      private String form;
      private String status;
      private String description;
      private MatchHistoryDto all;
      private MatchHistoryDto home;
      private MatchHistoryDto away;

      @NoArgsConstructor
      @AllArgsConstructor
      @Data
      private static class TeamDto {
        private Long id;
        private String name;
        private String logo;
      }

      @NoArgsConstructor
      @AllArgsConstructor
      @Data
      private static class MatchHistoryDto {
        private Integer played;
        private Integer win;
        private Integer draw;
        private Integer lose;
        private GoalInfoDto goals;

        public MatchHistory toMatchHistory() {
          return new MatchHistory(played, win, draw, lose, goals.getForGoals(), goals.getAgainst());
        }

        @NoArgsConstructor
        @AllArgsConstructor
        @Data
        private static class GoalInfoDto {
          @JsonProperty("for")
          private Integer forGoals;
          private Integer against;
        }
      }
    }
  }
}