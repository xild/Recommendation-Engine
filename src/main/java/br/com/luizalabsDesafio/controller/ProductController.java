package br.com.luizalabsDesafio.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.luizalabsDesafio.domain.Product;

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

	
	@Loggable
	@RequestMapping(value = "",  params= {"productId", "name", "price"} ,  method = RequestMethod.POST, headers = "Accept=application/json")
	@ResponseStatus(HttpStatus.OK)
	public void saveProducts(@RequestParam("productId") long productId,
			@RequestParam("name") String name,
			@RequestParam("price") BigDecimal price){
		
		

	}
	
	@Loggable
	@RequestMapping(value = "/{productId}", method = RequestMethod.GET, headers = "Accept=application/json")
	public @ResponseBody Product getProducts(
			@PathVariable("personId") int productId) {
	
		System.out.println(productId);
		return new Product(1L, "Notebook", new BigDecimal(1000));
	
	}
	
	@Loggable
	@RequestMapping(value = "/{productId}", method = RequestMethod.DELETE, headers = "Accept=application/json")
	@ResponseStatus(HttpStatus.OK)
	public void removeProducts(@PathVariable("personId") int productId) {
	
		System.out.println(productId);
	
	}
	
	@Loggable
	@RequestMapping(value = "", method = RequestMethod.GET, headers = "Accept=application/json")
	public @ResponseBody List<Product> listProducts(
			@RequestParam("limit") int limit) {
		
		System.out.println(limit);

		List<Product> products = new ArrayList<Product>();
		Product p = new Product(1L, "Notebook", new BigDecimal(1000));
		products.add(p);
		p = new Product(1L, "Calculadora", new BigDecimal(100));
		products.add(p);
		return products;
	
	}
}
