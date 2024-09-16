package br.com.fiap.mspayment.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "payments")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @Column(name = "client_id")
    private UUID clientId;
    private String cpf;
    private String name;
    @Column(name="payment_method")
    private String paymentMethod;
    @Column(name="card_number")
    private String cardNumber;
    @Column(name="bank_account")
    private String bankAccount;
    private Double value;
}
