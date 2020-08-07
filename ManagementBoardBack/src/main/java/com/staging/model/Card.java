package com.staging.model;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "mb_card")
@JsonIgnoreProperties({"category"})
public class Card implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "card_id")
	private String id;
	
	//@Column(name = "fk_category")
	@Transient
	private String owningCategoryId;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "fk_category")
	private Category category;
	
	@Column(name = "card_title")
	private String title;
	
	@Column(name = "card_description")
	private String description;

	@Column(name = "card_index")
	private Integer index;
	
	public Card() {
		this.id = UUID.randomUUID().toString();
		this.category = null;
		this.title = null;
		this.description = null;
		this.index = null;
	}

	public Card(String title, String description) {
		this.id = UUID.randomUUID().toString();
		this.category = null;
		this.title = title;
		this.description = description;
		this.index = null;
	}

	public Card(String title, String description, Category category) {
		this.id = UUID.randomUUID().toString();
		this.category = category;
		this.title = title;
		this.description = description;
		this.index = null;
	}//----------------------------------------------------------------

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getOwningCategoryId() {
		if (owningCategoryId == null) {
			return category.getId();
		}
		return owningCategoryId;
	}

	public void setOwningCategoryId(String owningCategoryId) {
		this.owningCategoryId = owningCategoryId;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
		this.owningCategoryId = category.getId();
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

	public Integer getIndex() {
		return index;
	}

	public void setIndex(Integer index) {
		this.index = index;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((category == null) ? 0 : category.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((index == null) ? 0 : index.hashCode());
		result = prime * result + ((owningCategoryId == null) ? 0 : owningCategoryId.hashCode());
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
		if (category == null) {
			if (other.category != null)
				return false;
		} else if (!category.equals(other.category))
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
		if (index == null) {
			if (other.index != null)
				return false;
		} else if (!index.equals(other.index))
			return false;
		if (owningCategoryId == null) {
			if (other.owningCategoryId != null)
				return false;
		} else if (!owningCategoryId.equals(other.owningCategoryId))
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
		return "Card [id=" + id + ", owningCategoryId=" + owningCategoryId + ", title=" + title + ", description="
				+ description + ", index=" + index + "]";
	}

}
