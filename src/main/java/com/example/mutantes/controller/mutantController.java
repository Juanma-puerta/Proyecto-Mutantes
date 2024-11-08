package com.example.mutantes.controller;

import com.example.mutantes.model.dnaSequence;
import com.example.mutantes.repository.mutantRepository;
import com.example.mutantes.service.mutantService;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/mutant")
public class mutantController {

    @Autowired
    private mutantService mutantService;

    @Autowired
    private mutantRepository mutantRepository;

    @PostMapping
    public ResponseEntity<String> isMutant(@RequestBody DnaRequest request) {
        String DnaSequence = String.join("", request.getDna());

        // Evitar duplicados
        if (mutantRepository.existsBySequence(DnaSequence)) {
            boolean isMutant = mutantRepository.findBySequence(dnaSequence).isMutant();
            return new ResponseEntity<>(isMutant ? "Es mutante" : "No es mutante", isMutant ? HttpStatus.OK : HttpStatus.FORBIDDEN);
        }

        boolean result = mutantService.isMutant(request.getDna());
        mutantRepository.save(new dnaSequence(null, DnaSequence, result));

        return result ? ResponseEntity.ok("Es mutante") : ResponseEntity.status(HttpStatus.FORBIDDEN).body("No es mutante");
    }

    @GetMapping("/stats")
    public ResponseEntity<StatsResponse> getStats() {
        long countMutants = mutantRepository.countMutants();
        long countHumans = mutantRepository.countHumans();
        double ratio = countHumans > 0 ? (double) countMutants / countHumans : 0;

        StatsResponse stats = new StatsResponse(countMutants, countHumans, ratio);
        return ResponseEntity.ok(stats);
    }

    // Clase para mapear el JSON recibido
    public static class DnaRequest {
        private String[] dna;
        public String[] getDna() { return dna; }
        public void setDna(String[] dna) { this.dna = dna; }
    }

    // Clase para respuesta de estadísticas
    @Data
    @AllArgsConstructor
    public static class StatsResponse {
        private long count_mutant_dna;
        private long count_human_dna;
        private double ratio;
    }
}
