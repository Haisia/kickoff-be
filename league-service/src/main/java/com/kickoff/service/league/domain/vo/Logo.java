package com.kickoff.service.league.domain.vo;

import com.kickoff.service.common.domain.vo.BaseVo;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;

import java.util.Objects;

@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Embeddable
public class Logo extends BaseVo {
  private String url;
  @Enumerated(EnumType.STRING)
  private UrlType urlType;

  @Override
  public boolean equals(Object o) {
    if (o == null || getClass() != o.getClass()) return false;
    Logo logo = (Logo) o;
    return Objects.equals(url, logo.url);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(url);
  }
}
