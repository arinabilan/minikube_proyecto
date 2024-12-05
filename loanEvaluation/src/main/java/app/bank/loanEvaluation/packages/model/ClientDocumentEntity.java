package app.bank.loanEvaluation.packages.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClientDocumentEntity {
    private Long id;
    private LocalDate fechaCarga; //cuando se cargo dicho documento al sistema
    private String rutaDocumento; //la ubicacion del documento
    private Boolean estado; //estado del documento(si es valido o no, lo debe aprobar ejecutivo)


    private Long clientId;
    private DocumentEntity document;
}
