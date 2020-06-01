package com.project.service;

import java.sql.Timestamp;
import java.util.List;

import com.project.main.Memory;

public interface MemoryService {
	public List<Memory> readRecords();

	public Iterable<Memory> putRecords(Memory[] users);
	
	public List<Memory> findByHostnameTimestamp(String hostname);

	//for charts
	public List<Memory> findByHostnameBetweenTimestamp(String hostname,String timestamp1,String timestamp2);

}
