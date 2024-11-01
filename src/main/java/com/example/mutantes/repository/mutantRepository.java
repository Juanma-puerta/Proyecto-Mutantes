package com.example.mutantes.repository;

import com.example.mutantes.model.dnaSequence;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface mutantRepository extends JpaRepository<dnaSequence, Long> {
}
