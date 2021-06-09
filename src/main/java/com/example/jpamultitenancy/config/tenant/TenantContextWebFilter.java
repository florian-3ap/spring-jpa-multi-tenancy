package com.example.jpamultitenancy.config.tenant;

import java.util.Optional;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

@Component
public class TenantContextWebFilter implements WebFilter {

  @Override
  public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
    Optional.ofNullable(exchange.getRequest().getHeaders().getFirst("tenant_id"))
        .ifPresent(TenantContext::setCurrentTenant);

    return chain
        .filter(exchange)
        .doOnSuccess(unused -> TenantContext.clear())
        .doOnError(throwable -> TenantContext.clear());
  }
}
