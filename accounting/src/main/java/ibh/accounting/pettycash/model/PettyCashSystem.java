package ibh.accounting.pettycash.model;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Data
@Component
public class PettyCashSystem
{
    private final long STARTING_CASH = 300l;
    private final long MAX_DOLLAR_AMOUNT = 50l;
    private final long LOW_THRESHOLD = 20l;
    private final String DEFAULT_CUSTODIAN = "DEFAULT";
    private ArrayList<PettyCashVoucher> voucherReceiptList;
    private long currentBalance;
    @NonNull private String custodianName;

    public  PettyCashSystem()
    {
        currentBalance = STARTING_CASH;
        voucherReceiptList = new ArrayList<>();
        custodianName = this.DEFAULT_CUSTODIAN;
    }
    public PettyCashSystem(@Nullable String custodian)
    {
        currentBalance = STARTING_CASH;
        voucherReceiptList = new ArrayList<>();
        if(custodian!=null) custodianName = custodian;
        else custodianName = this.DEFAULT_CUSTODIAN;
    }

    public boolean addReceipt(@NonNull PettyCashVoucher pettyCashVoucherToAdd)
    {
        boolean result = false;
        long voucherAmount = pettyCashVoucherToAdd.getAmount();

        if(currentBalance >= voucherAmount && voucherAmount <= this.MAX_DOLLAR_AMOUNT )
        {
            currentBalance -= pettyCashVoucherToAdd.getAmount();
            voucherReceiptList.add(pettyCashVoucherToAdd);
            result = true;
        }

        return result;
    }

    public boolean removeReceipt(@NonNull PettyCashVoucher pettyCashVoucherToRemove)
    {
        boolean result = false;

        if(voucherReceiptList.contains(pettyCashVoucherToRemove))
        {
            currentBalance+= pettyCashVoucherToRemove.getAmount();
            voucherReceiptList.remove(pettyCashVoucherToRemove);
            result = true;
        }
        return result;
    }

    public List<PettyCashVoucher> replenish()
    {
        //Persist all expenses to Expense table
        List<PettyCashVoucher> returnList = new ArrayList<>(voucherReceiptList);
        //reset the pettyCash
        currentBalance = this.STARTING_CASH;
        //Clear the voucherReceiptList
        voucherReceiptList.clear();
        return returnList;

    }

}
