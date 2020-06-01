package com.project.service;

import java.util.List;

import com.project.main.Cpu;

public interface CpuService {
	public List<Cpu> readRecords();
	
	public Iterable<Cpu> putRecords(Cpu[] users);
	
	public List<Cpu> findByHostnameTimestamp(String hostname);
	
	public List<Cpu> findByHostAndTime(String hostname,String timestamp);
}
