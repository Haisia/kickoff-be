package com.kickoff.service.fixture.adapter.externalapi.rapid.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class RapidApiClientConfig {

  @Value("${value.rapid.api.key}")
  private String RAPID_API_KEY;

  @Bean
  public WebClient customWebClient() {
    ExchangeStrategies strategies = ExchangeStrategies.builder()
      .codecs(configurer -> configurer.defaultCodecs().maxInMemorySize(10 * 1024 * 1024))
      .build();

    return WebClient.builder()
      .baseUrl("https://v3.football.api-sports.io")
      .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
      .defaultHeader("x-rapidapi-key", RAPID_API_KEY)
      .exchangeStrategies(strategies)
      .build();
  }
}
