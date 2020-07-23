package com.staging.model;

import java.util.List;
import java.util.UUID;

import javax.persistence.*;

@Entity
@Table
public class Board {

	@Id
	@Column(name = "board_id")
	private UUID id;
	@Column(name = "board_title")
	private String title;
	@OneToMany
    @JoinTable(joinColumns = @JoinColumn(name = "category_id"))
	private List<Category> categories;
	
	public Board() {
		this.id = UUID.randomUUID();
		this.title = null;
		this.categories = null;
	}
	
	public Board(String title) {
		this.id = UUID.randomUUID();
		this.title = title;
		this.categories = null;
	}
	
	public Board(String title, List<Category> categories) {
		this.id = UUID.randomUUID();
		this.title = title;
		this.categories = categories;
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

	public List<Category> getCategories() {
		return categories;
	}

	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((categories == null) ? 0 : categories.hashCode());
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
		Board other = (Board) obj;
		if (categories == null) {
			if (other.categories != null)
				return false;
		} else if (!categories.equals(other.categories))
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
		return "Board [id=" + id + ", title=" + title + ", categories=" + categories + "]";
	}
	
}
