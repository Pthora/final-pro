package com.project.serviceImp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.main.Cpu;
import com.project.repositories.CpuRepo;
import com.project.service.CpuService;

@Service
public class CpuImp implements CpuService{

    @Autowired
    private CpuRepo repository;

	@Override
	public List<Cpu> readRecords() {
		return repository.findAll();
	}
	@Override
	 public Iterable<Cpu> putRecords(Cpu[] users){
		return repository.save(users);
		
	}
	
	@Override
	public List<Cpu> findByHostnameTimestamp(String hostname) {
		return repository.findTopByHostnameOrderByTimestampDesc(hostname);
	}
	@Override
	public List<Cpu> findByHostAndTime(String hostname, String timestamp) {
		return repository.findByHostnameAndTimestamp(hostname, timestamp);
	}


}
