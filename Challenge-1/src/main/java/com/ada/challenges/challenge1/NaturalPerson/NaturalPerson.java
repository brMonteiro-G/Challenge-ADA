package com.ada.challenges.challenge1.NaturalPerson;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Entity
public class NaturalPerson {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "Merchant_Category_Code")
    private Integer mcc;
    private String cpf;
    private String personName;
    private String personEmail;



}
