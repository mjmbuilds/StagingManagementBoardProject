package com.staging.dao;

import java.util.UUID;

import com.staging.model.Board;

public interface BoardDao {
	
	int addBoard(Board board);
	
	void updateBoard(Board board);
	
	void deleteBoard(UUID id);
	
}
