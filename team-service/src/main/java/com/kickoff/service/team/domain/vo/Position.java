package com.kickoff.service.team.domain.vo;

import java.util.Arrays;

public enum Position {
  UNKNOWN,
  GOALKEEPER,
  DEFENDER,
  MIDFIELDER,
  ATTACKER,
  ;

  public static Position parseIgnoreCase(String value) {
    if (value == null) {
      return Position.UNKNOWN;
    }
    return Arrays.stream(Position.values())
      .filter(name -> name.toString().equalsIgnoreCase(value))
      .findFirst()
      .orElse(Position.UNKNOWN);
  }
}
