package com.project.simulate.packages.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor // Esto generará un constructor sin parámetros
public class SimulateEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double amount;
    private double interestRate;
    private int years;

    // Constructor con parámetros (esto lo genera Lombok automáticamente si usas @AllArgsConstructor)
    public SimulateEntity(double amount, double interestRate, int years) {
        this.amount = amount;
        this.interestRate = interestRate;
        this.years = years;
    }
}
