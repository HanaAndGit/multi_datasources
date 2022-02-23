package com.example.demo.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import com.example.demo.content.Board;
import com.example.demo.content.BoardRepository;

@Service
public class BoardService {
	
	@Autowired
	private BoardRepository repository;
	
	@Autowired
	@Qualifier("data1JdbcTemplate")
	private JdbcTemplate oracle;
	
	public void oracleToPostgres() {
		String query = "select * from MULTI_DATASOURCES.board";
		
		List<Board> boards = oracle.query(query, new RowMapper<Board>() {

			@Override
			public Board mapRow(ResultSet rs, int rowNum) throws SQLException, DataAccessException {
				return Board.builder()
					.writer(rs.getString("WRITER"))
					.title(rs.getString("TITLE"))
					.content(rs.getString("CONTENT"))
					.createdDate(rs.getDate("CREATED_DATE"))
					.build();
			}

		});
		
		boards.stream()
			.forEach(b -> {
				repository.save(b);
			});
		
	}
}
