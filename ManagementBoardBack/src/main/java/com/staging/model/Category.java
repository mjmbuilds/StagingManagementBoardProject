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
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "fk_board")
	private Board board;
	
	@Column(name = "category_title")
	private String title;
	
	@OneToMany(mappedBy = "category", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Card> cards;
	
	public Category() {
		this.id = UUID.randomUUID();
		this.board = null;
		this.title = null;
		this.cards = null;
	}

	public Category(String title) {
		this.id = UUID.randomUUID();
		this.board = null;
		this.title = title;
		this.cards = null;
	}
	
	public Category(String title, Board board) {
		this.id = UUID.randomUUID();
		this.board = board;
		this.title = title;
		this.cards = null;
	}//----------------------------------------------------------------

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public Board getBoard() {
		return board;
	}

	public void setBoard(Board board) {
		this.board = board;
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
		return "Category [id=" + id + ", board=" + board + ", title=" + title + ", cards=" + cards + "]";
	}

}
