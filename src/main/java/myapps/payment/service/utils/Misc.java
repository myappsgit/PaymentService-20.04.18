package myapps.payment.service.utils;

import java.util.UUID;

public class Misc {
	public static String getTranId(String name) {
		return UUID.randomUUID().toString() + name;
	}
}
