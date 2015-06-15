package br.com.luizalabsDesafio.domain;

import java.math.BigDecimal;
import java.util.Collection;

import org.neo4j.graphdb.Direction;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Label;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Relationship;
import org.neo4j.graphdb.RelationshipType;
import org.neo4j.graphdb.ReturnableEvaluator;
import org.neo4j.graphdb.StopEvaluator;
import org.neo4j.graphdb.Traverser;
import org.neo4j.graphdb.Traverser.Order;
import org.springframework.data.neo4j.annotation.Fetch;
import org.springframework.data.neo4j.annotation.GraphId;
import org.springframework.data.neo4j.annotation.Indexed;
import org.springframework.data.neo4j.annotation.NodeEntity;
import org.springframework.data.neo4j.annotation.RelatedToVia;

// tag::product[]
@NodeEntity
public class Product  {
    @GraphId Long id;

    @Indexed(unique=true) private Long productId;
    private String name;

    private BigDecimal price;

    public Product() { }
    
    public Product(Long productId, String name, BigDecimal price){
    	this.productId = productId;
    	this.name = name;
    	this.price = price;
    }
    
    public Long getProductId() {
        return productId;
    }


    public String getName() {
		return name;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}


}
