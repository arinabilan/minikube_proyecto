package app.bank.solicitude.packages.entities;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
//@Table(name="client_documents")
@Table(
        name="client_documents",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"clientId", "Document_Id"})}
)
@Data
@AllArgsConstructor
@NoArgsConstructor
//esta clase(entidad) es de documento que se está subiendo el cliente al momento de solicitar su prestamo
public class ClientDocumentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;
    private LocalDate fechaCarga; //cuando se cargo dicho documento al sistema
    private String rutaDocumento; //la ubicacion del documento
    @Lob
    @Column(name = "documento", nullable = true)
    private byte[] documento;
    private Boolean estado; //estado del documento(si es valido o no, lo debe aprobar ejecutivo)


    private Long clientId; //el dicho documento esta asociado a entidad cliente, a un Id cliente


    @ManyToOne
    @JoinColumn(name = "Document_Id", nullable = false)
    private DocumentEntity document; //el dicho documento ingresado por el cliente esta asociado a entidad
    //documento que consiste en entidad-descripcion del documento, tiene id y titulo de documento
}
