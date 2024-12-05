package app.bank.loanEvaluation.packages.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClientDatesEntity {
    private Long id;

    private int monthSalary; //salario mensual
    private int date; //cantidad de meses con cuenta en banco, para saber cuanto tiempo lleva de cliente en el banco
    private int initialContract; // meses con contrato laboral
    private Boolean dicom; //buleano para saber si esta en DICOM o no
    private int type; //tipo de trabajador (Independiente o no), rango de valores (1,2) : 1 - independiente, 2: - no independiente
    private int mediaSalary; //promedio de ingresos en ultimos 2 años
    private int monthlyDebt;
    private ClientEntity client;
}
