package br.com.luizalabsDesafio.domain;

import java.util.Set;

import org.neo4j.graphdb.Direction;
import org.springframework.data.neo4j.annotation.Fetch;
import org.springframework.data.neo4j.annotation.GraphId;
import org.springframework.data.neo4j.annotation.Indexed;
import org.springframework.data.neo4j.annotation.NodeEntity;
import org.springframework.data.neo4j.annotation.RelatedToVia;

import com.fasterxml.jackson.annotation.JsonProperty;

@NodeEntity
public class Person {
	
    @GraphId private Long id;

    @Indexed(unique = true)
    private Long personId;

    private String name;
    
    private String email;

	public Person() { }

	public Person(Long personId, String name, String email) {
		super();
		this.personId = personId;
		this.name = name;
		this.email = email;
	}

	public Long getPersonId() {
		return personId;
	}

	public void setPersonId(Long personId) {
		this.personId = personId;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

}
