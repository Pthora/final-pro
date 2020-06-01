package com.project.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import com.project.main.Cpu;


@Repository
public interface CpuRepo extends MongoRepository<Cpu, String>{
	public List<Cpu> findAll();
	
	public Iterable<Cpu> save(Cpu[] users);
	
	public List<Cpu> findTopByHostnameOrderByTimestampDesc(String hostname);
	
	public List<Cpu> findByHostnameAndTimestamp(String hostname,String timestamp);
}
