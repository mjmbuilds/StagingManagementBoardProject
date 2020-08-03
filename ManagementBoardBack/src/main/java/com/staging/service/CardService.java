package com.staging.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.staging.data.CardDao;
import com.staging.model.Card;

@Service
public class CardService extends GenericService {

	private final Logger log = LoggerFactory.getLogger(this.getClass());
	private final CardDao cardDao;
	
	@Autowired // daoQualifier is specified in GenericService
	public CardService(@Qualifier(daoQualifier) CardDao cardDao) {
		this.cardDao = cardDao;
	}
	
	public int addCard(Card card) {
		log.trace("addCard()");
		return cardDao.addCard(card);
	}
	
	public Card getCard(String id) {
		log.trace("getCard()");
		return cardDao.getCard(id);
	}
	
	public int updateCard(Card card) {
		log.trace("updateCard()");
		return cardDao.updateCard(card);
	}
	
	public int deleteCard(String id) {
		log.trace("deleteCard()");
		return cardDao.deleteCard(id);
	}
}
