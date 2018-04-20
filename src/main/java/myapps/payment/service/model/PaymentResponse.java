package myapps.payment.service.model;

public class PaymentResponse {
	private boolean success;
	private InstaMojoPayment payment;
	private String message;

	public PaymentResponse() {
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public InstaMojoPayment getPayment() {
		return payment;
	}

	public void setPayment(InstaMojoPayment payment) {
		this.payment = payment;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
