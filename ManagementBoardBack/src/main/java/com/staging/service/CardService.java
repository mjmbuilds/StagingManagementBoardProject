package com.staging.service;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.staging.data.CardDao;
import com.staging.model.Card;

@Service
public class CardService {

	private final Logger log = LoggerFactory.getLogger(this.getClass());
	private final CardDao cardDao;
	
	@Autowired
	public CardService(@Qualifier("fakeDao") CardDao cardDao) {
		this.cardDao = cardDao;
	}
	
	public int addCard(Card card) {
		log.trace("addCard()");
		return cardDao.addCard(card);
	}
	
	public void updateCard(Card card) {
		log.trace("updateCard()");
		cardDao.updateCard(card);
	}
	
	public void deleteCard(UUID id) {
		log.trace("deleteCard()");
		cardDao.deleteCard(id);
	}
}
