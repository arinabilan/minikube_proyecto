package app.bank.loanEvaluation.packages.entities;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "executives")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExecutiveEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;
    private String name;
    private String surname;
    private String email;
    private String phone;
    private String password;
}
