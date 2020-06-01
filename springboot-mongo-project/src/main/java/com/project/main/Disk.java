package com.project.main;

import org.springframework.data.mongodb.core.mapping.Document;

@Document (collection = "Disk")
public class Disk {
	//@Id
	//private String id;
	private String timestamp;
	private String hostname;
	private String fileSys;
	private String size;
	private String used;
	private String available;
	private String usedPercent;
	private String mountPoint;
	
	/*public String getId() {
	return id;
	}
	public void setId(String id) {
		this.id = id;
	}*/
	public String getFileSys() {
		return fileSys;
	}
	public void setFileSys(String fileSys) {
		this.fileSys = fileSys;
	}
	public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
	}
	public String getUsed() {
		return used;
	}
	public void setUsed(String used) {
		this.used = used;
	}
	public String getAvailable() {
		return available;
	}
	public void setAvailable(String available) {
		this.available = available;
	}
	public String getUsedPercent() {
		return usedPercent;
	}
	public void setUsedPercent(String usedPercent) {
		this.usedPercent = usedPercent;
	}
	public String getMountPoint() {
		return mountPoint;
	}
	public void setMountPoint(String mountPoint) {
		this.mountPoint = mountPoint;
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
	
	@Override
	public String toString() {
		return "" + timestamp + " " + hostname;
	}
	
}
