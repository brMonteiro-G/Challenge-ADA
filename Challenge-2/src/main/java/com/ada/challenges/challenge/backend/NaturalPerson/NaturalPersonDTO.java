package com.ada.challenges.challenge.backend.NaturalPerson;

import com.ada.challenges.challenge.backend.Constants.Constants;
import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.validator.constraints.br.CPF;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class NaturalPersonDTO {


    @NotBlank(message = "cpf  is mandatory")
    @Size(max = Constants.CPF_SIZE)
    @CPF
    private String cpf;

    @Column(name = "Merchant_Category_Code")
    private Integer mcc;

    @Size(max = 50)
    @NotBlank(message = "person name is mandatory")
    private String personName;

    @Email(regexp = "^([a-zA-Z0-9_\\-\\.]+)@([a-zA-Z0-9_\\-\\.]+)\\.([a-zA-Z]{2,5})$")
    @NotBlank(message = "email person is mandatory")
    private String personEmail;


}
