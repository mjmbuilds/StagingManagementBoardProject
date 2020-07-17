package com.staging.model;

import java.util.List;
import java.util.UUID;

public class Category {

	private UUID id;
	private String title;
	private List<Card> cards;
	
	public Category() {
		this.id = UUID.randomUUID();
		this.title = null;
		this.cards = null;
	}

	public Category(String title) {
		this.id = UUID.randomUUID();
		this.title = title;
		this.cards = null;
	}
	
	public Category(String title, List<Card> cards) {
		this.id = UUID.randomUUID();
		this.title = title;
		this.cards = cards;
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<Card> getCards() {
		return cards;
	}

	public void setCards(List<Card> cards) {
		this.cards = cards;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cards == null) ? 0 : cards.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Category other = (Category) obj;
		if (cards == null) {
			if (other.cards != null)
				return false;
		} else if (!cards.equals(other.cards))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Category [id=" + id + ", title=" + title + ", cards=" + cards + "]";
	}

}
