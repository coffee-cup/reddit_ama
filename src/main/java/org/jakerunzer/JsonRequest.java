package org.jakerunzer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class JsonRequest {
	public JsonRequest() {
	}

	/**
	 * HTTP request will be made to url fetching the json data
	 * 
	 * @param url_string
	 *            the url to make the http request to
	 * @return a string containg the json data recieved from the HTTP request.
	 *         If null is returned, HTTP request could not be made or there was
	 *         an error with the server
	 */
	public static String requestJson(String url_string) {
		String json = "";
		try {
			URL url = new URL(url_string);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept", "applicatoin/json");

			if (conn.getResponseCode() != 200) {
//				throw new RuntimeException("Failed : HTTP error code:"
//						+ conn.getResponseCode());
				return null;
			}

			BufferedReader br = new BufferedReader(new InputStreamReader(
					conn.getInputStream()));

			String output;
			while ((output = br.readLine()) != null) {
				json += output;
			}

			conn.disconnect();
		} catch (MalformedURLException e) {
//			e.printStackTrace();
			return null;
		} catch (IOException e) {
//			e.printStackTrace();
			return null;
		}

		return json;
	}
}
