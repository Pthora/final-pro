package com.project.main;

import java.sql.Timestamp;

import org.springframework.data.mongodb.core.mapping.Document;

@Document (collection = "Memory")
public class Memory {
	//@Id
	private String timestamp;
	//private Timestamp timestamp;
	private String hostname;
	private String cpuPercent;
	private String totalRam;
	private String ramUsed;
	private String ramPercent;
	private String available;
	private String cache;
	private String swapTotal;
	private String swapUsed;
	
	public String getTotalRam() {
		return totalRam;
	}
	public void setTotalRam(String totalRam) {
		this.totalRam = totalRam;
	}
	public String getRamUsed() {
		return ramUsed;
	}
	public void setRamUsed(String ramUsed) {
		this.ramUsed = ramUsed;
	}
	public String getRamPercent() {
		return ramPercent;
	}
	public void setRamPercent(String ramPercent) {
		this.ramPercent = ramPercent;
	}
	public String getAvailable() {
		return available;
	}
	public void setAvailable(String available) {
		this.available = available;
	}
	public String getCache() {
		return cache;
	}
	public void setCache(String cache) {
		this.cache = cache;
	}
	public String getSwapTotal() {
		return swapTotal;
	}
	public void setSwapTotal(String swapTotal) {
		this.swapTotal = swapTotal;
	}
	public String getSwapUsed() {
		return swapUsed;
	}
	public void setSwapUsed(String swapUsed) {
		this.swapUsed = swapUsed;
	}
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
	public String getCpuPercent() {
		return cpuPercent;
	}
	public void setCpuPercent(String cpuPercent) {
		this.cpuPercent = cpuPercent;
	}
	
}
