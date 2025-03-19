package com.kickoff.service.team.domain.port.output.externalapi;

import com.kickoff.service.team.domain.entity.Player;

import java.util.List;

public interface PlayerExternalApiService {
  List<Player> getSquads(Long apiFootballTeamId);
}
