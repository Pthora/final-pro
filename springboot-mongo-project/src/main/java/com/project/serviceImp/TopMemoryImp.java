package com.project.serviceImp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.main.TopMemory;
import com.project.repositories.TopMemoryRepo;
import com.project.service.TopMemoryService;

@Service
public class TopMemoryImp implements TopMemoryService {

    @Autowired
    private TopMemoryRepo repository;

	@Override
	public List<TopMemory> readRecords() {
		return repository.findAll();
	}

	@Override
	 public Iterable<TopMemory> putRecords(TopMemory[] users){
		return repository.save(users);
		
	}

	@Override
	public List<TopMemory> findByHostnameTimestamp(String hostname) {
		return repository.findTop5ByHostnameOrderByTimestampDesc(hostname);
	}

}
