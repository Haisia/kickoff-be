package com.kickoff.service.team.adapter.externalapi.rapid.client;

import com.kickoff.service.common.dto.RapidApiResponse;
import com.kickoff.service.team.adapter.externalapi.rapid.dto.teams.TeamsResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.Year;
import java.util.List;
import java.util.Objects;

@RequiredArgsConstructor
@Component
public class TeamExternalApiClient {

  private final WebClient webClient;

  public List<TeamsResponse> requestTeams(Long apiFootballLeagueId, Year year) {
    ParameterizedTypeReference<RapidApiResponse<TeamsResponse>> responseType = new ParameterizedTypeReference<>() {};

    return Objects.requireNonNull(
      webClient.get()
        .uri(String.format("/teams?league=%d&season=%s", apiFootballLeagueId, year))
        .retrieve()
        .bodyToMono(responseType)
        .block()
    ).getResponse();
  }
}
