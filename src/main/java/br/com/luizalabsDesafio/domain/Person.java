package br.com.luizalabsDesafio.domain;

import org.springframework.data.neo4j.annotation.GraphId;
import org.springframework.data.neo4j.annotation.Indexed;
import org.springframework.data.neo4j.annotation.NodeEntity;
import org.springframework.data.neo4j.annotation.RelatedTo;

import java.util.Collection;

@NodeEntity
public class Person {
    @GraphId private Long id;

    @Indexed private String name;
    private String email;

    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

  

	public Person(Long id, String name, String email) {
		super();
		this.id = id;
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
