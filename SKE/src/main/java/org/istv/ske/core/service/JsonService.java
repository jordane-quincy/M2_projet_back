package org.istv.ske.core.service;

import java.io.Reader;

import com.google.gson.JsonElement;

public interface JsonService {

	JsonElement parse(String json);
	
	JsonElement parse(Reader reader);
	
	String stringify(JsonElement element);
	
}
