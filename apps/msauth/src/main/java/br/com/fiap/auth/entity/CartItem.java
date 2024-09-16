package br.com.fiap.auth.entity;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CartItem {
    private UUID id;
    private UUID productId;
    private Long quantity;
    private Double price;
    private UUID cartId;
}
