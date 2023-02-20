package com.flight.application.config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.cloud.loadbalancer.core.ServiceInstanceListSupplier;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoadBalancerConfiguration {
	private static final Logger logger = LogManager.getLogger(LoadBalancerConfiguration.class);

	@Bean
	public ServiceInstanceListSupplier discoveryClientServiceInstanceListSupplier(
			ConfigurableApplicationContext context) {
		logger.info("Configuring Load balancer");
		return ServiceInstanceListSupplier.builder().withBlockingDiscoveryClient().withSameInstancePreference()
				.build(context);
	}
}
