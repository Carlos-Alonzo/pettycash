package repositories;

import model.PettyCashVoucher;
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
    PettyCashVoucher findByNumber(String number);
    PettyCashVoucher findByDescription(String description);
    PettyCashVoucher findByReceivedBy(String receivedBy);
    PettyCashVoucher findByAuthorizedBy(String authorizedBy);
    PettyCashVoucher findByPaidTo(String paidTo);

}
