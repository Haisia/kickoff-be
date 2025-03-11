package com.kickoff.service.common.application.constant;

import java.util.List;

public class ApplicationConfigConstants {
  public static final List<Integer> AVAILABLE_LEAGUE_API_FOOTBALL_LEAGUE_IDS = List.of(
    //    2  // UEFA
    39  // EPL
    , 140  // LaLiga
    , 135  // Serie A
    , 78 // Bundesliga
    , 61 // France Ligue 1
    , 292 // K League 1
  );

  private ApplicationConfigConstants() {
  }
}
