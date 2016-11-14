package com.logistica.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import com.fasterxml.jackson.databind.ObjectMapper;


// RESTful Java client with Java build-in HTTP client library
public class RESTManager {

	private static ObjectMapper mapper = new ObjectMapper();

	public static Object send(String urlREST, Object jsonObject, Class<?> responseClass) {

		try {
			URL url = new URL(urlREST);
			HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

			urlConnection.setDoOutput(true);
			urlConnection.setRequestMethod("POST");
			urlConnection.setRequestProperty("Content-Type", "application/json");

			// Convierto el objeto (jsonObject) a formato JSON con JACKSON
			String jsonString = mapper.writeValueAsString(jsonObject);

			OutputStream os = urlConnection.getOutputStream();
			os.write(jsonString.getBytes());
			os.flush();

			if (urlConnection.getResponseCode() != HttpURLConnection.HTTP_OK) {
				throw new RuntimeException("Failed : HTTP error code : " + urlConnection.getResponseCode());
			}

			BufferedReader br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));

			String output, response = "";
			while ((output = br.readLine()) != null) {
				System.out.println(output);
				response = response + output;
			}

			urlConnection.disconnect();

			return mapper.readValue(response, responseClass);

		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}

	}

}
