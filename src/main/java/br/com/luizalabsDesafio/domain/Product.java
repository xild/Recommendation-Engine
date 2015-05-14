package br.com.luizalabsDesafio.domain;

import java.math.BigDecimal;
import java.util.Collection;

import org.neo4j.graphdb.Direction;
import org.springframework.data.neo4j.annotation.Fetch;
import org.springframework.data.neo4j.annotation.GraphId;
import org.springframework.data.neo4j.annotation.Indexed;
import org.springframework.data.neo4j.annotation.NodeEntity;
import org.springframework.data.neo4j.annotation.RelatedToVia;

// tag::product[]
@NodeEntity
public class Product {
    @GraphId Long id;

    @Indexed(unique=true) private Long productId;

    private String name;

    private BigDecimal price;
    
    @Fetch @RelatedToVia(type="VIEWED", direction = Direction.INCOMING) 
    Collection<Cart> carts;

// end::product[]

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

	public Collection<Cart> getCarts() {
        return carts;
    }
}
