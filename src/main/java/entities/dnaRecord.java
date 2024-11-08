package entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor

public class dnaRecord {
    @Id
    private String dnaSequence; // Usa el ADN como clave primaria para evitar duplicados
    private boolean isMutant;
}
