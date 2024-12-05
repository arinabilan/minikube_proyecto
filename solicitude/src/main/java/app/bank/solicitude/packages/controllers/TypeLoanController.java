package app.bank.solicitude.packages.controllers;

import app.bank.solicitude.packages.entities.TypeLoanEntity;
import app.bank.solicitude.packages.services.TypeLoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/typeloan")
public class TypeLoanController {
    @Autowired
    private TypeLoanService typeLoanService;

    @GetMapping("/")
    public ResponseEntity<List<TypeLoanEntity>> getAllTypeLoans() {
        List<TypeLoanEntity> types = typeLoanService.getAllTypeLoans();
        return ResponseEntity.ok(types);
    }

    @GetMapping("/{type}")
    public ResponseEntity<TypeLoanEntity> getTypeLoan(@PathVariable String type){
        TypeLoanEntity loan = typeLoanService.getTypeLoan(type);
        return ResponseEntity.ok(loan);
    }

    @PostMapping("/")
    public ResponseEntity<TypeLoanEntity> createTypeLoan(@RequestBody TypeLoanEntity typeLoan){
        TypeLoanEntity loan = typeLoanService.createTypeLoan(typeLoan);
        return ResponseEntity.ok(loan);
    }
}
