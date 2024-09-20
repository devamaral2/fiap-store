package br.com.fiap.mscart.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "cart_item")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @Column(name = "product_id")
    private UUID productId;
    private Long quantity;
    private Double price;
    private String imageUrl;
    private  String name;

    @ManyToOne
    @JoinColumn(name = "cart_id")
    private Cart cart;
}
