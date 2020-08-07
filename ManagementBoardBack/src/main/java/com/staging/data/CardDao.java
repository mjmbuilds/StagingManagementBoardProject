package com.staging.data;

import com.staging.model.Card;
import com.staging.model.CodeMessage;
import com.staging.model.IndexList;

public interface CardDao {
	
	CodeMessage updateCardIndexList(IndexList indexList);
	
	CodeMessage addCard(Card card);
	
	Card getCard(String id);
	
	CodeMessage updateCard(Card card);
	
	CodeMessage deleteCard(String id);

}
