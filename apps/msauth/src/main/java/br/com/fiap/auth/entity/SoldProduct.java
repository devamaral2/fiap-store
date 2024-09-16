package br.com.fiap.auth.entity;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SoldProduct {
    private UUID productId;
    private Long quantity;
}