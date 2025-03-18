package com.kickoff.service.fixture.adapter.externalapi.rapid.client;

import com.kickoff.service.common.dto.RapidApiResponse;
import com.kickoff.service.fixture.adapter.externalapi.rapid.dto.fixtures.FixtureStatisticsResponse;
import com.kickoff.service.fixture.adapter.externalapi.rapid.dto.fixtures.FixturesResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.Year;
import java.util.List;
import java.util.Objects;

@RequiredArgsConstructor
@Component
public class FixtureExternalApiClient {

  private final WebClient webClient;

  public List<FixturesResponse> requestFixtures(Long apiFootballFixtureId, Year year) {
    ParameterizedTypeReference<RapidApiResponse<FixturesResponse>> responseType = new ParameterizedTypeReference<>() {};

    return Objects.requireNonNull(
      webClient.get()
        .uri(String.format("/fixtures?league=%d&season=%d", apiFootballFixtureId, year.getValue()))
        .retrieve()
        .bodyToMono(responseType)
        .block()
    ).getResponse();
  }

  public List<FixtureStatisticsResponse> requestFixturesStatistics(Long apiFootballFixtureId) {
    ParameterizedTypeReference<RapidApiResponse<FixtureStatisticsResponse>> responseType = new ParameterizedTypeReference<>() {};

    return Objects.requireNonNull(
      webClient.get()
        .uri(String.format("/fixtures/statistics?fixture=%d", apiFootballFixtureId))
        .retrieve()
        .bodyToMono(responseType)
        .block()
    ).getResponse();
  }
}
