package com.staging.data;

import com.staging.model.Card;
import com.staging.model.CodeMessage;

public interface CardDao {
	
	CodeMessage addCard(Card card);
	
	Card getCard(String id);
	
	CodeMessage updateCard(Card card);
	
	CodeMessage deleteCard(String id);

}
