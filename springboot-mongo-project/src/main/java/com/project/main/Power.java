package com.project.main;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Power")
public class Power {
	
	private String timestamp;
	private String hostname;
	private String core0;
	private String core1;
	private String core2;
	private String core3;
	
	public String getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
	public String getHostname() {
		return hostname;
	}
	public void setHostname(String hostname) {
		this.hostname = hostname;
	}
	public String getCore0() {
		return core0;
	}
	public void setCore0(String core0) {
		this.core0 = core0;
	}
	public String getCore1() {
		return core1;
	}
	public void setCore1(String core1) {
		this.core1 = core1;
	}
	public String getCore2() {
		return core2;
	}
	public void setCore2(String core2) {
		this.core2 = core2;
	}
	public String getCore3() {
		return core3;
	}
	public void setCore3(String core3) {
		this.core3 = core3;
	}
	
	
}