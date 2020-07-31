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
public class BoardService extends GenericService {

	private final Logger log = LoggerFactory.getLogger(this.getClass());
	private final BoardDao boardDao;
	
	@Autowired // daoQualifier is specified in GenericService
	public BoardService(@Qualifier(daoQualifier) BoardDao boardDao) {
		this.boardDao = boardDao;
	}
	
	public int addBoard(Board board) {
		log.trace("addBoard()");
		return boardDao.addBoard(board);
	}
	
	public int updateBoard(Board board) {
		log.trace("updateBoard()");
		return boardDao.updateBoard(board);
	}
	
	public int deleteBoard(String id) {
		log.trace("deleteBoard()");
		return boardDao.deleteBoard(id);
	}
}
