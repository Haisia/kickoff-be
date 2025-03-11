package com.kickoff.service.league.domain.vo;

import lombok.Getter;

import java.util.Arrays;

@Getter
public enum LeagueType {
  UNKNOWN("unknown"),
  CUP("cup"),
  LEAGUE("league"),
  ;

  public final String value;

  LeagueType(String value) {
    this.value = value;
  }

  public static LeagueType fromValue(String value) {
    return Arrays.stream(LeagueType.values())
      .filter(name -> name.getValue().equalsIgnoreCase(value))
      .findFirst()
      .orElse(LeagueType.UNKNOWN);
  }
}
