package com.staging.data;

import com.staging.model.Board;
import com.staging.model.CodeMessage;

public interface BoardDao {
	
	CodeMessage addBoard(Board board);
	
	Board getBoard(String id);
	
	CodeMessage updateBoard(Board board);
	
	CodeMessage deleteBoard(String id);
	
}
