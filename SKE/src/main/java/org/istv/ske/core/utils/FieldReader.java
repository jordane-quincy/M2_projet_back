package org.istv.ske.core.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.istv.ske.core.exception.BadRequestException;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class FieldReader {
	
	private static JsonElement get(JsonObject object, String key) throws BadRequestException {
		if(object.get(key) == null)
			throw new BadRequestException("Le champ " + key + " n'est pas renseigné");
		return object.get(key);
	}

	public static String readString(JsonObject object, String key) throws BadRequestException {
		try {
			return get(object, key).getAsString();
		} catch (Exception e) {
			throw new BadRequestException("La valeur du champ " + key + " n'est pas un String " + e.getMessage());
		}
	}
	
	public static Long readLong(JsonObject object, String key) throws BadRequestException {
		try {
			return get(object, key).getAsLong();
		} catch (Exception e) {
			throw new BadRequestException("La valeur du champ " + key + " n'est pas un Long : " + e.getMessage());
		}
	}
	
	public static Boolean existField(JsonObject object, String key){
		Set<String> keys = object.keySet();
		if (keys.contains(key))
			return true;
		return false;
	}
	
	public static List<String> readStringArray(JsonObject object, String key) throws BadRequestException {
		try {
			List<String> ret = new ArrayList<>();
			JsonArray array = get(object, key).getAsJsonArray();
			for(JsonElement o : array)
				ret.add(o.getAsString());
			return ret;
		} catch (Exception e) {
			throw new BadRequestException("La valeur du champ " + key + " n'est pas un String[] : " + e.getMessage());
		}
	}
	
}
