package br.com.luizalabsDesafio.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.neo4j.conversion.Result;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.luizalabsDesafio.domain.Product;
import br.com.luizalabsDesafio.repositories.ProductRepository;
@Service
public class ProductServices {
	
	@Autowired 
	private ProductRepository productRepo;

	@Transactional
	public Result<Product> findAll(int limit){
		return productRepo.findAll();
	}
}
