package com.example.demo.content;

import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Table
@Getter
@Setter
@Builder
public class Board {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String writer;
	
	private String title;
	
	private String content;
	
	private Date createdDate;
	
}	
