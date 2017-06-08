package org.istv.ske.core.service;

import java.io.Reader;

import org.istv.ske.core.exception.BadRequestException;
import org.springframework.stereotype.Service;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

@Service
public class JsonServiceImpl implements JsonService {
	
	@Override
	public JsonElement parse(String json) throws BadRequestException {
		try {
			JsonParser parser = new JsonParser();
			return parser.parse(json);
		} catch (Exception e) {
			throw new BadRequestException("Le JSON fourni est invalide");
		}
	}

	@Override
	public JsonElement parse(Reader reader) throws BadRequestException {
		try {
			JsonParser parser = new JsonParser();
			return parser.parse(reader);
		} catch (Exception e) {
			throw new BadRequestException("Le JSON fourni est invalide");
		}
	}

	@Override
	public String stringify(JsonElement element) {
		return element.toString();
	}

}
