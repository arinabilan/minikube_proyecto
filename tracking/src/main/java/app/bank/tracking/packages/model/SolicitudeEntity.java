package app.bank.tracking.packages.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SolicitudeEntity {
    private Long id;
    private float maxMonths; //plazo maximo en meses
    private double interestRate; //tasa de interes
    private float maxAmount; //monto maximo en porcentaje (entre 0 y 100)
    private int deadline; //plazo solicitado en meses
    private float amount; //monto solicitado
    private LocalDate date; //fecha de solicitud
    private float calculatedAmount; //calculo entre monto solicitado y el % de monto maximo - sera cantidad maxima
    // de plata que banco puede pasar a cliente, depende de tipo de prestamo(ej: si compras primera vivienda,
    // te pueden financiar solo 80% de esa vivienda)
    private int state; //estado en entero (del 0 al 9) : 0-Sin revisar, 1-En revision inicial, 2-Pendiente de documentación, 3-rechazado

    private Long executiveId; //para saber que ejecutivo vio la solicitud.

    private Long clientId;
    private TypeLoanEntity loanType;
}
