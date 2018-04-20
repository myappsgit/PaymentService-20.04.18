package myapps.payment.service.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Date;

@JsonIgnoreProperties(ignoreUnknown = true)
public class InstaMojoRefund {
	private String id;
	private String paymentId;
	private String status;
	private double totalAmount;
	private double refundAmount;
	private Date refundDate;

	public InstaMojoRefund() {
	}

	public InstaMojoRefund(String id, String status, double totalAmount, double refundAmount, Date refundDate) {
		this.id = id;
		this.status = status;
		this.totalAmount = totalAmount;
		this.refundAmount = refundAmount;
		this.refundDate = refundDate;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@JsonProperty("payment_id")
	public String getPaymentId() {
		return paymentId;
	}

	public void setPaymentId(String paymentId) {
		this.paymentId = paymentId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@JsonProperty("total_amount")
	public double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}

	@JsonProperty("refund_amount")
	public double getRefundAmount() {
		return refundAmount;
	}

	public void setRefundAmount(double refundAmount) {
		this.refundAmount = refundAmount;
	}

	@JsonProperty("created_at")
	public Date getRefundDate() {
		return refundDate;
	}

	public void setRefundDate(Date refundDate) {
		this.refundDate = refundDate;
	}
}
