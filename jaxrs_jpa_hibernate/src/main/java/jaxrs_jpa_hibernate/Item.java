package jaxrs_jpa_hibernate;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@Entity
@Table(name = "items")
@JsonPropertyOrder({"id", "content", "origin"})
public class Item {

	@Id
	private String id;
	private String content;
	private String origin;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getOrigin() {
		return origin;
	}

	public void setOrigin(String origin) {
		this.origin = origin;
	}

}
