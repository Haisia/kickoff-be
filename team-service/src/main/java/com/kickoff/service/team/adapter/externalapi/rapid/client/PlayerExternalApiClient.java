package com.kickoff.service.team.adapter.externalapi.rapid.client;

import com.kickoff.service.common.dto.RapidApiResponse;
import com.kickoff.service.team.adapter.externalapi.rapid.dto.players.SquadsResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.Objects;

@RequiredArgsConstructor
@Component
public class PlayerExternalApiClient {

  private final WebClient webClient;

  public List<SquadsResponse> requestPlayersSquads(Long apiFootballTeamId) {
    ParameterizedTypeReference<RapidApiResponse<SquadsResponse>> responseType = new ParameterizedTypeReference<>() {};

    return Objects.requireNonNull(
      webClient.get()
        .uri(String.format("/players/squads?team=%d", apiFootballTeamId))
        .retrieve()
        .bodyToMono(responseType)
        .block()
    ).getResponse();
  }
}
