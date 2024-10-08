package br.com.fiap.mscart.repositories;
import br.com.fiap.mscart.models.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CartRepository extends JpaRepository<Cart, UUID> {
    @Transactional
    Optional<Cart> findByClientId(UUID id);
}
