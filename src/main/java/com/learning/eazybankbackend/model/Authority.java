package com.learning.eazybankbackend.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "authorities")
public class Authority {
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "authority_generator"
    )

    @SequenceGenerator(
            name = "authority_generator",
            sequenceName = "authority_seq",
            allocationSize = 1
    )

    private Long id;
    private String name;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

}
