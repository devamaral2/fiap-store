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
        cartItems.forEach(item -> item.setCartId(optionalCart.get().getId()));
        return cartItemRepository.saveAll(cartItems);
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
