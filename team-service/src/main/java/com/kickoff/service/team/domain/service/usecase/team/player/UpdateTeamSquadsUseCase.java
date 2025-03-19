package com.kickoff.service.team.domain.service.usecase.team.player;

import com.kickoff.service.team.domain.entity.Player;
import com.kickoff.service.team.domain.entity.Team;
import com.kickoff.service.team.domain.port.output.externalapi.PlayerExternalApiService;
import com.kickoff.service.team.domain.port.output.repository.PlayerRepository;
import com.kickoff.service.team.domain.port.output.repository.TeamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class UpdateTeamSquadsUseCase {

  private final PlayerExternalApiService playerExternalApiService;
  private final TeamRepository teamRepository;
  private final PlayerRepository playerRepository;

  public void updateOrPersistSquads(Long apiFootballTeamId) {
    Team team = teamRepository.findByApiFootballTeamId(apiFootballTeamId).orElseThrow();
    playerExternalApiService.getSquads(apiFootballTeamId)
      .forEach(player -> updateOrAddTeamSquad(team, player));
  }

  private void updateOrAddTeamSquad(Team team, Player player) {
    if (team == null || player == null) return;

    playerRepository.findByApiFootballPlayerId(player.getApiFootballPlayerId())
      .ifPresentOrElse(
        containsPlayer -> {
          containsPlayer.setName(player.getName());
          containsPlayer.setAge(player.getAge());
          containsPlayer.setNumber(player.getNumber());
          containsPlayer.setPosition(player.getPosition());
          player.getPhoto().forEach(containsPlayer::addPhoto);
        },
        () -> team.addTeamSquad(player)
      );
  }
}
