package org.istv.ske.core.service;

import java.io.Reader;

import org.springframework.stereotype.Service;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

@Service
public class JsonServiceImpl implements JsonService {
	
	@Override
	public JsonElement parse(String json) {
		JsonParser parser = new JsonParser();
		return parser.parse(json);
	}

	@Override
	public JsonElement parse(Reader reader) {
		JsonParser parser = new JsonParser();
		return parser.parse(reader);
	}

	@Override
	public String stringify(JsonElement element) {
		return element.toString();
	}

}
