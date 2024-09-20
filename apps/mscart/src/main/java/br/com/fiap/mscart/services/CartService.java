package br.com.fiap.mscart.services;

import br.com.fiap.mscart.dto.CartItemDto;
import br.com.fiap.mscart.models.Cart;
import br.com.fiap.mscart.models.CartItem;
import br.com.fiap.mscart.repositories.CartRepository;
import br.com.fiap.mscart.repositories.CartItemRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
@AllArgsConstructor
public class CartService {

    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    public List<CartItem> findByClientId(UUID clientId) {
        Optional<Cart> optionalCart = cartRepository.findByClientId(clientId);
        Cart cart;
        if (optionalCart.isEmpty()) {
            cart = Cart.builder().clientId(clientId).build();
            cartRepository.save(cart);
        } else {
            cart = optionalCart.get();
        }
        return optionalCart.get().getCartItems();

    }

    @Transactional
    public void updateCart(UUID clientId, List<CartItemDto> cartItemsDto) {
        
        Optional<Cart> optionalCart = cartRepository.findByClientId(clientId);
        Cart cart = optionalCart.get();

        cartItemRepository.deleteAllByCartId(cart.getId());
        if (!cartItemsDto.isEmpty()) {
           List<CartItem> updatedCartItems = cartItemsDto.stream()
                .map(item -> CartItem.builder()
                        .productId(item.productId())
                        .quantity(item.quantity())
                        .price(item.price())
                        .cart(cart)
                        .imageUrl(item.imageUrl())
                        .name(item.name())
                        .build())
                .toList();
           cartItemRepository.saveAll(updatedCartItems);
        }
    }

    public void clearCart(UUID clientId) {
        Optional<Cart> optionalCart = cartRepository.findByClientId(clientId);
        cartItemRepository.deleteAllByCartId(optionalCart.get().getId());
        log.info("Cart com items removidos");
    }


}
