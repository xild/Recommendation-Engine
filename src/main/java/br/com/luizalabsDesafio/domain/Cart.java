package br.com.luizalabsDesafio.domain;


import org.springframework.data.neo4j.annotation.*;

import java.util.Collection;

@RelationshipEntity(type = "VIEWED")
public class Cart {
    @GraphId
    Long id;
    Collection<String> cart;
    @Fetch @StartNode Person person;
    @Fetch @EndNode   Product product;

    public Collection<String> getCart() {
        return cart;
    }

    public Person getPerson() {
        return person;
    }

    public Product getProduct() {
        return product;
    }
}
