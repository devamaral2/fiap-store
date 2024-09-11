package br.com.fiap.adjt.ecommerce.dto;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import br.com.fiap.adjt.ecommerce.model.Product;

@Mapper(componentModel = "spring")
public interface ProductMapper {
	
	ProductDTO productToDTO(Product product);
    
	Product dtoToProduct(ProductDTO productDTO);
    
    @Mapping(target = "id", ignore = true)
    ProductDTO createProductDTOWithoutId(Product product);
	
}
