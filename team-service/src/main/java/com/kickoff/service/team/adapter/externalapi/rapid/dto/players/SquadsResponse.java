package com.kickoff.service.team.adapter.externalapi.rapid.dto.players;

import com.kickoff.service.common.domain.vo.UrlInfo;
import com.kickoff.service.common.domain.vo.UrlType;
import com.kickoff.service.team.domain.entity.Player;
import com.kickoff.service.team.domain.vo.Position;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor @AllArgsConstructor
@Data
public class SquadsResponse {

  private TeamDto team;
  private List<PlayerDto> players;

  public List<Player> toPlayers() {
    return players.stream()
      .map(PlayerDto::toPlayer)
      .toList();
  }

  @NoArgsConstructor @AllArgsConstructor
  @Data
  private static class TeamDto {
    private Long id;
    private String name;
    private String logo;
  }

  @NoArgsConstructor @AllArgsConstructor
  @Data
  private static class PlayerDto {
    private Long id;
    private String name;
    private Integer age;
    private Integer number;
    private String position;
    private String photo;

    public Player toPlayer() {
      Player createdPlayer = Player.builder()
        .apiFootballPlayerId(id)
        .name(name)
        .age(age)
        .number(number)
        .position(Position.parseIgnoreCase(position))
        .build();
      createdPlayer.addPhoto(new UrlInfo(photo, UrlType.EXTERNAL));
      return createdPlayer;
    }
  }
}
