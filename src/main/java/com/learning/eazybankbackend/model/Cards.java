package com.learning.eazybankbackend.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;


@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "cards")
public class Cards {

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "cards_generator"
    )

    @SequenceGenerator(
            name = "cards_generator",
            sequenceName = "cards_seq",
            allocationSize = 1
    )
    @Column(name = "card_id")
    private Long cardId;

    @Column(name = "customer_id")
    private Long customerId;

    @Column(name = "card_number")
    private String cardNumber;

    @Column(name = "card_type")
    private String cardType;

    @Column(name = "total_used")
    private BigDecimal totalLimit;

    @Column(name = "amount_used")
    private BigDecimal amountUsed;

    @Column(name = "available_amount")
    private BigDecimal availableAmount;

    @CreationTimestamp
    @Column(name = "created_date")
    private LocalDateTime createdDate;
}
