package br.com.luizalabsDesafio.domain;

import org.springframework.data.neo4j.annotation.GraphId;
import org.springframework.data.neo4j.annotation.Indexed;
import org.springframework.data.neo4j.annotation.NodeEntity;
import org.springframework.data.neo4j.annotation.RelatedTo;

import java.util.Collection;

@NodeEntity
public class Person {
    @GraphId private Long personId;

    @Indexed private String name;
    private String email;

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

	@RelatedTo(type = "ACTED_IN")
    Collection<Product> movies;

    public Person() { }

  

	public Person(Long personId, String name, String email) {
		super();
		this.personId = personId;
		this.name = name;
		this.email = email;
	}

	public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public Collection<Product> getMovies() {
        return movies;
    }
}
