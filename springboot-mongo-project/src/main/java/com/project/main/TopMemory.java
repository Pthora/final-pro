package com.project.main;

import org.springframework.data.mongodb.core.mapping.Document;

@Document (collection = "TopMemory")
public class TopMemory {
	
	private String timestamp;
	private String hostname;
	private String pid;
	private String uid;
	private String memoryPercent;
	private String cpuPercent;
	
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getMemoryPercent() {
		return memoryPercent;
	}
	public void setMemoryPercent(String memoryPercent) {
		this.memoryPercent = memoryPercent;
	}
	public String getCpuPercent() {
		return cpuPercent;
	}
	public void setCpuPercent(String cpuPercent) {
		this.cpuPercent = cpuPercent;
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
	

	
}
