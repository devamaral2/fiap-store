package com.fiap.msproduct.models;

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
