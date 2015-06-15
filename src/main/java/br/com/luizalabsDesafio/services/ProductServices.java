package br.com.luizalabsDesafio.services;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.neo4j.conversion.Result;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.luizalabsDesafio.domain.Person;
import br.com.luizalabsDesafio.domain.Product;
import br.com.luizalabsDesafio.repositories.ProductRepository;
@Service
@Transactional
public class ProductServices {
	
	@Autowired 
	private ProductRepository productRepo;

	public List<Product> findAll(int limit){
		if(limit == 0){
			return productRepo.findAll().as(ArrayList.class);
		}
		
		Page<Product> findAll = productRepo.findAll(new PageRequest(0, limit));
		return findAll.getContent();
	}

	public void save(long productId, String name, BigDecimal price) {
		Product p = new Product(productId, name, price);
		productRepo.save(p);
	}

	public Product findByProductId(long productId) {
		return	productRepo.findByProductId(productId);
	}

	public void delete(long productId) {
		productRepo.delete(findByProductId(productId));
	}


	public void save(List<Product> products) {
		products.forEach(p -> productRepo.save(p));
	}
}
