package com.ada.challenges.challenge.backend.LegalPerson;


import com.ada.challenges.challenge.backend.Constants.Constants;
import jakarta.validation.constraints.*;

import lombok.*;
import org.hibernate.validator.constraints.br.CNPJ;
import org.hibernate.validator.constraints.br.CPF;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class LegalPersonDTO {

    @NotBlank(message = "cnpj is mandatory")
    @Pattern(regexp = "[0-9]+", message = "cnpj should have only numbers")
    @CNPJ(message = "CNPJ is invalid")
    @Size(max = Constants.CNPJ_SIZE, message = "cnpj should have only 12 digits")
    private String cnpj;

    @NotBlank(message = "social reason is mandatory")
    @Size(max = 50)
    private String socialReason;

    @NotNull(message = "mcc is mandatory")
    @Max(value = 9999, message = "invalid mcc code, until 4 numbers are allowed")
    private Integer mcc;

    @NotBlank(message = "cpf establishment contact is mandatory")
    @Size(max = Constants.CPF_SIZE)
    @CPF(message = "CPF is invalid")
    private String cpfEstablishmentContact;
    @Size(max = 50)
    @NotBlank(message = "name establishment contact is mandatory")
    private String nameEstablishmentContact;

    @Email(regexp = "^([a-zA-Z0-9_\\-\\.]+)@([a-zA-Z0-9_\\-\\.]+)\\.([a-zA-Z]{2,5})$")
    @NotBlank(message = "email establishment contact is mandatory")
    private String emailEstablishmentContact;


}
