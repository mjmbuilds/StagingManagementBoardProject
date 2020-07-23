package com.staging.data;

import java.util.UUID;

import com.staging.model.Card;

public interface CardDao {
	
	int addCard(Card card);
	
	void updateCard(Card card);
	
	void deleteCard(UUID id);

}
