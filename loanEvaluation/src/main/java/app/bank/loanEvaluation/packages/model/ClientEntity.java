package app.bank.loanEvaluation.packages.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClientEntity {
    private Long id;
    private String rut;
    private String name;
    private String surname;
    private LocalDate birthday;
    private String email;
    private String password;
}
