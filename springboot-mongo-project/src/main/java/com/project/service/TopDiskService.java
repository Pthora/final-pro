package com.project.service;

import java.util.List;

import com.project.main.TopDisk;

public interface TopDiskService {
	public List<TopDisk> readRecords();
	
	public Iterable<TopDisk> putRecords(TopDisk[] users);
	
	public List<TopDisk> findByHostnameOrderByTimestampDesc(String hostname);
}
