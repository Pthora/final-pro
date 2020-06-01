package com.project.serviceImp;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.project.main.Memory;
import com.project.repositories.MemoryRepo;
import com.project.service.MemoryService;

@Service
public class MemoryImp implements MemoryService {

    @Autowired
    private MemoryRepo repository;

	@Override
	public List<Memory> readRecords() {
		return repository.findAll();
	}
	
	@Override
	 public Iterable<Memory> putRecords(Memory[] users){
		return repository.save(users);
		
	}
	
	@Override
	public List<Memory> findByHostnameTimestamp(String hostname) {
		return repository.findTopByHostnameOrderByTimestampDesc(hostname);
	}

	@Override
	public List<Memory> findByHostnameBetweenTimestamp(String hostname,String timestamp1, String timestamp2) {
		return repository.findAllByHostnameAndTimestampBetween(hostname, timestamp1, timestamp2);
	}


}
