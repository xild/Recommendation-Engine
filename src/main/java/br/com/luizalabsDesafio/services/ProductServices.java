package br.com.luizalabsDesafio.services;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
		return productRepo.findAll().as(ArrayList.class);
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
		/**TODO 
		 * handling a better way to return with "query makes no effect on graph"
		 * */
		productRepo.delete(findByProductId(productId));
	}
}
