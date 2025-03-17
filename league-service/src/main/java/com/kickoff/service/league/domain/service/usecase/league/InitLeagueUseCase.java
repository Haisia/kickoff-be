package com.kickoff.service.league.domain.service.usecase.league;

import com.kickoff.service.league.domain.entity.League;
import com.kickoff.service.league.domain.entity.Season;
import com.kickoff.service.league.domain.port.output.externalapi.LeagueExternalApiService;
import com.kickoff.service.league.domain.port.output.repository.LeagueRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
public class InitLeagueUseCase {

  private final LeagueRepository leagueRepository;
  private final LeagueExternalApiService leagueExternalApiService;

  public void initLeague() {
    List<League> allLeagues = leagueExternalApiService.getAllLeagues();

    for (League league : allLeagues) {
      Optional<League> OptionalFoundLeague = leagueRepository.findByApiFootballLeagueId(league.getApiFootballLeagueId());
      if (OptionalFoundLeague.isPresent()) {
        League foundLeague = OptionalFoundLeague.get();
        for (Season season : league.getSeasons()) {
          boolean alreadyExist = foundLeague.getSeasons()
            .stream()
            .anyMatch(foundSeason -> foundSeason.getId().getYear().equals(season.getId().getYear()));
          if (!alreadyExist) {
            foundLeague.addSeason(season.getId().getYear(), season.getStartDate(), season.getEndDate());
          }
        }
      } else {
        boolean hasDuplicateSeason = league.getSeasons().stream()
          .map(Season::getId)
          .collect(Collectors.toSet())
          .size() < league.getSeasons().size();

        if (hasDuplicateSeason) continue;
        leagueRepository.save(league);
      }
    }
  }
}
