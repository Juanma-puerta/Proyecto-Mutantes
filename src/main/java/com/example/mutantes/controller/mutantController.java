package com.example.mutantes.controller;
import com.example.mutantes.model.dnaSequence;

import com.example.mutantes.repository.mutantRepository;
import com.example.mutantes.service.mutantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mutant")
public class mutantController {
    @Autowired
    private mutantService mutantservice;

    @Autowired
    private mutantRepository mutantrepository;

    @PostMapping
    public ResponseEntity<String> isMutant(@RequestBody DnaRequest request) {
        boolean result = mutantservice.isMutant(request.getDna());

        // Guardamos la secuencia en la base de datos
        mutantrepository.save(new dnaSequence(null, request.getDna(), result));

        if (result) {
            return ResponseEntity.ok("Es mutante");
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("No es mutante");
        }
    }


    public static class DnaRequest {
        private String[] dna;

        public String[] getDna() {
            return dna;
        }

        public void setDna(String[] dna) {
            this.dna = dna;
        }
    }
}
