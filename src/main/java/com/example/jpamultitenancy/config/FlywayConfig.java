package com.example.jpamultitenancy.config;

import com.example.jpamultitenancy.config.tenant.TenantProperties;
import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import lombok.RequiredArgsConstructor;
import org.flywaydb.core.Flyway;
import org.springframework.boot.autoconfigure.flyway.FlywayProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
@EnableConfigurationProperties(FlywayProperties.class)
public class FlywayConfig {

  private final DataSource dataSource;
  private final TenantProperties tenantProperties;
  private final FlywayProperties flywayProperties;

  @PostConstruct
  public void migrateFlyway() {
    tenantProperties.getNames().forEach(tenant -> flyway(tenant).migrate());
  }

  public Flyway flyway(String tenant) {
    final var schemaName = tenant.toLowerCase();
    return new Flyway(
        Flyway.configure()
            .baselineVersion(flywayProperties.getBaselineVersion())
            .baselineOnMigrate(flywayProperties.isBaselineOnMigrate())
            .schemas(schemaName)
            .defaultSchema(schemaName)
            .dataSource(dataSource));
  }
}
