package com.ada.challenges.challenge1.NaturalPerson;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface NaturalPersonRepository extends JpaRepository<NaturalPerson, Long> {

    Optional<NaturalPerson> findByCpf(String cpf);

}
