package com.kickoff.service.league.domain.port.output.externalapi;

import com.kickoff.service.league.domain.entity.League;

import java.util.List;

public interface LeagueExternalApiService {
  List<League> getAllLeagues();
}
