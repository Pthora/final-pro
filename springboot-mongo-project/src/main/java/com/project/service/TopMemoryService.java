package com.project.service;

import java.util.List;

import com.project.main.TopMemory;

public interface TopMemoryService {
	public List<TopMemory> readRecords();
	
	public Iterable<TopMemory> putRecords(TopMemory[] users);
	
	public List<TopMemory> findByHostnameTimestamp(String hostname);
}
