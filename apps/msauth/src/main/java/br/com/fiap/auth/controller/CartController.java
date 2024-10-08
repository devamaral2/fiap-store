package br.com.fiap.auth.controller;

import br.com.fiap.auth.dto.CartItemDto;
import br.com.fiap.auth.dto.UpdateCartDto;
import br.com.fiap.auth.entity.User;
import br.com.fiap.auth.producer.carts.CartProducer;
import br.com.fiap.auth.producer.carts.CartUpdateProducer;
import br.com.fiap.auth.security.SecurityFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("carts")
@AllArgsConstructor
@Slf4j
public class CartController {

    private final CartProducer cartProducer;
    private final CartUpdateProducer cartUpdateProducer;
    private final SecurityFilter securityFilter;

    @GetMapping("/find")
    public List<CartItemDto> getCart(HttpServletRequest request) {
        User user = securityFilter.getUserByToken(request);
        UUID uuid = UUID.fromString(user.getId());
        return cartProducer.findCart(uuid);
    }

    @PatchMapping("/update-cart")
    public void updateCart(HttpServletRequest request, @RequestBody @Valid UpdateCartDto body) {
        User user = securityFilter.getUserByToken(request);
        UUID uuid = UUID.fromString(user.getId());
        cartUpdateProducer.update(uuid, body);
    }

}
