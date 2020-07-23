package com.staging.service;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.staging.data.BoardDao;
import com.staging.model.Board;

@Service
public class BoardService {

	private final Logger log = LoggerFactory.getLogger(this.getClass());
	private final BoardDao boardDao;
	
	@Autowired
	public BoardService(@Qualifier("fakeDao") BoardDao boardDao) {
		this.boardDao = boardDao;
	}
	
	public int addBoard(Board board) {
		log.trace("addBoard()");
		return boardDao.addBoard(board);
	}
	
	public void updateBoard(Board board) {
		log.trace("updateBoard()");
		boardDao.updateBoard(board);
	}
	
	public void deleteBoard(UUID id) {
		log.trace("deleteBoard()");
		boardDao.deleteBoard(id);
	}
}
