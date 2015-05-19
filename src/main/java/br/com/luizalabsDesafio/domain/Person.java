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

//    @Fetch @RelatedToVia(type="VIEWED", direction=Direction.BOTH)
//    @JsonProperty("VIEWED")
//    private Set<MyRelationship> relationShipViewed;
//	
//
//    @Fetch @RelatedToVia(type="BOUGHT", direction=Direction.BOTH)
//    @JsonProperty("BOUGHT")
//    private Set<MyRelationship> relationShipBought;
//    
//	@Fetch @RelatedToVia(type="ADD_TO_CART",direction=Direction.BOTH)
//	@JsonProperty("ADD-TO-CART")
//    private Set<MyRelationship> relationShipAddToCart;
    

//	@Query(value = "MATCH (person {personId: {personId}})-[r:VIEWED|BOUGHT|ADD_TO_CART]->() RETURN person, r order by r.time limit 3",
//            params = {"personId"})
////    $MATCH (person {personId: 1})-[r:VIEWED|BOUGHT|ADD_TO_CART]->() RETURN person, r order by r.time limit 3
//    private List<MyRelationship> friends;
    
	public Person() { }

    

	public Person(Long personId, String name, String email) {
		super();
		this.personId = personId;
		this.name = name;
		this.email = email;
	}

	

    
//    public Set<MyRelationship> getRelationShipViewed() {
//		return relationShipViewed;
//	}
//
//
//
//	public Set<MyRelationship> getRelationShipBought() {
//		return relationShipBought;
//	}
//
//
//
//	public Set<MyRelationship> getRelationShipAddToCart() {
//		return relationShipAddToCart;
//	}


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
