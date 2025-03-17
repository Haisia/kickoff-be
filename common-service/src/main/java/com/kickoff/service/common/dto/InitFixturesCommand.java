package com.kickoff.service.common.dto;

import com.kickoff.service.common.domain.vo.FixtureId;
import com.kickoff.service.common.domain.vo.LeagueId;
import com.kickoff.service.common.domain.vo.TeamId;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Year;
import java.util.*;

@Builder
@NoArgsConstructor @AllArgsConstructor
@Data
public class InitFixturesCommand {
  private Long apiFootballLeagueId;
  private LeagueId leagueId;
  private List<Season> seasons = new ArrayList<>();
  private List<TeamIdMap> teamIdMap = new ArrayList<>();

  public void addSeason(Year year) {
    if (seasons == null) seasons = new ArrayList<>();
    seasons.add(new Season(year, new ArrayList<>()));
  }

  public void addTeamId(TeamId teamId, Long apiFootballFixtureId) {
    if (teamIdMap == null) teamIdMap = new ArrayList<>();
    teamIdMap.add(new TeamIdMap(teamId, apiFootballFixtureId));
  }

  public void addFixturesToSeason(Year year, List<FixtureId> fixtures) {
    if (seasons == null) seasons = new ArrayList<>();
    seasons.stream()
      .filter(season -> season.getYear().equals(year))
      .findFirst()
      .ifPresent(season -> season.fixtures = fixtures);
  }

  public List<Year> getYears() {
    List<Year> years = new ArrayList<>();
    if (seasons != null) {
      seasons.forEach(season -> years.add(season.getYear()));
    }
    return years;
  }

  public List<FixtureId> getFixtures(Year year) {
    if (seasons == null) return new ArrayList<>();
    return seasons.stream()
      .filter(season -> season.getYear().equals(year))
      .findFirst()
      .map(Season::getFixtures)
      .orElse(new ArrayList<>());
  }

  @NoArgsConstructor @AllArgsConstructor
  @Data
  private static class Season {
    private Year year;
    private List<FixtureId> fixtures = new ArrayList<>();
  }

  @NoArgsConstructor @AllArgsConstructor
  @Data
  public static class TeamIdMap {
    private TeamId teamId;
    private Long apiFootballFixtureId;
  }
}
