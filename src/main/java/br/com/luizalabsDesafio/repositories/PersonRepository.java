package br.com.luizalabsDesafio.repositories;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.data.repository.query.Param;

import br.com.luizalabsDesafio.domain.Product;

/**
 * @author Luis
 * @since 13.05.2015
 */

// tag::repository[]
public interface PersonRepository extends GraphRepository<Product> {
    // the "0" parameter is a workaround for a bug in SDN
	Product findById(@Param("0") Long id);

    Collection<Product> findByLimit(@Param("limit") int limit);

    @Query("MATCH (m:Movie)<-[:ACTED_IN]-(a:Person) RETURN m.title as movie, collect(a.name) as cast LIMIT {limit}")
    List<Map<String,Object>> graph(@Param("limit") int limit);
    
}
// end::repository[]

