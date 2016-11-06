package com.logistica.test;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.logistica.jsons.GoogleRespuestaJSON;

public class CalculoDistancia {

	public static GoogleRespuestaJSON obtenerDistancia (String response) throws IOException{
	
		ObjectMapper mapper = new ObjectMapper();
//		String response = IOUtils.toString(urlConnection.getInputStream());
		GoogleRespuestaJSON maps = mapper.readValue(response, GoogleRespuestaJSON.class);
		return maps;
	}
}
