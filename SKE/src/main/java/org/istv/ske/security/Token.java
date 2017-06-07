package org.istv.ske.security;

import java.sql.Timestamp;

public class Token {
	
	Timestamp timestamp;
	String value;
	
	public Token() {}
	
	public Token(Timestamp timestamp, String token) {
		this.timestamp = timestamp;
		this.value = token;
	}
	
	public Timestamp getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
}