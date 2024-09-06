package br.com.fiap.adjt.ecommerce.model;

import java.math.BigDecimal;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {

  @Id
  private String id;

  private String name;

  private String description;

  private String imageUrl;
  
  private BigDecimal price;
  
  private Integer amount; 

}
