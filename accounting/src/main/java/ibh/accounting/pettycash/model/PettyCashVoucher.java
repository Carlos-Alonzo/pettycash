package ibh.accounting.pettycash.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;


import javax.persistence.*;
import java.sql.Timestamp;

@Data
@NoArgsConstructor
@Entity
@Table(name = "expenses")
public class PettyCashVoucher
{

//    public PettyCashVoucher(){}

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty
    private long id;

    @NonNull
    @Column
    private Timestamp time;

    @Column
    @JsonProperty
    private long amount;

    @NonNull
    @Column
    @JsonProperty
    private String description;

    @NonNull
    @Column
    @JsonProperty
    private String receivedBy;

    @NonNull
    @Column
    @JsonProperty
    private String authorizedBy;

    @NonNull
    @Column
    @JsonProperty
    private String paidTo;
}
