package com.staging.data;

import java.util.UUID;

import com.staging.model.Board;

public interface BoardDao {
	
	int addBoard(Board board);
	
	void updateBoard(Board board);
	
	void deleteBoard(UUID id);
	
}
