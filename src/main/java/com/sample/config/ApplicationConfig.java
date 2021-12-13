package com.sample.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;

import brave.http.HttpTracing;
import reactor.netty.http.brave.ReactorNettyHttpTracing;
import reactor.netty.http.client.HttpClient;

@Configuration
public class ApplicationConfig {

	@Bean
	public ReactorNettyHttpTracing reactorNettyHttpTracing(HttpTracing httpTracing) {
		return ReactorNettyHttpTracing.create(httpTracing);
	}

	@Bean
	public WebClient webClient(ReactorNettyHttpTracing tracing) {

		var httpClient = HttpClient.create();

		// @formatter:off
		return WebClient.builder()
			            .clientConnector(new ReactorClientHttpConnector(tracing.decorateHttpClient(httpClient)))
			            .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
			            /*.filters(exchangeFilters -> { 
			                exchangeFilters.add(exchangeFilter.logRequest());
			                exchangeFilters.add(exchangeFilter.logResponse());
			            })*/
			            .build();
		// @formatter:on
	}

}
