package br.com.fiap.adjt.ecommerce.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import br.com.fiap.adjt.ecommerce.model.Product;
import br.com.fiap.adjt.ecommerce.repository.ProductRepository;
import lombok.AllArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public class ProductService {

	private ProductRepository productRepository;

	public Flux<Product> findAll() {
		return productRepository.findAll();
	}

	public Mono<Product> findById(String id) {
		return productRepository.findById(id);
	}

	public Mono<Product> save(Product product) {
		return productRepository.save(product);
	}

	public Mono<Product> update(String id, Product product) {
		return productRepository.findById(id).map(Optional::of).defaultIfEmpty(Optional.empty()).flatMap(optional -> {
			if (optional.isPresent()) {
				product.setId(id);
				return productRepository.save(product);
			}
			return Mono.empty();
		});
	}

	public Mono<Void> deleteById(String id) {
		return productRepository.deleteById(id);
	}

	public Mono<Void> deleteAll() {
		return productRepository.deleteAll();
	}

	public Flux<Product> findByName(String name) {
		return productRepository.findByName(name);
	}

	public Mono<Product> sell(String id, Product product) {
		
		return productRepository.findById(id).map(Optional::of).defaultIfEmpty(Optional.empty()).flatMap(optional -> {
			if (optional.isPresent()) {
				
				Product productdb = optional.get(); 

				if (productdb.getAmount() > product.getAmount()) {
					Integer newAmout = productdb.getAmount() - product.getAmount();
					productdb.setAmount(newAmout);
					
					return productRepository.save(productdb);
				}
				
			}
			return Mono.empty();
		});
		
	}

}
