package com.example.mutantes.repository;

import com.example.mutantes.model.dnaSequence;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface mutantRepository extends JpaRepository<dnaSequence, Long> {

    boolean existsBySequence(String sequence);

    Optional<dnaSequence> findBySequence(String[] dna);

    @Query("SELECT COUNT(d) FROM DnaSequence d WHERE d.isMutant = true")
    long countMutants();

    @Query("SELECT COUNT(d) FROM DnaSequence d WHERE d.isMutant = false")
    long countHumans();
}
