package com.kickoff.service.common.application.constant;

import java.util.List;

public class ApplicationConfigConstants {
  public static final List<Long> AVAILABLE_LEAGUE_API_FOOTBALL_LEAGUE_IDS = List.of(
    //    2L  // UEFA
    39L  // EPL
    , 140L  // LaLiga
    , 135L  // Serie A
    , 78L // Bundesliga
    , 61L // France Ligue 1
    , 292L // K League 1
  );

  private ApplicationConfigConstants() {
  }
}
