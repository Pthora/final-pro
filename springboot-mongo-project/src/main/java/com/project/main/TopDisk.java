package com.project.main;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "TopDisk")
public class TopDisk {
	// @Id
	// private String id;
	private String timestamp;
	private String hostname;
	private String fileSize;
	private String fileName;

	public String getFileSize() {
		return fileSize;
	}

	public void setFileSize(String filesize) {
		this.fileSize = filesize;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String filename) {
		this.fileName = filename;
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

	/*
	 * public String getId() { return id; }
	 * 
	 * public void setId(String id) { this.id = id; }
	 */

}
