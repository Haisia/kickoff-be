package com.kickoff.service.league.adapter.externalapi.rapid.client;

import com.kickoff.service.league.adapter.externalapi.rapid.dto.RapidApiResponse;
import com.kickoff.service.league.adapter.externalapi.rapid.dto.leagues.LeaguesResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.Objects;

@RequiredArgsConstructor
@Component
public class LeagueExternalApiClient {

  private final WebClient webClient;

  public List<LeaguesResponse> requestLeagues() {
    ParameterizedTypeReference<RapidApiResponse<LeaguesResponse>> responseType = new ParameterizedTypeReference<>() {};

    return Objects.requireNonNull(
      webClient.get()
        .uri("/leagues")
        .retrieve()
        .bodyToMono(responseType)
        .block()
    ).getResponse();
  }
}
