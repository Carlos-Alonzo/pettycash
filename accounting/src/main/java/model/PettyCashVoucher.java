package model;

import lombok.Data;
import lombok.NonNull;
import org.springframework.data.annotation.Id;

import javax.persistence.Entity;
import java.sql.Timestamp;

@Data
@Entity
public class PettyCashVoucher
{
    @Id
    int id;
    @NonNull private Timestamp time;
    private long amount;
    @NonNull private String number;
    @NonNull private String description;
    @NonNull private String receivedBy;
    @NonNull private String authorizedBy;
    @NonNull private String paidTo;
}
