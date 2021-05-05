package no.hvl.dat110.aciotdevice.client;

import okhttp3.*;

import java.io.IOException;
import java.util.Arrays;

public class RestClient {

	public static final MediaType JSON
			= MediaType.parse("application/json; charset=utf-8");

	public RestClient() {
		// TODO Auto-generated constructor stub
	}

	private static String logpath = "/accessdevice/log";

	public void doPostAccessEntry(String message) {

		// TODO: implement a HTTP POST on the service to post the message
		OkHttpClient client = new OkHttpClient();

		RequestBody body = RequestBody.create(JSON, message);
		Request request = new Request.Builder().url("http://localhost:8080/accessdevice/log").put(body).build();

		try (Response response = client.newCall(request).execute()) {
			System.out.println(response.body().string());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private static String codepath = "/accessdevice/code";
	
	public AccessCode doGetAccessCode() {

		AccessCode code = new AccessCode();
		
		// TODO: implement a HTTP GET on the service to get current access code
		OkHttpClient client = new OkHttpClient();
		Request request = new Request.Builder()
				.url("http://localhost:8080/accessdevice/code")
				.get()
				.build();
		try (Response response = client.newCall(request).execute()) {
			String codeStr = response.body().string();
			System.out.println("The received code is: " + codeStr);
			String[] items = codeStr.replaceAll("\\[", "").replaceAll("\\\"","").replaceAll("\\ ", "").replaceAll("\\]", "").split(",");
			System.out.println(Arrays.toString(items) + "The length is " + items.length + " Element 1: " + items[0] + " Element 2 " + items[1]);
			int[] result = new int [items.length];
			for (int i = 0; i < 0; i++){
				try {
					result[i] = Integer.parseInt(items[i]);
					code.setAccesscode(result);
				} catch (NumberFormatException e) {
					System.out.println("Formatting error at doGetAccessCode! " + e);
				}
			}

		} catch (IOException e) {
			System.out.println("Exception raised in the method doGetAccessCode! " + e);
		}
		int[] received = code.getAccesscode();
		System.out.println("doCode received code: " + received[0] + received[1]);
		return code;
	}
}
