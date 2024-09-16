package br.com.fiap.mscart.controllers;
import br.com.fiap.mscart.dto.CartItemDto;
import br.com.fiap.mscart.dto.UpdateCartDto;
import br.com.fiap.mscart.models.CartItem;
import br.com.fiap.mscart.services.CartService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("carts")
@AllArgsConstructor
@Slf4j
public class CartController {

    private final CartService cartService;

    @GetMapping("/{id}/find")
    public List<CartItemDto> findByClientId(@PathVariable UUID id) {
        log.info(String.valueOf(id));
        List<CartItem> list =  this.cartService.findByClientId(id);
        return list.stream().map(i -> new CartItemDto(
                i.getId(),
                i.getProductId(),
                i.getQuantity(),
                i.getPrice(),
                i.getCart().getId()
        )).toList();
    }

    @PatchMapping("/{id}/update-cart")
    public void updateCart(
            @RequestBody UpdateCartDto dto,
            @PathVariable String id) {
        UUID uuid = UUID.fromString(id);
        this.cartService.updateCart(uuid, dto.cartItems());
    }

    @PatchMapping("/{id}/clear-cart")
    public void clearCart(
            @PathVariable UUID id) {
        this.cartService.clearCart(id);
    }
}
