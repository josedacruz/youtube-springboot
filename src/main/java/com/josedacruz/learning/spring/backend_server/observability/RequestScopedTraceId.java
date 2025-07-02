package com.josedacruz.learning.spring.backend_server.observability;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;
import java.util.UUID;

@Component
@Scope(value = WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class RequestScopedTraceId {
    private final String traceId = UUID.randomUUID().toString();

    public String getTraceId() {
        System.out.println("RequestScopedTraceId.getTraceId() - traceId: " + traceId);
        return traceId;
    }
}

