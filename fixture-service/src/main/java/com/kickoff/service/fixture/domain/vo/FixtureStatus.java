package com.kickoff.service.fixture.domain.vo;

import com.kickoff.service.common.domain.vo.BaseVo;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;

@Builder
@AllArgsConstructor @NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@EqualsAndHashCode(callSuper = false)
@Embeddable
public class FixtureStatus extends BaseVo {
  @Enumerated(EnumType.STRING)
  private FixtureStatusType fixtureStatusType;
  // 현재 경기 진행도(분). 종료시 90
  private Integer elapsed;
  // 연장 정보
  private String extra;
}
