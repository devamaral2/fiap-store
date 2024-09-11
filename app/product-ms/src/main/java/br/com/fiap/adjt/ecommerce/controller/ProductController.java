package br.com.fiap.adjt.ecommerce.controller;

import java.util.concurrent.ExecutionException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

import br.com.fiap.adjt.ecommerce.dto.ProductDTO;
import br.com.fiap.adjt.ecommerce.dto.ProductMapper;
import br.com.fiap.adjt.ecommerce.dto.ProdutoSellDTO;
import br.com.fiap.adjt.ecommerce.model.Product;
import br.com.fiap.adjt.ecommerce.service.ProductService;
import lombok.AllArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@CrossOrigin(origins = "http://localhost:8002")
@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class ProductController {

	private final ProductService productService;
	
	private final ProductMapper productMapper;

	@GetMapping("/product")
	@ResponseStatus(HttpStatus.OK)
	public Flux<ProductDTO> getAll(@RequestParam(required = false) String name) {
		if (name == null)
			return productService.findAll().map(p -> productMapper.productToDTO(p));
		else
			return productService.findByName(name).map(p -> productMapper.productToDTO(p));
	}

	@GetMapping("/product/{id}")
	@ResponseStatus(HttpStatus.OK)
	public Mono<ProductDTO> getProductById(@PathVariable("id") String id) {
		return productService.findById(id).map(p -> productMapper.productToDTO(p));
	}


	@PostMapping("/products")
	@ResponseStatus(HttpStatus.CREATED)
	public Flux<ProductDTO> create(@RequestBody Flux<ProductDTO> productsDTO) {
		Flux<Product> flux = productsDTO.map(p -> productMapper.dtoToProduct(p));
		return productService.save(flux).map(p -> productMapper.productToDTO(p));
	}

	@PostMapping("/product")
	@ResponseStatus(HttpStatus.CREATED)
	public Mono<ProductDTO> create(@RequestBody ProductDTO productDTO) {
		Product product = productMapper.dtoToProduct(productDTO);
		return productService.save(product).map(p -> productMapper.productToDTO(p));
	}

	@PutMapping("/product/{id}")
	@ResponseStatus(HttpStatus.OK)
	public Mono<ProductDTO> update(@PathVariable("id") String id, @RequestBody ProductDTO productDTO) {
		Product product = productMapper.dtoToProduct(productDTO);
		return productService.update(id, product).map(p -> productMapper.productToDTO(p));
	}
	
	@PutMapping("/product/sell")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Mono<ProductDTO>> update2(@RequestBody ProdutoSellDTO produtoSellDTO) throws InterruptedException, ExecutionException {
		return productService.sellProduct(produtoSellDTO.productId(), produtoSellDTO.quantity());
	}

	@DeleteMapping("/product/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public Mono<Void> delete(@PathVariable("id") String id) {
		return productService.deleteById(id);
	}

}
