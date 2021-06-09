package com.example.jpamultitenancy.domain;

import java.time.Instant;
import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@Setter
@ToString
@MappedSuperclass
@RequiredArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class BaseEntity {

  @Column(name = "created_at", nullable = false, updatable = false)
  @CreatedDate
  Instant createdAt;

  @Column(name = "updated_at", nullable = false)
  @LastModifiedDate
  Instant updatedAt;
}
