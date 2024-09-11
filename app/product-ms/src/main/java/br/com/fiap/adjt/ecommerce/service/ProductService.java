package br.com.fiap.adjt.ecommerce.service;

import java.util.Optional;
import java.util.concurrent.ExecutionException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.fiap.adjt.ecommerce.dto.ProductDTO;
import br.com.fiap.adjt.ecommerce.dto.ProductMapper;
import br.com.fiap.adjt.ecommerce.model.Product;
import br.com.fiap.adjt.ecommerce.repository.ProductRepository;
import lombok.AllArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public class ProductService {

	private ProductRepository productRepository;
	private final ProductMapper productMapper;

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

	public Flux<Product> save(Flux<Product> products) {
		return productRepository.saveAll(products);
	}

	public ResponseEntity<Mono<ProductDTO>> sellProduct(String id, Integer quantity) throws InterruptedException, ExecutionException {
		
		Product product = productRepository.findById(id).toFuture().get();
		
		if (product != null && product.getQuantity() >= quantity) {
			
			int quantityCurrent = product.getQuantity() ;

			product.setQuantity(quantityCurrent - quantity);
			
			Mono<ProductDTO> map = productRepository.save(product).map(p -> productMapper.productToDTO(p));
			
			return ResponseEntity.status(HttpStatus.OK).body(map);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Mono.empty());
		}

	}

}
