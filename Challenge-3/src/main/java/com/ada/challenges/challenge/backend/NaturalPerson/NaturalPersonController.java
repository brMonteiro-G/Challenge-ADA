package com.ada.challenges.challenge.backend.NaturalPerson;


import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/natural-person")
public class NaturalPersonController {

    private final NaturalPersonService naturalPersonService;

    public NaturalPersonController(NaturalPersonService naturalPersonService) {
        this.naturalPersonService = naturalPersonService;
    }

    @PostMapping(path = "/create")
    @ResponseStatus(HttpStatus.CREATED)
    public NaturalPerson createEntity(@RequestBody @Valid NaturalPersonDTO naturalPersonDTO) {
        return this.naturalPersonService.create(naturalPersonDTO);
    }


    @GetMapping(path = "/read")
    @ResponseStatus(HttpStatus.OK)
    public List<NaturalPerson> readEntity() {
        return this.naturalPersonService.getAll();
    }


    @PutMapping(path = "/update/{cpf}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public NaturalPerson updateEntity(@PathVariable String cpf, @RequestBody @Valid NaturalPersonDTO naturalPersonDTO) {
        return this.naturalPersonService.update(cpf, naturalPersonDTO);
    }

    @DeleteMapping(path = "/delete/{cpf}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateEntity(@PathVariable String cpf) {
        this.naturalPersonService.delete(cpf);
    }


}
