package com.kickoff.service.fixture.container;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableJpaAuditing
@EnableJpaRepositories(basePackages = {"com.kickoff.service.fixture.adapter.dataaccess.repository"})
@EntityScan(basePackages = {"com.kickoff.service.fixture.domain.entity"})
@EnableScheduling
@SpringBootApplication(scanBasePackages = "com.kickoff.service")
public class FixtureApplication {
  public static void main(String[] args) {
    SpringApplication.run(FixtureApplication.class, args);
  }
}
