package br.com.luizalabsDesafio.domain;

import java.util.Date;

import org.springframework.data.neo4j.annotation.EndNode;
import org.springframework.data.neo4j.annotation.Fetch;
import org.springframework.data.neo4j.annotation.GraphId;
import org.springframework.data.neo4j.annotation.RelationshipEntity;
import org.springframework.data.neo4j.annotation.RelationshipType;
import org.springframework.data.neo4j.annotation.StartNode;

@RelationshipEntity
public class ActionRelationship {

	/** just jpa */
	public ActionRelationship() {

	}

	public ActionRelationship(Person person, Product product, Date time) {
		super();
		this.person = person;
		this.product = product;
		this.time = time;
	}

	@GraphId
	private Long id;
	@StartNode
	Person person;
	@EndNode
	@Fetch
	Product product;

	@RelationshipType
	private String action;

	private Date time;

	public String getAction() {
		return action;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public Product getProduct() {
		return product;
	}

}
