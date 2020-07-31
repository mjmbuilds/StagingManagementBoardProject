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
@Table(name = "mb_board")
@JsonIgnoreProperties({"user"})
public class Board implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "board_id")
	private String id;
	
	@Transient
	private String owningUserId;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "fk_user")
	private User user;
	
	@Column(name = "board_title")
	private String title;
	
	@OneToMany(mappedBy = "board", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Category> categories;
	
	public Board() {
		this.id = UUID.randomUUID().toString();
		this.user = null;
		this.title = null;
		this.categories = new ArrayList<Category>();
	}
	
	public Board(String title) {
		this.id = UUID.randomUUID().toString();
		this.user = null;
		this.title = title;
		this.categories = new ArrayList<Category>();
	}
	
	public Board(String title, User user) {
		this.id = UUID.randomUUID().toString();
		this.user = user;
		this.title = title;
		this.categories = new ArrayList<Category>();
	}
	
	public void addCategory(Category category) {
		categories.add(category);
		category.setBoard(this);
	}
	
	public void removeCategory(Category category) {
		categories.remove(category);
		category.setBoard(null);
	}//----------------------------------------------------------------

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getOwningUserId() {
		if (owningUserId == null) {
			return user.getId();
		}
		return owningUserId;
	}

	public void setOwningUserId(String owningUserId) {
		this.owningUserId = owningUserId;
	}
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
		this.owningUserId = user.getId();
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
		result = prime * result + ((user == null) ? 0 : user.hashCode());
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
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Board [id=" + id + ", owningUserId=" + owningUserId + ", title=" + title + ", categories=" + categories
				+ "]";
	}

}
