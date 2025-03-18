package com.kickoff.service.common.dto;

import com.kickoff.service.common.domain.vo.LeagueId;
import com.kickoff.service.common.domain.vo.TeamId;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Year;
import java.util.ArrayList;
import java.util.List;

@Builder
@NoArgsConstructor @AllArgsConstructor
@Data
public class InitTeamsCommand {
  private Long apiFootballLeagueId;
  private LeagueId leagueId;
  private List<Season> seasons = new ArrayList<>();

  public void addSeason(Year year) {
    if (seasons == null) seasons = new ArrayList<>();
    seasons.add(new Season(year, new ArrayList<>()));
  }

  public List<Year> getYears() {
    List<Year> years = new ArrayList<>();
    if (seasons != null) {
      seasons.forEach(season -> years.add(season.getYear()));
    }
    return years;
  }

  public List<TeamId> getTeams(Year year) {
    if (seasons == null) return new ArrayList<>();
    return seasons.stream()
      .filter(season -> season.getYear().equals(year))
      .findFirst()
      .map(Season::getTeams)
      .orElse(new ArrayList<>());
  }

  public void addTeam(Year year, TeamId teamId) {
    if (seasons == null) seasons = new ArrayList<>();
    seasons.stream()
      .filter(season -> season.getYear().equals(year))
      .findFirst()
      .ifPresent(season -> season.getTeams().add(teamId));
  }

  public void addAllTeams(Year year, List<TeamId> teamIds) {
    if (seasons == null) seasons = new ArrayList<>();
    seasons.stream()
      .filter(season -> season.getYear().equals(year))
      .findFirst()
      .ifPresent(season -> season.teams = teamIds);
  }

  @NoArgsConstructor @AllArgsConstructor
  @Data
  private static class Season {
    private Year year;
    private List<TeamId> teams;
  }
}
