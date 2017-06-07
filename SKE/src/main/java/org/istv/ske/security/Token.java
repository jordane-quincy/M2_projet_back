package org.istv.ske.security;

public class Token {
	
	long timestamp;
	String value;
	
	public Token() {
		
	}
	
	public Token(long timestamp, String token) {
		this.timestamp = timestamp;
		this.value = token;
	}
	
	public long getTimestamp() {
		return timestamp;
	}
	
	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}
	
	public String getValue() {
		return value;
	}
	
	public void setValue(String value) {
		this.value = value;
	}
}