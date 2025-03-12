package com.kickoff.service.team.container;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaAuditing
@EnableJpaRepositories(basePackages = {"com.kickoff.service.team.adapter.dataaccess.repository"})
@EntityScan(basePackages = {"com.kickoff.service.team.domain.entity"})
@SpringBootApplication(scanBasePackages = "com.kickoff.service")
public class TeamApplication {
  public static void main(String[] args) {
    SpringApplication.run(TeamApplication.class, args);
  }
}
