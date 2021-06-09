package com.example.jpamultitenancy.config.tenant;

import org.hibernate.context.spi.CurrentTenantIdentifierResolver;
import org.springframework.stereotype.Component;

@Component
public class TenantSchemaResolver implements CurrentTenantIdentifierResolver {

  private static final String DEFAULT_SCHEMA = "public";

  @Override
  public String resolveCurrentTenantIdentifier() {
    return TenantContext.getCurrentTenant().map(String::toLowerCase).orElse(DEFAULT_SCHEMA);
  }

  @Override
  public boolean validateExistingCurrentSessions() {
    return true;
  }
}
