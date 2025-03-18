package com.kickoff.service.fixture.adapter.externalapi.rapid.dto.fixtures;

import com.kickoff.service.fixture.domain.entity.FixtureStatistic;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor @AllArgsConstructor
@Data
public class FixtureStatisticsResponse {

  private TeamDto team;
  private List<StatisticDto> statistics;

  public List<FixtureStatistic> toFixtureStatistics() {
    return statistics.stream().map(dto -> {
      FixtureStatistic fixtureStatistic = dto.toFixtureStatistic();
      fixtureStatistic.setApiFootballTeamId(team.getId());
      return fixtureStatistic;
    }).collect(Collectors.toList());
  }

  @NoArgsConstructor @AllArgsConstructor
  @Data
  private static class TeamDto {
    private Long id;
    private String name;
    private String logo;
  }

  @NoArgsConstructor @AllArgsConstructor
  @Data
  private static class StatisticDto {
    private String type;
    private String value;

    public FixtureStatistic toFixtureStatistic() {
      return FixtureStatistic.builder()
        .type(type)
        .value(value)
        .build();
    }
  }
}
