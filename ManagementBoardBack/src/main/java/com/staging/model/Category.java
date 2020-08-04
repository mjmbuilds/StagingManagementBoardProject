package com.staging.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "mb_category")
@JsonIgnoreProperties({"board"})
public class Category implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "category_id")
	private String id;
	
	//@Column(name = "fk_board")
	@Transient
	private String owningBoardId;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "fk_board")
	private Board board;
	
	@Column(name = "category_title")
	private String title;
	
	@OneToMany(mappedBy = "category", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Card> cards;
	
	public Category() {
		this.id = UUID.randomUUID().toString();
		this.board = null;
		this.title = null;
		this.cards = new ArrayList<Card>();
	}

	public Category(String title) {
		this.id = UUID.randomUUID().toString();
		this.board = null;
		this.title = title;
		this.cards = new ArrayList<Card>();
	}
	
	public Category(String title, Board board) {
		this.id = UUID.randomUUID().toString();
		this.board = board;
		this.title = title;
		this.cards = new ArrayList<Card>();
	}
	
	public void addCard(Card card) {
		cards.add(card);
		card.setCategory(this);
	}
	
	public void removeCard(Card card) {
		cards.remove(card);
		card.setCategory(null);
	}//----------------------------------------------------------------

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getOwningBoardId() {
		if (owningBoardId == null) {
			return board.getId();
		}
		return owningBoardId;
	}

	public void setOwningBoardId(String owningBoardId) {
		this.owningBoardId = owningBoardId;
	}

	public Board getBoard() {
		return board;
	}

	public void setBoard(Board board) {
		this.board = board;
		this.owningBoardId = board.getId();
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
		result = prime * result + ((board == null) ? 0 : board.hashCode());
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
		if (board == null) {
			if (other.board != null)
				return false;
		} else if (!board.equals(other.board))
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
		return "Category [id=" + id + ", owningBoardId=" + owningBoardId + ", title=" + title + ", cards=" + cards
				+ "]";
	}

}
