package com.logistica.test;

import java.io.IOException;
import java.net.HttpURLConnection;

import org.apache.commons.io.IOUtils;

import com.fasterxml.jackson.databind.ObjectMapper;

public class CalculoDistancia {

	public static Response obtenerDistancia (HttpURLConnection urlConnection) throws IOException{
	
		ObjectMapper mapper = new ObjectMapper();
		String response = IOUtils.toString(urlConnection.getInputStream());
		Response maps = mapper.readValue(response, Response.class);
		return maps;
	}
}
