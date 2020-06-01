package com.project.service;

import java.util.List;
import com.project.main.Disk;

public interface DiskService {
	public List<Disk> readRecords();

	public Iterable<Disk> putRecords(Disk[] users);
	
	public List<Disk> findByHostnameTimestamp(String hostname);
	
	public List<Disk> findByHostAndTime(String hostname,String timestamp);
}
