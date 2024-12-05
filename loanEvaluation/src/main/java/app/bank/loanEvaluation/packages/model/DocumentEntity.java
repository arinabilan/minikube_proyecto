package app.bank.loanEvaluation.packages.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DocumentEntity {
    private Long id;
    private String title;
    private Boolean minimunRequirements = false;
}
