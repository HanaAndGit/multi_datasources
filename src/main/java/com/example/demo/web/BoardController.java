package com.example.demo.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.service.BoardService;

@Controller
public class BoardController {
	
	@Autowired
	private BoardService boardService;
	
	@GetMapping("/")
	public ResponseEntity<?> home() {
		
		boardService.oracleToPostgres();
		
		return ResponseEntity.ok().build();
	}
}
