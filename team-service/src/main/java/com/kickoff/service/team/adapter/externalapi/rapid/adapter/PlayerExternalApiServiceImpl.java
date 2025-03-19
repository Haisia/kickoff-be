package com.kickoff.service.team.adapter.externalapi.rapid.adapter;

import com.kickoff.service.team.adapter.externalapi.rapid.client.PlayerExternalApiClient;
import com.kickoff.service.team.adapter.externalapi.rapid.dto.players.SquadsResponse;
import com.kickoff.service.team.domain.entity.Player;
import com.kickoff.service.team.domain.port.output.externalapi.PlayerExternalApiService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class PlayerExternalApiServiceImpl implements PlayerExternalApiService {

  private final PlayerExternalApiClient playerExternalApiClient;

  @Override
  public List<Player> getSquads(Long apiFootballTeamId) {
    List<SquadsResponse> squadsResponses = playerExternalApiClient.requestPlayersSquads(apiFootballTeamId);
    if (squadsResponses.isEmpty()) return List.of();
    return squadsResponses
      .getFirst()
      .toPlayers();
  }
}
