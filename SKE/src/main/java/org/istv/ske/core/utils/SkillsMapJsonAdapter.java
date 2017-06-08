package org.istv.ske.core.utils;

import java.io.IOException;
import java.util.Map;
import java.util.Map.Entry;

import org.istv.ske.dal.entities.Skill;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public class SkillsMapJsonAdapter extends JsonSerializer<Map<Skill, Boolean>> {

	@Override
	public void serialize(Map<Skill, Boolean> arg0, JsonGenerator out, SerializerProvider arg2)
			throws IOException, JsonProcessingException {

		out.writeStartArray();
		for(Entry<Skill, Boolean> entry : arg0.entrySet()) {
			out.writeStartObject();
			out.writeNumberField("id", entry.getKey().getId());
			out.writeStringField("label", entry.getKey().getLabel());
			out.writeBooleanField("validated", entry.getValue());
			out.writeEndObject();
		}
		out.writeEndArray();
		
	}

}
