package br.com.luizalabsDesafio.domain;

import java.util.Date;

import org.springframework.data.neo4j.annotation.EndNode;
import org.springframework.data.neo4j.annotation.GraphId;
import org.springframework.data.neo4j.annotation.RelationshipEntity;
import org.springframework.data.neo4j.annotation.StartNode;

@RelationshipEntity
public class MyRelationship {
	/**just jpa*/
	public MyRelationship(){
		
	}
	
	public MyRelationship(Person person, Product product, Date time) {
		super();
		this.person = person;
		this.product = product;
		this.time = time;
	}
	@GraphId private Long id;
	@StartNode Person person;
    @EndNode Product product;
    
    private Date time;
    
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	
    
    
}
