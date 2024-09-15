package br.com.fiap.mscart.services;

import br.com.fiap.mscart.models.Cart;
import br.com.fiap.mscart.models.CartItem;
import br.com.fiap.mscart.repositories.CartRepository;
import br.com.fiap.mscart.repositories.CartItemRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Slf4j
@AllArgsConstructor
public class CartService {

    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    public Cart findByClientId(UUID clientId) {
        Optional<Cart> optionalCart = cartRepository.findByClientId(clientId);
        if (optionalCart.isEmpty()) {
            Cart newCart = Cart.builder().clientId(clientId).build();
            cartRepository.save(newCart);
        }
        return  optionalCart.get();
    }

    public List<CartItem> updateCart(UUID clientId, List<CartItem> cartItems) {
        Optional<Cart> optionalCart = cartRepository.findByClientId(clientId);
        if (optionalCart.isEmpty()) {
            Cart newCart = Cart.builder().clientId(clientId).build();
            cartRepository.save(newCart);
        }
        List<CartItem> updatedCartItems = cartItems.stream()
                .map(item -> CartItem.builder()
                        .productId(item.getProductId())
                        .quantity(item.getQuantity())
                        .price(item.getPrice())
                        .cart(optionalCart.get())  // Associa o cart ao novo CartItem
                        .build())
                .collect(Collectors.toList());
        return cartItemRepository.saveAll(updatedCartItems);
    }

    public void clearCart(UUID clientId) {
        Optional<Cart> optionalCart = cartRepository.findByClientId(clientId);
        if (optionalCart.isEmpty()) {
            Cart newCart = Cart.builder().clientId(clientId).build();
            cartRepository.save(newCart);
        }
        cartItemRepository.deleteAllByCartId(optionalCart.get().getId());
        log.info("Cart com items removidos");
    }


}
