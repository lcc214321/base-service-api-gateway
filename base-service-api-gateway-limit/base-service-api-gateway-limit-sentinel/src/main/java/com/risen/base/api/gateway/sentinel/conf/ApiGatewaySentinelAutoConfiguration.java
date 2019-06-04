package com.risen.base.api.gateway.sentinel.conf;

import com.alibaba.csp.sentinel.adapter.gateway.sc.SentinelGatewayFilter;
import com.alibaba.csp.sentinel.adapter.gateway.sc.exception.SentinelGatewayBlockExceptionHandler;
import com.risen.base.api.gateway.config.ApiGatewayAutoConfiguration;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.codec.ServerCodecConfigurer;
import org.springframework.web.reactive.DispatcherHandler;
import org.springframework.web.reactive.result.view.ViewResolver;

import java.util.Collections;
import java.util.List;

/**
 * Sentinel  config
 *
 * @author mengxr
 * @since 1.0
 */
@ConditionalOnProperty(name = "spring.cloud.gateway.sentinel.enabled", matchIfMissing = true)
@Configuration
@ConditionalOnClass(DispatcherHandler.class)
@EnableConfigurationProperties({ApiGatewaySentinelProperties.class})
@ConditionalOnBean(ApiGatewayAutoConfiguration.class)
public class ApiGatewaySentinelAutoConfiguration {

    @Bean
    @Order(-1)
    public GlobalFilter sentinelGatewayFilter() {
        return new SentinelGatewayFilter();
    }

    @Bean
    @Order(Ordered.HIGHEST_PRECEDENCE)
    public SentinelGatewayBlockExceptionHandler sentinelGatewayBlockExceptionHandler(ObjectProvider<List<ViewResolver>> viewResolversProvider,
                                                                                     ServerCodecConfigurer serverCodecConfigurer) {
        // Register the block exception handler for Spring Cloud Gateway.
        List<ViewResolver> viewResolvers = viewResolversProvider.getIfAvailable(Collections::emptyList);
        return new SentinelGatewayBlockExceptionHandler(viewResolvers, serverCodecConfigurer);
    }

}
