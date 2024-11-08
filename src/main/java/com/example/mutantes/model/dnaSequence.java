package com.example.mutantes.model;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "dna_sequences", uniqueConstraints = @UniqueConstraint(columnNames = {"sequence"}))
public class dnaSequence {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String[] dna;

    @Column(nullable = false)
    private boolean isMutant;
}
