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
@Table(name = "loans")
public class Loans {
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "loan_generator"
    )

    @SequenceGenerator(
            name = "loan_generator",
            sequenceName = "loan_seq",
            allocationSize = 1
    )
    @Column(name = "loan_number")
    private Long loanNumber;

    @Column(name = "cutomer_id")
    private Long customerId;

    @Column(name = "start_date")
    private LocalDateTime startDate;

    @Column(name = "loan_type")
    private String loanType;

    @Column(name = "total_loan")
    private BigDecimal totalLoan;

    @Column(name = "amount_paid")
    private BigDecimal amountPaid;

    @Column(name = "outstanding_amount")
    private BigDecimal outstandingAmount;

    @CreationTimestamp
    @Column(name ="created_date")
    private LocalDateTime createdDate;

}
