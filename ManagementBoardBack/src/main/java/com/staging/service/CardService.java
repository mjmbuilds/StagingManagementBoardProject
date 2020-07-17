package com.staging.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.staging.dao.CardDao;
import com.staging.model.Card;

@Service
public class CardService {

	private final CardDao cardDao;
	
	@Autowired
	public CardService(@Qualifier("fakeDao") CardDao cardDao) {
		this.cardDao = cardDao;
	}
	
	public int addCard(Card card) {
		return cardDao.addCard(card);
	}
	
	public void updateCard(Card card) {
		cardDao.updateCard(card);
	}
	
	public void deleteCard(UUID id) {
		cardDao.deleteCard(id);
	}
}
