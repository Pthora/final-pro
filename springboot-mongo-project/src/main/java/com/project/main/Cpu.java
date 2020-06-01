package com.project.main;

import org.springframework.data.mongodb.core.mapping.Document;

@Document (collection = "Cpu")
public class Cpu {
	private String timestamp;
	private String hostname;
	private String cpu;
	private String userPercent;
	private String nicePercent;
	private String sysPercent;
	private String ioPercent;
	private String softPercent;
	private String idlePercent;
	//private String cpuPercent;
	
	public String getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
	public String getCpu() {
		return cpu;
	}
	public void setCpu(String cpu) {
		this.cpu = cpu;
	}
	public String getUserPercent() {
		return userPercent;
	}
	public void setUserPercent(String userPercent) {
		this.userPercent = userPercent;
	}
	public String getNicePercent() {
		return nicePercent;
	}
	public void setNicePercent(String nicePercent) {
		this.nicePercent = nicePercent;
	}
	public String getSysPercent() {
		return sysPercent;
	}
	public void setSysPercent(String sysPercent) {
		this.sysPercent = sysPercent;
	}
	public String getIoPercent() {
		return ioPercent;
	}
	public void setIoPercent(String ioPercent) {
		this.ioPercent = ioPercent;
	}
	public String getSoftPercent() {
		return softPercent;
	}
	public void setSoftPercent(String softPercent) {
		this.softPercent = softPercent;
	}
	public String getIdlePercent() {
		return idlePercent;
	}
	public void setIdlePercent(String idlePercent) {
		this.idlePercent = idlePercent;
	}
	
	public String getHostname() {
		return hostname;
	}
	public void setHostname(String hostname) {
		this.hostname = hostname;
	}
	@Override
	public String toString() {
		return "" + timestamp + " " + hostname;
	}
	
	
}
