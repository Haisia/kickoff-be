package com.kickoff.service.fixture.domain.vo;

import lombok.Getter;

@Getter
public enum FixtureProgressStatus {
  UNKNOWN("unknown", "알 수 없음"),
  SCHEDULED("Scheduled", "경기 일정이 잡힘"),
  IN_PLAY("In Play", "경기 진행 중"),
  FINISHED("Finished", "경기 종료"),
  POSTPONED("Postponed", "경기 연기"),
  CANCELLED("Cancelled", "경기 취소"),
  ABANDONED("Abandoned", "경기 중단"),
  NOT_PLAYED("Not Played", "경기 진행 안 됨"),
  ;

  public final String status;
  public final String description;

  FixtureProgressStatus(String status, String description) {
    this.status = status;
    this.description = description;
  }
}