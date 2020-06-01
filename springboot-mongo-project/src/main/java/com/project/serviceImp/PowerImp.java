package com.project.serviceImp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.main.Memory;
import com.project.main.Power;
import com.project.repositories.PowerRepo;
import com.project.service.PowerService;

@Service
public class PowerImp implements PowerService {

    @Autowired
    private PowerRepo repository;

	@Override
	public List<Power> readRecords() {
		return repository.findAll();
	}
	
	@Override
	 public Iterable<Power> putRecords(Power[] users){
		System.out.println("heythere");
		return repository.save(users);
		
	}
	
	@Override
	public List<Power> findByHostnameTimestamp(String hostname) {
		return repository.findTopByHostnameOrderByTimestampDesc(hostname);
	}
	

	@Override
	public List<Power> findByHostnameBetweenTimestamp(String hostname,String timestamp1, String timestamp2) {
		return repository.findAllByHostnameAndTimestampBetween(hostname, timestamp1, timestamp2);
	}



}
