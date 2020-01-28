package ibh.accounting.pettycash.model;

import lombok.Data;
import lombok.NonNull;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;

@Data
public class PettyCash
{
    private final long STARTING_CASH = 300l;
    private final long MAX_DOLLAR_AMOUNT = 50l;
    private final long LOW_THRESHOLD = 20l;
    private ArrayList<PettyCashVoucher> voucherReceiptList;
    private long currentBalance;
    @NonNull private String custodianName;

    public PettyCash(String custodian)
    {
        currentBalance = STARTING_CASH;
        voucherReceiptList = new ArrayList<>();
        custodianName = custodian;
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

    public ArrayList<PettyCashVoucher> replenish()
    {
        //Persist all expenses to Expense table
        ArrayList<PettyCashVoucher> returnList = new ArrayList<>(voucherReceiptList);
        //reset the pettyCash
        currentBalance = STARTING_CASH;
        //Clear the voucherReceiptList
        voucherReceiptList.clear();
        return returnList;

    }

}
