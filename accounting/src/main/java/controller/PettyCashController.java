package controller;

import lombok.NonNull;
import model.PettyCash;
import model.PettyCashVoucher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import repositories.ExpenseRepository;

import java.util.List;

@RestController
@RequestMapping("/petty")
public class PettyCashController
{

    @Autowired
    private ExpenseRepository expenseRepository;
    @Autowired
    private PettyCash pettyCash;

    @GetMapping(value = "/expenses")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public List<?> serveAllExpenses()     {return expenseRepository.findAll();    }

    @PostMapping(value ="/insert")
    public ResponseEntity<?> processOneReceipt(@NonNull @RequestBody PettyCashVoucher receipt)
    {
        pettyCash.addReceipt(receipt);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

}
