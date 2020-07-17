package com.staging.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.staging.dao.BoardDao;
import com.staging.model.Board;

@Service
public class BoardService {

	private final BoardDao boardDao;
	
	@Autowired
	public BoardService(@Qualifier("fakeDao") BoardDao boardDao) {
		this.boardDao = boardDao;
	}
	
	public int addBoard(Board board) {
		return boardDao.addBoard(board);
	}
	
	public void updateBoard(Board board) {
		boardDao.updateBoard(board);
	}
	
	public void deleteBoard(UUID id) {
		boardDao.deleteBoard(id);
	}
}
