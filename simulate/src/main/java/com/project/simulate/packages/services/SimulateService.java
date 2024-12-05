package com.project.simulate.packages.services;

import com.project.simulate.packages.entities.SimulateEntity;
import com.project.simulate.packages.repositories.SimulateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SimulateService {

    @Autowired
    private SimulateRepository simulateRepository;

    public double simulateAmount(double amount, double interestRate, double years) {
        // Realiza el cálculo de simulación (ejemplo de interés compuesto)
        //double result = amount * Math.pow(1 + (interestRate / 100), years);

        // Creamos la entidad y la guardamos en el repositorio simulado (aunque no persiste en DB)
        //SimulateEntity simulateEntity = new SimulateEntity(amount, interestRate, (int) years);
        //simulateRepository.save(simulateEntity); // Aquí "guardamos" en memoria

        //return result;
        double r = interestRate/12/100;
        double n = years * 12;
        double pow = Math.pow((1+r), n);
        return Math.round(amount * ((r*pow)/(pow-1)));
    }
}
