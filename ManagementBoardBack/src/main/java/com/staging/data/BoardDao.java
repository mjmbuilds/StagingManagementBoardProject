package com.staging.data;

import java.util.UUID;

import com.staging.model.Board;

public interface BoardDao {
	
	int addBoard(Board board);
	
	int updateBoard(Board board);
	
	int deleteBoard(UUID id);
	
}
