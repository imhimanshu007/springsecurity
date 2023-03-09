package com.learning.eazybankbackend.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Accounts {

    @Column(name = "customer_id")
    private Long customerId;
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "account_generator"
    )

    @SequenceGenerator(
            name = "account_generator",
            sequenceName = "account_seq",
            allocationSize = 1
    )
    @Column(name = "account_number")
    private Long accountNumber;

    @Column(name = "account_type")
    private String accountType;

    @Column(name = "branch_address")
    private String branchAddress;

    @CreationTimestamp
    @Column(name = "created_date")
    private String createdDate;
}
