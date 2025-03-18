package com.kickoff.service.league.domain.port.output.externalapi;

import com.kickoff.service.league.domain.entity.League;
import com.kickoff.service.league.domain.entity.LeagueStanding;

import java.time.Year;
import java.util.List;

public interface LeagueExternalApiService {
  List<League> getAllLeagues();
  List<LeagueStanding> getLeagueStandings(Long apiFootballLeagueId, Year season);
}
