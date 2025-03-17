package com.kickoff.service.fixture.domain.vo;

import lombok.Getter;

import java.util.Arrays;

@Getter
public enum FixtureStatusType {
  UNKNOWN("unknown", "Unknown", FixtureProgressStatus.UNKNOWN, "알 수 없는 상태"),
  TBD("tbd", "Time To Be Defined", FixtureProgressStatus.SCHEDULED, "일정이 정해졌지만 날짜와 시간이 아직 확정되지 않음"),
  NS("ns", "Not Started", FixtureProgressStatus.SCHEDULED, "예정된 경기이지만 아직 시작되지 않음"),
  H1("1h", "First Half, Kick Off", FixtureProgressStatus.IN_PLAY, "전반전 진행 중"),
  HT("ht", "Halftime", FixtureProgressStatus.IN_PLAY, "전반전 종료, 후반전을 대기 중"),
  H2("2h", "Second Half, 2nd Half Started", FixtureProgressStatus.IN_PLAY, "후반전 진행 중"),
  ET("et", "Extra Time", FixtureProgressStatus.IN_PLAY, "연장전 진행 중"),
  BT("bt", "Break Time", FixtureProgressStatus.IN_PLAY, "연장전 휴식 시간"),
  P("p", "Penalty In Progress", FixtureProgressStatus.IN_PLAY, "승부차기 진행 중"),
  SUSP("susp", "Match Suspended", FixtureProgressStatus.IN_PLAY, "심판의 결정으로 경기 중단, 다른 날로 재조정될 수 있음"),
  INT("int", "Match Interrupted", FixtureProgressStatus.IN_PLAY, "심판의 결정으로 경기 일시 중단, 몇 분 내에 재개될 예정"),
  FT("ft", "Match Finished", FixtureProgressStatus.FINISHED, "정규 시간 내 경기가 종료됨"),
  AET("aet", "Match Finished After Extra Time", FixtureProgressStatus.FINISHED, "연장전 후 경기가 종료됨 (승부차기 없이)"),
  PEN("pen", "Match Finished After Penalty Shootout", FixtureProgressStatus.FINISHED, "승부차기 후 경기가 종료됨"),
  PST("pst", "Match Postponed", FixtureProgressStatus.POSTPONED, "경기가 다른 날로 연기됨, 날짜와 시간이 확정되면 상태가 변경됨"),
  CANC("canc", "Match Cancelled", FixtureProgressStatus.CANCELLED, "경기가 취소되었으며 진행되지 않음"),
  ABD("abd", "Match Abandoned", FixtureProgressStatus.ABANDONED, "날씨나 안전 등의 이유로 경기 중단, 다시 일정이 잡힐 수도 있고 아닐 수도 있음"),
  AWD("awd", "Technical Loss", FixtureProgressStatus.NOT_PLAYED, "경기가 몰수패로 간주되며 진행되지 않음"),
  WO("wo", "Walk Over", FixtureProgressStatus.NOT_PLAYED, "부전승 또는 상대의 부재로 인한 승리"),
  LIVE("live", "Live Match, In Progress", FixtureProgressStatus.IN_PLAY, "현재 진행 중인 경기이지만 시간이나 단계와 같은 세부사항은 알 수 없음");

  public final String code;
  public final String name;
  public final FixtureProgressStatus progressStatus;
  public final String description;

  FixtureStatusType(String code, String name, FixtureProgressStatus progressStatus, String description) {
    this.code = code;
    this.name = name;
    this.progressStatus = progressStatus;
    this.description = description;
  }

  public boolean isInPlay() {
    return this.progressStatus == FixtureProgressStatus.IN_PLAY;
  }

  public boolean isFinished() {
    return this.progressStatus == FixtureProgressStatus.FINISHED;
  }

  public static FixtureStatusType parseCodeIgnoreCase(String code) {
    if (code == null) {
      return FixtureStatusType.UNKNOWN;
    }
    return Arrays.stream(FixtureStatusType.values())
      .filter(name -> name.getCode().equalsIgnoreCase(code))
      .findFirst()
      .orElse(FixtureStatusType.UNKNOWN);
  }
}