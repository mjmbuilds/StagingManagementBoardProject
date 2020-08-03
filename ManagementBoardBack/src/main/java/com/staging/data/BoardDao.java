package com.staging.data;

import com.staging.model.Board;

public interface BoardDao {
	
	int addBoard(Board board);
	
	Board getBoard(String id);
	
	int updateBoard(Board board);
	
	int deleteBoard(String id);
	
}
