package com.staging.model;

import java.util.List;
import java.util.UUID;

//import com.fasterxml.jackson.annotation.JsonCreator;
//import com.fasterxml.jackson.annotation.JsonProperty;

public class User {
	
	private UUID id;
	private String firstName;
	private String lastName;
	private String username;
	private String password;
	private List<Board> boards;

	public User() {
		this.id = UUID.randomUUID();
		this.firstName = null;
		this.lastName = null;
		this.username = null;
		this.password = null;
		this.boards = null;
	}
	
	public User(String firstName, String lastName, String username, String password) {
		this.id = UUID.randomUUID();
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
		this.password = password;
		this.boards = null;
	}
	/* 
	// JsonCreator and JsonProperty not actually needed??? maybe only if property names differ?
	@JsonCreator
	public User(
			@JsonProperty("firstName") String firstName, 
			@JsonProperty("lastName")String lastName, 
			@JsonProperty("username")String username, 
			@JsonProperty("password") String password
			) {
		this.id = UUID.randomUUID();
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
		this.password = password;
	}
	*/
	
	/**
	 * Generates a new unique id
	 */
	public void generateId() {
		this.id = UUID.randomUUID();
	}
	//----------------------------------------------------------------

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Board> getBoards() {
		return boards;
	}

	public void setBoards(List<Board> boards) {
		this.boards = boards;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((boards == null) ? 0 : boards.hashCode());
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((username == null) ? 0 : username.hashCode());
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
		User other = (User) obj;
		if (boards == null) {
			if (other.boards != null)
				return false;
		} else if (!boards.equals(other.boards))
			return false;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", username=" + username
				+ ", password=" + password + ", boards=" + boards + "]";
	}
	
}