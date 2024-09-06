package br.com.fiap.adjt.ecommerce.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import br.com.fiap.adjt.ecommerce.model.Product;
import reactor.core.publisher.Flux;

@Repository
public interface ProductRepository extends ReactiveMongoRepository<Product, String> {
	
	Flux<Product> findByName(String name);

}
