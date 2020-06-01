package com.project.service;

import java.util.List;

import com.project.main.Memory;
import com.project.main.Power;

public interface PowerService {
	public List<Power> readRecords();

	public Iterable<Power> putRecords(Power[] users);
	
	public List<Power> findByHostnameTimestamp(String hostname);
	
	//for charts
	public List<Power> findByHostnameBetweenTimestamp(String hostname,String timestamp1,String timestamp2);

}
