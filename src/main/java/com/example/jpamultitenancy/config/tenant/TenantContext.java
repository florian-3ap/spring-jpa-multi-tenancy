package com.example.jpamultitenancy.config.tenant;

import java.util.Optional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class TenantContext {

  private static final ThreadLocal<String> currentTenant = new InheritableThreadLocal<>();

  public static Optional<String> getCurrentTenant() {
    return Optional.ofNullable(currentTenant.get());
  }

  public static void setCurrentTenant(String tenant) {
    currentTenant.set(tenant);
  }

  public static void clear() {
    currentTenant.remove();
  }
}
