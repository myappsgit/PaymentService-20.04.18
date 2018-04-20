package myapps.payment.service.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Date;

@JsonIgnoreProperties(ignoreUnknown = true)
public class InstaMojoPayment {
	private String id;
	private String buyerName;
	private String method;
	private double amount;
	private String status;
	private double fees;
	private Date paymentDate;

	public InstaMojoPayment() {
	}

	public InstaMojoPayment(String id, String buyerName, String type, double amount, String status, double fees,
			Date paymentDate) {
		this.id = id;
		this.buyerName = buyerName;
		method = type;
		this.amount = amount;
		this.status = status;
		this.fees = fees;
		this.paymentDate = paymentDate;
	}

	@JsonProperty("payment_id")
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@JsonProperty("buyer_name")
	public String getBuyerName() {
		return buyerName;
	}

	public void setBuyerName(String buyerName) {
		this.buyerName = buyerName;
	}

	@JsonProperty("instrument_type")
	public String getMethod() {
		return method;
	}

	public void setMethod(String type) {
		method = type;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public double getFees() {
		return fees;
	}

	public void setFees(double fees) {
		this.fees = fees;
	}

	@JsonProperty("created_at")
	public Date getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(Date paymentDate) {
		this.paymentDate = paymentDate;
	}
}
