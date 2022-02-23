package com.example.demo;


import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import lombok.extern.slf4j.Slf4j;

@Configuration
@Slf4j
public class Data1Config {
	
	@Bean
	@Qualifier("data1")
	@ConfigurationProperties(prefix = "spring.datasource.hikari.data1")
	public DataSource datasource1() {
		return DataSourceBuilder.create().build();
	}
	
	@Bean("data1JdbcOperations")
	NamedParameterJdbcOperations data1JdbcOperations(@Qualifier("data1") DataSource datasource)
			throws SQLException {
		log.info("data1JdbcOperations({})", datasource.getConnection());
		return new NamedParameterJdbcTemplate(datasource);
	}

	@Bean("data1JdbcTemplate")
	public JdbcTemplate data1Template(@Qualifier("data1") DataSource datasource) {
		return new JdbcTemplate(datasource);
	}
	
}
