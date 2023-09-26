package com.ada.challenges.challenge1.LegalPerson;


import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/legal-person")
public class LegalPersonController {

    private final LegalPersonService legalPersonService;

    public LegalPersonController(LegalPersonService legalPersonService) {
        this.legalPersonService = legalPersonService;
    }


    @PostMapping(path = "/create")
    @ResponseStatus(HttpStatus.CREATED)
    public LegalPerson createEntity(@RequestBody @Valid LegalPersonDTO legalPersonDTO){
        return this.legalPersonService.create(legalPersonDTO);
    }


    @GetMapping(path = "/read")
    @ResponseStatus(HttpStatus.OK)
    public List<LegalPerson> readEntity(){
        return this.legalPersonService.getAll();
    }


    @PutMapping(path = "/update/{cnpj}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public LegalPerson updateEntity( @PathVariable String cnpj, @RequestBody @Valid LegalPersonDTO legalPersonDTO){
        return this.legalPersonService.update(cnpj, legalPersonDTO);
    }
    @DeleteMapping(path = "/delete/{cnpj}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateEntity( @PathVariable String cnpj){
         this.legalPersonService.delete(cnpj);
    }


}
