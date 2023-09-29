package com.ada.challenges.challenge.backend.LegalPerson;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
public class LegalPerson {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "Merchant_Category_Code")
    private Integer mcc;
    private String cnpj;
    private String socialReason;
    private String cpfEstablishmentContact;
    private String nameEstablishmentContact;
    private String emailEstablishmentContact;


}
