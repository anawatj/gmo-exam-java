package com.gmo.exam;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import com.zaxxer.hikari.HikariDataSource;

@Configuration
public class AppConfig {
	@Bean
	public JdbcTemplate jdbcTemplate(HikariDataSource hikariDataSource) {
		return new JdbcTemplate(hikariDataSource);
	}
}
