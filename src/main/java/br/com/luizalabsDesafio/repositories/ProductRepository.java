package br.com.luizalabsDesafio.repositories;

import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.data.repository.query.Param;

import br.com.luizalabsDesafio.domain.Product;

/**
 * @author Luis
 * @since 13.05.2015
 */

// tag::repository[]
public interface ProductRepository extends GraphRepository<Product> {
    // the "0" parameter is a workaround for a bug in SDN
	Product findById(@Param("0") Long id);
 
    
}
// end::repository[]

