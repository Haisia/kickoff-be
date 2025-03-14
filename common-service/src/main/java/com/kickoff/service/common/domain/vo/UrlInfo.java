package com.kickoff.service.common.domain.vo;

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
public class UrlInfo extends BaseVo {
  private String url;
  @Enumerated(EnumType.STRING)
  private UrlType urlType;

  @Override
  public boolean equals(Object o) {
    if (o == null || getClass() != o.getClass()) return false;
    UrlInfo urlInfo = (UrlInfo) o;
    return Objects.equals(url, urlInfo.url);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(url);
  }
}
