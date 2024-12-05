package app.bank.tracking.packages.entities;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Loans")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoanEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
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
