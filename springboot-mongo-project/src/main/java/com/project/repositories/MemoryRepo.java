package com.project.repositories;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import com.project.main.Memory;

@Repository
public interface MemoryRepo extends MongoRepository<Memory, String> {

	public List<Memory> findAll();

	public Iterable<Memory> save(Memory[] users);

	public List<Memory> findTopByHostnameOrderByTimestampDesc(String hostname);
	
	//for charts
	public List<Memory> findAllByHostnameAndTimestampBetween(String hostname,String timestamp1, String timestamp2);
}
