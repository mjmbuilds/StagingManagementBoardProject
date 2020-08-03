package com.staging.data;

import com.staging.model.Card;

public interface CardDao {
	
	int addCard(Card card);
	
	Card getCard(String id);
	
	int updateCard(Card card);
	
	int deleteCard(String id);

}
