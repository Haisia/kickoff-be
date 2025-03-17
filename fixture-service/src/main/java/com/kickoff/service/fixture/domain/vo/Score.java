package com.kickoff.service.fixture.domain.vo;

import com.kickoff.service.common.domain.vo.BaseVo;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@EqualsAndHashCode(callSuper = true)
@Embeddable
public class Score extends BaseVo {
  @Column(name = "home_score")
  private Integer home;
  @Column(name = "away_score")
  private Integer away;
}
