package com.ada.challenges.challenge1.LegalPerson;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LegalPersonRepository extends JpaRepository<LegalPerson, Long> {


    Optional<LegalPerson> findByCnpj(String cnpj);

}
