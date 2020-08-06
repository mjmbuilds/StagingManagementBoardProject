package com.staging.model;

public class CodeMessage {
	public int code;
	public String message;
	
	public CodeMessage() {
		code = 0;
		message = "";
	}
	
	public CodeMessage(int code, String message) {
		this.code = code;
		this.message = message;
	}
	
	public CodeMessage(int code) {
		this.code = code;
		this.message = "";
	}
	
	public CodeMessage(String message) {
		this.code = 0;
		this.message = message;
	}
}
