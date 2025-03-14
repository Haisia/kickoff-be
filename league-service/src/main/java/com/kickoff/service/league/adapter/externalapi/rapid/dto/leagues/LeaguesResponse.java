package com.kickoff.service.league.adapter.externalapi.rapid.dto.leagues;

import com.kickoff.service.common.domain.vo.UrlInfo;
import com.kickoff.service.common.domain.vo.UrlType;
import com.kickoff.service.league.domain.entity.League;
import com.kickoff.service.league.domain.vo.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.Year;
import java.util.List;

@NoArgsConstructor @AllArgsConstructor
@Data
public class LeaguesResponse {
  private LeagueDto league;
  private CountryDto country;
  private List<SeasonDto> seasons;

  public League toLeague() {
    League createdLeague = league.toLeague();
    createdLeague.setCountry(country.toCountry());
    seasons.forEach(seasonDto -> createdLeague.addSeason(Year.of(seasonDto.getYear()), seasonDto.getStart(), seasonDto.getEnd()));
    return createdLeague;
  }

  @NoArgsConstructor @AllArgsConstructor
  @Data
  private static class LeagueDto {
    public Long id;
    public String name;
    public String type;
    public String logo;

    public League toLeague() {
      League createdLeague = League.builder()
        .apiFootballLeagueId(id)
        .name(name)
        .type(LeagueType.fromValue(type))
        .build();
      createdLeague.addLogo(new UrlInfo(logo, UrlType.EXTERNAL));
      return createdLeague;
    }
  }

  @NoArgsConstructor @AllArgsConstructor
  @Data
  private static class CountryDto {
    public String name;
    public String code;
    public String flag;

    public Country toCountry() {
      Flag createdFlag = new Flag(flag, UrlType.EXTERNAL);
      return Country.builder()
        .name(name)
        .code(code)
        .flag(createdFlag)
        .build();
    }
  }

  @NoArgsConstructor @AllArgsConstructor
  @Data
  private static class SeasonDto {
    public Integer year;
    public LocalDate start;
    public LocalDate end;
    public Boolean current;
    public CoverageDto coverage;

    @NoArgsConstructor @AllArgsConstructor
    @Data
    private static class CoverageDto {
      public FixturesDto fixtures;

      public Boolean standings;
      public Boolean players;
      public Boolean top_corers;
      public Boolean top_assists;
      public Boolean top_cards;
      public Boolean injuries;
      public Boolean predictions;
      public Boolean odds;

      @NoArgsConstructor @AllArgsConstructor
      @Data
      private static class FixturesDto {
        public Boolean events;
        public Boolean lineups;
        public Boolean statistics_fixtures;
        public Boolean statistics_players;
      }
    }
  }

}
