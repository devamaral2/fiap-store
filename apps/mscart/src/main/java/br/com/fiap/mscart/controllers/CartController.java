package br.com.fiap.mscart.controllers;
import br.com.fiap.mscart.controllers.exceptions.ValidationTrigger;
import br.com.fiap.mscart.dto.UpdateCartDto;
import br.com.fiap.mscart.models.Cart;
import br.com.fiap.mscart.models.CartItem;
import br.com.fiap.mscart.models.ClearCartResponse;
import br.com.fiap.mscart.services.CartService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("carts")
@AllArgsConstructor
public class CartController {

    private final CartService cartService;

    @GetMapping("/{id}")
    public ResponseEntity<Cart> findByClientId(@PathVariable UUID id) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(this.cartService.findByClientId(id));
    }

    @PatchMapping("/{id}/update-cart")
    public ResponseEntity<List<CartItem>> updateCart(
            @RequestBody @Valid UpdateCartDto dto,
            BindingResult bindingResult) {
        new ValidationTrigger(bindingResult).verify();
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(this.cartService.updateCart(dto.clienteId(), dto.cartItems()));
    }

    @PatchMapping("/{id}/clear-cart")
    public ResponseEntity<ClearCartResponse> update(
            @PathVariable UUID id) {
        this.cartService.clearCart(id);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ClearCartResponse("Cart esvaziado com sucesso"));
    }
}
