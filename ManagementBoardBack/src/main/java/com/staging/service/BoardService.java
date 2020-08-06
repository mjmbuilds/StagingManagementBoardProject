package com.staging.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.staging.data.BoardDao;
import com.staging.model.Board;
import com.staging.model.CodeMessage;

@Service
public class BoardService extends GenericService {

	private final Logger log = LoggerFactory.getLogger(this.getClass());
	private final BoardDao boardDao;
	
	@Autowired // daoQualifier is specified in GenericService
	public BoardService(@Qualifier(daoQualifier) BoardDao boardDao) {
		this.boardDao = boardDao;
	}
	
	public CodeMessage addBoard(Board board) {
		log.trace("addBoard()");
		return boardDao.addBoard(board);
	}
	
	public Board getBoard(String id) {
		log.trace("getBoard()");
		return boardDao.getBoard(id);
	}
	
	public CodeMessage updateBoard(Board board) {
		log.trace("updateBoard()");
		return boardDao.updateBoard(board);
	}
	
	public CodeMessage deleteBoard(String id) {
		log.trace("deleteBoard()");
		return boardDao.deleteBoard(id);
	}
}
