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
    private String name;
    @Column(name="expiration_date")
    private String expirationDate;
    @Column(name="card_number")
    private String cardNumber;
    private String cvv;
    private String value;
    private String installments;
}
