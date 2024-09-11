package br.com.fiap.adjt.ecommerce.dto;

import java.math.BigDecimal;

public record 
	ProductDTO(String id, String name, String description, String imageUrl, BigDecimal price, String category, int quantity) {}
