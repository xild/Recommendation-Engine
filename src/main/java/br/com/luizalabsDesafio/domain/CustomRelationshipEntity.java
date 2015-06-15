package br.com.luizalabsDesafio.domain;

import java.util.Date;

import org.springframework.data.neo4j.annotation.QueryResult;
import org.springframework.data.neo4j.annotation.ResultColumn;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

@QueryResult
public class CustomRelationshipEntity {
	
	@ResultColumn("personId")
	private Long personId;
	
	@JsonProperty("action")
	@ResultColumn("type")
	private String type;

	@ResultColumn("product")
	private Product product;


	@ResultColumn("time")
	@JsonIgnore
	private Date time;

	public Long getPersonId() {
		return personId;
	}

	public void setPersonId(Long personId) {
		this.personId = personId;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}
	
	
}
