package ibh.accounting.pettycash.controller;

import ibh.accounting.pettycash.model.PettyCashSystem;
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
    @Autowired
    private PettyCashSystem pettyCash;

    @GetMapping("/expenses")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<List<PettyCashVoucher>> serveAllExpenses()
    {
        return new ResponseEntity<List<PettyCashVoucher>>(pettyCash.getVoucherReceiptList(), null, HttpStatus.ACCEPTED);
    }

    @PostMapping("/insert")
    public ResponseEntity<String> processOneReceipt(@Valid @NonNull @RequestBody NewCashReceiptRequest receiptRequest) throws Exception
    {
        ResponseEntity<String> response;
        PettyCashVoucher pettyCashReceipt = new PettyCashVoucher();
        pettyCashReceipt.setTime(new Timestamp(System.currentTimeMillis()));
        pettyCashReceipt.setAmount(receiptRequest.getAmount());
        pettyCashReceipt.setAuthorizedBy(receiptRequest.getAuthorizedBy());
        pettyCashReceipt.setDescription(receiptRequest.getDescription());
        pettyCashReceipt.setPaidTo(receiptRequest.getPaidTo());
        pettyCashReceipt.setReceivedBy(receiptRequest.getReceivedBy());

        if(pettyCash.addReceipt(pettyCashReceipt))
            response = new ResponseEntity<String>("Successfully created a cash receipt: " +pettyCashReceipt.toString(), HttpStatus.ACCEPTED);
        else
            response = new ResponseEntity<String>("Failure to add the voucher receipt", HttpStatus.BAD_REQUEST);

        return response;
    }

    @PostMapping("/replenish")
    public ResponseEntity<List<PettyCashVoucher>> replenishPettyCash()
    {
//        ResponseEntity response = new ResponseEntity(HttpStatus.ACCEPTED);
//        response.getBody().
        expenseRepository.saveAll(pettyCash.getVoucherReceiptList());
        return new ResponseEntity(pettyCash.replenish(),null,HttpStatus.ACCEPTED);
    }

//    @PostMapping("/replenish/{replenishAmount}")
//    public ResponseEntity<List<PettyCashVoucher>> replenishPettyCash(@Valid @PathVariable Long replenishAmount)
//    {
//
//
//    }
}
