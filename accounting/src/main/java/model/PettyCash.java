package model;

import lombok.Data;
import lombok.NonNull;

import javax.persistence.Entity;
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

    public void addReceipt(@NonNull PettyCashVoucher pettyCashVoucherToAdd)
    {
        if(currentBalance >= pettyCashVoucherToAdd.getAmount())
        {
            currentBalance -= pettyCashVoucherToAdd.getAmount();
            voucherReceiptList.add(pettyCashVoucherToAdd);
        }
    }

    public void removeReceipt(@NonNull PettyCashVoucher pettyCashVoucherToRemove)
    {
        if(voucherReceiptList.contains(pettyCashVoucherToRemove))
        {
            currentBalance+= pettyCashVoucherToRemove.getAmount();
            voucherReceiptList.remove(pettyCashVoucherToRemove);
        }
    }

    public void replenish()
    {
        //Persist all expenses to Expense table
        //reset the pettyCash
        //Clear the voucherReceiptList

    }

}
