package com.kickoff.service.league.adapter.externalapi.rapid.adapter;

import com.kickoff.service.league.adapter.externalapi.rapid.client.LeagueExternalApiClient;
import com.kickoff.service.league.adapter.externalapi.rapid.client.StandingExternalApiClient;
import com.kickoff.service.league.adapter.externalapi.rapid.dto.leagues.LeaguesResponse;
import com.kickoff.service.league.domain.entity.League;
import com.kickoff.service.league.domain.entity.LeagueStanding;
import com.kickoff.service.league.domain.port.output.externalapi.LeagueExternalApiService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Year;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class LeagueExternalApiServiceImpl implements LeagueExternalApiService {

  private final LeagueExternalApiClient leagueExternalApiClient;
  private final StandingExternalApiClient standingExternalApiClient;

  @Override
  public List<League> getAllLeagues() {
    return leagueExternalApiClient.requestLeagues()
      .stream()
      .map(LeaguesResponse::toLeague)
      .collect(Collectors.toList());
  }

  @Override
  public List<LeagueStanding> getLeagueStandings(Long apiFootballLeagueId, Year season) {
    return standingExternalApiClient.requestStandings(apiFootballLeagueId, season)
      .getFirst()
      .toLeagueStandings();
  }
}
