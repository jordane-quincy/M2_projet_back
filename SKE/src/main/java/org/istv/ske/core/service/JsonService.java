package org.istv.ske.core.service;

import java.io.Reader;

import org.istv.ske.core.exception.BadRequestException;

import com.google.gson.JsonElement;

public interface JsonService {

	JsonElement parse(String json) throws BadRequestException;
	
	JsonElement parse(Reader reader) throws BadRequestException;
	
	String stringify(JsonElement element);
	
}
