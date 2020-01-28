package ibh.accounting.pettycash.requests;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
public class NewCashReceiptRequest
{
	@JsonProperty
	private long amount;

	@NonNull
	@JsonProperty
	private String description;

	@NonNull
	@JsonProperty
	private String receivedBy;

	@NonNull
	@JsonProperty
	private String authorizedBy;

	@NonNull
	@JsonProperty
	private String paidTo;

	@Override
	public String toString()
	{
		return 	   " . Desc: " + description +
					   " . Amount: " + amount;
	}
}
