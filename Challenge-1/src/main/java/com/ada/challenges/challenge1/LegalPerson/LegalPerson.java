package com.ada.challenges.challenge1.LegalPerson;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
public class LegalPerson {

    @Id
    private Long id;
    private String cnpj;
    private String socialReason;
    private String cpfEstablishmentContact;
    private String nameEstablishmentContact;
    private String emailEstablishmentContact;


}
