package br.com.luizalabsDesafio.controller;

import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.neo4j.conversion.Result;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.luizalabsDesafio.domain.Product;
import br.com.luizalabsDesafio.repositories.ProductRepository;
import br.com.luizalabsDesafio.services.ProductServices;

import com.jcabi.aspects.Loggable;



/**
 * @author luis vieira 14.05.2015
 *
 */
@RestController
@RequestMapping(value = "v1/products")
public class ProductController {
	// @Autowired
	// private PersonService personService;
	private final Logger logger = LoggerFactory.getLogger(ProductController.class);

	@Autowired
	private ProductRepository repo;
	@Autowired
	private ProductServices service;
	
	@Loggable
	@RequestMapping(value = "",  params= {"productId", "name", "price"} ,  method = RequestMethod.POST, headers = "Accept=application/json")
	@ResponseStatus(HttpStatus.CREATED)
	public void saveProducts(@RequestParam("productId") long productId,
			@RequestParam("name") String name,
			@RequestParam("price") BigDecimal price){
		Product p = new Product(productId, name, price);
		repo.save(p);
		
		

	}
	
	@Loggable
	@RequestMapping(value = "/{productId}", method = RequestMethod.GET, headers = "Accept=application/json")
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody Product getProducts(
			@PathVariable long productId) {
		
		return repo.findById(productId);
	
	}
	
	@Loggable
	@RequestMapping(value = "/{productId}", method = RequestMethod.DELETE, headers = "Accept=application/json")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void removeProducts(@PathVariable long productId) {
		repo.delete(productId);
	
	}
	
	@Loggable
	@RequestMapping(value = "", method = RequestMethod.GET, headers = "Accept=application/json")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public @ResponseBody Result<Product> listProducts(
			@RequestParam("limit") int limit) {
			return service.findAll(limit);
	
	}
}
