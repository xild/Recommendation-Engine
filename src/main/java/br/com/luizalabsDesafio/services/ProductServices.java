package br.com.luizalabsDesafio.services;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.neo4j.conversion.Result;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.luizalabsDesafio.domain.Product;
import br.com.luizalabsDesafio.repositories.ProductRepository;
@Service
@Transactional
public class ProductServices {
	
	@Autowired 
	private ProductRepository productRepo;

	public List<Product> findAll(int limit){
		List<Product> products = new ArrayList<Product>();
		Result<Product> findAll = productRepo.findAll();
		if(findAll != null){
			products = findAll.as(ArrayList.class);
		}
		return limit >= products.size() ? products.subList(0, products.size()) : products.subList(0, limit);
	}

	public void save(long productId, String name, BigDecimal price) {
		// TODO Auto-generated method stub
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
