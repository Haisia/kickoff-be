package com.kickoff.service.league.adapter.externalapi.rapid.client;

import com.kickoff.service.common.dto.RapidApiResponse;
import com.kickoff.service.league.adapter.externalapi.rapid.dto.standings.StandingsResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.Year;
import java.util.List;
import java.util.Objects;

@RequiredArgsConstructor
@Component
public class StandingExternalApiClient {

  private final WebClient webClient;

  public List<StandingsResponse> requestStandings(Long apiFootballLeagueId, Year year) {
    ParameterizedTypeReference<RapidApiResponse<StandingsResponse>> responseType = new ParameterizedTypeReference<>() {};

    return Objects.requireNonNull(
      webClient.get()
        .uri(String.format("/standings?league=%d&season=%d", apiFootballLeagueId, year.getValue()))
        .retrieve()
        .bodyToMono(responseType)
        .block()
    ).getResponse();
  }
}
