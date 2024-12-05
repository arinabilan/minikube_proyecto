package app.bank.loanEvaluation.packages.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoanEntity {
    private Long id; //id asosiado al dicho prestamo}

    private double monthlyFee; //cuota mensual
    private double insuranceFee; //cuota seguro desgravamento
    private double insuranceFire = 20000; //cuota seguro incendio
    private double comision; //comision
    private double monthlyCost; //costo mensual
    private double totalCost; //costo total
    private Boolean approved = false; //estado de credito, si esta aprobado o no por el cliente

    //private Long typeLoanId;
    private Long solicitudeId;
}
