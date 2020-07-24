package com.staging.model;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.*;

@Entity
@Table(name = "mb_card")
public class Card implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "card_id")
	private UUID id;
	@Column(name = "category_id")
	private UUID categoryId;
	@Column(name = "card_title")
	private String title;
	@Column(name = "card_description")
	private String description;

	public Card() {
		this.id = UUID.randomUUID();
		this.categoryId = null;
		this.title = null;
		this.description = null;
	}

	public Card(String title, String description) {
		this.id = UUID.randomUUID();
		this.categoryId = null;
		this.title = title;
		this.description = description;
	}

	public Card(String title, String description, UUID categoryId) {
		this.id = UUID.randomUUID();
		this.categoryId = categoryId;
		this.title = title;
		this.description = description;
	}
	
	//----------------------------------------------------------------
	
	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public UUID getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(UUID categoryId) {
		this.categoryId = categoryId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((categoryId == null) ? 0 : categoryId.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
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
		Card other = (Card) obj;
		if (categoryId == null) {
			if (other.categoryId != null)
				return false;
		} else if (!categoryId.equals(other.categoryId))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
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
		return "Card [id=" + id + ", categoryId=" + categoryId + ", title=" + title + ", description=" + description
				+ "]";
	}

}
