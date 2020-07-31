package com.staging.data;

import java.util.UUID;

import com.staging.model.Card;

public interface CardDao {
	
	int addCard(Card card);
	
	int updateCard(Card card);
	
	int deleteCard(String id);

}
