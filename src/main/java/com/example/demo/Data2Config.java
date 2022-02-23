package com.example.demo;


import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jdbc.repository.config.EnableJdbcRepositories;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import lombok.extern.slf4j.Slf4j;

@Configuration
@Slf4j
@EnableJdbcRepositories(basePackages = { "com.example.demo.content" }, jdbcOperationsRef = "data2JdbcOperations")
@ComponentScan("com.example.demo.service")
public class Data2Config {
	
	@Bean
	@Primary
	@Qualifier("data2")
	@ConfigurationProperties(prefix = "spring.data2.hikari")
	public DataSource datasource2() {
		return DataSourceBuilder.create().build();
	}
	
	@Primary
	@Bean("data2JdbcOperations")
	NamedParameterJdbcOperations data2JdbcOperations(@Qualifier("data2") DataSource datasource)
			throws SQLException {
		log.info("data2JdbcOperations({})", datasource.getConnection());
		return new NamedParameterJdbcTemplate(datasource);
	}

	@Primary
	@Bean("data2JdbcTemplate")
	public JdbcTemplate data2Template(@Qualifier("data2") DataSource datasource) {
		return new JdbcTemplate(datasource);
	}
	
	
}
