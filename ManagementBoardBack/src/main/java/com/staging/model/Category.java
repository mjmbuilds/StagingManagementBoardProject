package com.staging.model;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

import javax.persistence.*;

@Entity
@Table(name = "mb_category")
public class Category implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "category_id")
	private UUID id;
	@Column(name = "board_id")
	private UUID boardId;
	@Column(name = "category_title")
	private String title;
	//@OneToMany(cascade = CascadeType.ALL, mappedBy = "card_id")
	@OneToMany
	@JoinColumn(name="card_id")
	private List<Card> cards;
	
	public Category() {
		this.id = UUID.randomUUID();
		this.boardId = null;
		this.title = null;
		this.cards = null;
	}

	public Category(String title) {
		this.id = UUID.randomUUID();
		this.boardId = null;
		this.title = title;
		this.cards = null;
	}
	
	public Category(String title, UUID boardId) {
		this.id = UUID.randomUUID();
		this.boardId = boardId;
		this.title = title;
		this.cards = null;
	}

	//----------------------------------------------------------------
	
	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public UUID getBoardId() {
		return boardId;
	}

	public void setBoardId(UUID boardId) {
		this.boardId = boardId;
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((boardId == null) ? 0 : boardId.hashCode());
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
		if (boardId == null) {
			if (other.boardId != null)
				return false;
		} else if (!boardId.equals(other.boardId))
			return false;
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
		return "Category [id=" + id + ", boardId=" + boardId + ", title=" + title + ", cards=" + cards + "]";
	}

}
