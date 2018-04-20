package myapps.payment.service.model;

public class RefundResponse {
	private boolean success;
	private InstaMojoRefund refund;
	private String message;

	public RefundResponse() {
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public InstaMojoRefund getRefund() {
		return refund;
	}

	public void setRefund(InstaMojoRefund refund) {
		this.refund = refund;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
