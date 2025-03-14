package com.kickoff.service.league.domain.vo;

import com.kickoff.service.common.domain.vo.BaseVo;
import com.kickoff.service.common.domain.vo.UrlType;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@EqualsAndHashCode(callSuper = false)
@Embeddable
public class Flag extends BaseVo {
  @Column(name = "flag_url")
  private String url;
  @Enumerated(EnumType.STRING)
  @Column(name = "flag_url_type")
  private UrlType urlType;
}