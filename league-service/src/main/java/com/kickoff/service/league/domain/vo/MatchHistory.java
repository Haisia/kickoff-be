package com.kickoff.service.league.domain.vo;

import com.kickoff.service.common.domain.vo.BaseVo;
import jakarta.persistence.Embeddable;
import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@EqualsAndHashCode(callSuper = false)
@Embeddable
public class MatchHistory extends BaseVo {
  private Integer played;
  private Integer win;
  private Integer draw;
  private Integer lose;
  private Integer goalsFor;
  private Integer goalsAgainst;
}
