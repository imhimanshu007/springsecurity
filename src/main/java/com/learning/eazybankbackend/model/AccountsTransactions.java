package com.learning.eazybankbackend.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;


@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "accounts_transactions")
public class AccountsTransactions {

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "account_transactions_generator"
    )

    @SequenceGenerator(
            name = "account_transactions_generator",
            sequenceName = "account_transactions_seq",
            allocationSize = 1
    )
    @Column(name = "transaction_id")
    private String transactionId;

    @Column(name = "account_number")
    private Long accountNumber;

    @Column(name = "customer_id")
    private Long customerId;

    @Column(name = "transaction_date")
    private LocalDateTime transactionDate;

    @Column(name = "transaction_summary")
    private String transactionSummary;

    @Column(name = "transaction_type")
    private String transactionType;

    @Column(name = "transaction_amount")
    private BigDecimal transactionAmount;

    @Column(name = "closing_balance")
    private BigDecimal closingBalance;


    @Column(name = "created_date")
    private LocalDateTime createdDate;

}
