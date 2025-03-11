package com.kickoff.service.league.domain.vo;

import com.kickoff.service.common.domain.vo.BaseVo;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;
import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@EqualsAndHashCode(callSuper = false)
@Embeddable
public class Country extends BaseVo {
  @Column(name = "country_name")
  private String name;
  @Column(name = "country_code")
  private String code;
  @Embedded
  private Flag flag;
}
