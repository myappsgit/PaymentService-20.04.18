package myapps.payment.service;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.util.TextUtils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import myapps.payment.service.model.InstaMojoPayment;
import myapps.payment.service.model.InstaMojoRefund;
import myapps.payment.service.model.PaymentResponse;
import myapps.payment.service.model.RefundResponse;
import myapps.payment.service.utils.HttpUtils;

public class InstaMojoService {
	private static Properties props = null;
	private static InstaMojoService instaMojoService = null;

	private InstaMojoService() {
	}

	public static InstaMojoService getInstance() throws IOException {
		if (instaMojoService == null) {
			if (props == null) {
				props = new Properties();
				ClassLoader loader = Thread.currentThread().getContextClassLoader();
				props.load(loader.getResourceAsStream("application.properties"));
			}
			instaMojoService = new InstaMojoService();
		}
		return instaMojoService;
	}

	public String createPaymentRequest(String name, String email, String phone, String purpose, Double amount,
			String redirectUrl, String product) throws ClientProtocolException, IOException {
		Map<String, String> params = new HashMap<String, String>();
		params.put("buyer_name", name);
		params.put("amount", Double.toString(amount.doubleValue()));
		params.put("purpose", purpose);
		params.put("redirect_url", redirectUrl);
		if (!TextUtils.isEmpty(phone))
			params.put("phone", phone);
		params.put("email", email);

		String response = HttpUtils.sendPostRequest(props.getProperty(product + ".instamojo.api.payment.create"),
				getHeaders(product), params);

		ObjectMapper mapper = new ObjectMapper();
		JsonNode node = (JsonNode) mapper.readValue(response, JsonNode.class);
		if (node.get("success").asBoolean())
			return node.get("payment_request").get("longurl").asText();
		return null;
	}

	public InstaMojoPayment getPaymentResponse(String id, String product) throws URISyntaxException, IOException {
		String response = HttpUtils.sendGetRequest(
				props.getProperty(new StringBuilder().append(product).append(".instamojo.api.payment.get").toString())
						+ id + "/",
				getHeaders(product), null);
		ObjectMapper mapper = new ObjectMapper();
		PaymentResponse paymentResponse = (PaymentResponse) mapper.readValue(response, PaymentResponse.class);
		if (paymentResponse.getPayment().getStatus().equalsIgnoreCase("Credit"))
			return paymentResponse.getPayment();
		return null;
	}

	public InstaMojoRefund createRefund(String paymentId, double amount, String reason, String product)
			throws IOException {
		Map<String, String> params = new HashMap<String, String>();
		params.put("payment_id", paymentId);
		params.put("type", "TAN");
		params.put("body", paymentId);
		params.put("refund_amount", Double.toString(amount));
		String response = HttpUtils.sendPostRequest(props.getProperty(product + ".instamojo.api.refund.create"),
				getHeaders(product), params);
		if (response.startsWith("{\"success\":false"))
			return null;
		ObjectMapper mapper = new ObjectMapper();
		RefundResponse refundResponse = (RefundResponse) mapper.readValue(response, RefundResponse.class);
		if (refundResponse.isSuccess())
			return refundResponse.getRefund();
		return null;
	}

	private Map<String, String> getHeaders(String product) {
		Map<String, String> headers = new HashMap<String, String>();
		headers.put("X-Api-Key", props.getProperty(product + ".instamojo.api.key"));
		headers.put("X-Auth-Token", props.getProperty(product + ".instamojo.auth.token"));
		return headers;
	}

	public static void main(String[] args) throws ClientProtocolException, IOException, URISyntaxException {
		System.out.println(getInstance().createPaymentRequest("kannan", "kannan.m@myapps-solutions.com", "9952902452",
				"payment due", Double.valueOf(150.0D), "http://letzcashup.com", "cashup"));
	}
}
