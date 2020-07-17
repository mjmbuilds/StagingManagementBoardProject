package com.staging.dao;

import java.util.UUID;

import com.staging.model.Card;

public interface CardDao {
	
	int addCard(Card card);
	
	void updateCard(Card card);
	
	void deleteCard(UUID id);

}
