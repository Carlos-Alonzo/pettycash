package ibh.accounting.pettycash.controller;

import ibh.accounting.pettycash.model.PettyCash;
import ibh.accounting.pettycash.model.PettyCashVoucher;
import ibh.accounting.pettycash.repositories.ExpenseRepository;
import ibh.accounting.pettycash.requests.NewCashReceiptRequest;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.sql.Timestamp;
import java.util.List;

@RestController
@RequestMapping("/petty")
public class PettyCashController
{

    @NonNull
    @Autowired
    private ExpenseRepository expenseRepository;

    @NonNull
    private PettyCash pettyCash;

    @GetMapping("/expenses")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public List<?> serveAllExpenses()     {return expenseRepository.findAll();    }

    @PostMapping("/insert")
    public ResponseEntity<String> processOneReceipt(@Valid @NonNull @RequestBody NewCashReceiptRequest receiptRequest) throws Exception
    {
        PettyCashVoucher pettyCashReceipt = new PettyCashVoucher();
        pettyCashReceipt.setTime(new Timestamp(System.currentTimeMillis()));
        pettyCashReceipt.setAmount(receiptRequest.getAmount());
        pettyCashReceipt.setAuthorizedBy(receiptRequest.getAuthorizedBy());
        pettyCashReceipt.setDescription(receiptRequest.getDescription());
        pettyCashReceipt.setPaidTo(receiptRequest.getPaidTo());
        pettyCashReceipt.setReceivedBy(receiptRequest.getReceivedBy());
        assert pettyCash.addReceipt(pettyCashReceipt);
        return ResponseEntity.ok("Successfully created a cash receipt with id: " +pettyCashReceipt.getId());
    }

}
