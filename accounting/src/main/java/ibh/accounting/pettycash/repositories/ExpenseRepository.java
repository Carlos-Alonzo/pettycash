package ibh.accounting.pettycash.repositories;

import ibh.accounting.pettycash.model.PettyCashVoucher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

@Repository
public interface ExpenseRepository extends JpaRepository<PettyCashVoucher, Integer>
{
    List<PettyCashVoucher> findAll();
    PettyCashVoucher findByTime(Timestamp timestamp);
    PettyCashVoucher findByAmount(Long amount);
    PettyCashVoucher findByDescription(String description);
    PettyCashVoucher findByReceivedBy(String receivedBy);
    PettyCashVoucher findByAuthorizedBy(String authorizedBy);
    PettyCashVoucher findByPaidTo(String paidTo);

}
