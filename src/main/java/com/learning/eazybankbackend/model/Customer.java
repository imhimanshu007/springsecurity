package com.learning.eazybankbackend.model;

import jakarta.persistence.*;
import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Customer {
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "customer_generator"
    )

    @SequenceGenerator(
            name = "customer_generator",
            sequenceName = "customer_seq",
            allocationSize = 1
    )


    private Long id;
    private String email;
    private String pwd;
    private String role;
}
