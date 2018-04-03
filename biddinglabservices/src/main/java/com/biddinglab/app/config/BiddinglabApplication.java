package com.biddinglab.app.config;

import java.util.Arrays;

import org.apache.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = { "com.notify.app.config", "com.notify.app.controller", "com.notify.app.model",
		"com.notify.app.repo", "com.notify.app.services", "com.notify.app.util",
		"com.notify.app.validator" })
@EnableAutoConfiguration
@EnableJpaRepositories(basePackages = { "com.notify.app.repo" })
@EntityScan(basePackages = { "com.notify.app.model" })
@EnableCaching
public class BiddinglabApplication extends SpringBootServletInitializer {

	private static Logger CONFIG_LOGGEER = Logger.getLogger(BiddinglabApplication.class);

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		CONFIG_LOGGEER.info("Spring application is configuring");
		return builder.sources(BiddinglabApplication.class);
	}

	public static void main(String[] args) {
		CONFIG_LOGGEER.info("Spring application is runnng");
		SpringApplication.run(BiddinglabApplication.class, args);
	}

	@Bean
	public CacheManager cacheManager() {
		SimpleCacheManager cacheManager = new SimpleCacheManager();
		cacheManager.setCaches(Arrays.asList(new ConcurrentMapCache("expmgrwebcache")));
		return cacheManager;
	}
}
