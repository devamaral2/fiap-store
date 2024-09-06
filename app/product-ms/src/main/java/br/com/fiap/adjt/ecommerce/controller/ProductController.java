package br.com.fiap.adjt.ecommerce.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.adjt.ecommerce.model.Product;
import br.com.fiap.adjt.ecommerce.service.ProductService;
import lombok.AllArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class ProductController {

	private ProductService productService;

	@GetMapping("/product")
	@ResponseStatus(HttpStatus.OK)
	public Flux<Product> getAll(@RequestParam(required = false) String name) {
		if (name == null)
			return productService.findAll();
		else
			return productService.findByName(name);
	}

	@GetMapping("/product/{id}")
	@ResponseStatus(HttpStatus.OK)
	public Mono<Product> getProductById(@PathVariable("id") String id) {
		return productService.findById(id);
	}

	@PostMapping("/product")
	@ResponseStatus(HttpStatus.CREATED)
	public Mono<Product> create(@RequestBody Product product) {
		return productService.save(
				Product.builder()
				.name(product.getName())
				.description(product.getDescription())
				.imageUrl(product.getImageUrl())
				.price(product.getPrice())
				.amount(product.getAmount())
				.build());
	}

	@PutMapping("/product/{id}")
	@ResponseStatus(HttpStatus.OK)
	public Mono<Product> update(@PathVariable("id") String id, @RequestBody Product product) {
		return productService.update(id, product);
	}


	@PutMapping("/product/sell/{id}")
	@ResponseStatus(HttpStatus.OK)
	public Mono<Product> sell(@PathVariable("id") String id, @RequestBody Product product) {
		return productService.sell(id, product);
	}

//	@PutMapping("/product/sell/{id}")
//	@ResponseStatus(HttpStatus.OK)
//	public Mono<Product> sell(@PathVariable("id") String id, @RequestBody Product product) {
//		return productService.sell(id, product);
//	}

	
	@DeleteMapping("/product/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public Mono<Void> delete(@PathVariable("id") String id) {
		return productService.deleteById(id);
	}

}
