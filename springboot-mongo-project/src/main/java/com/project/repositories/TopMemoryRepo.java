package com.project.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import com.project.main.TopMemory;;

@Repository
public interface TopMemoryRepo extends MongoRepository<TopMemory, String>{
	public List<TopMemory> findAll();
	
	public Iterable<TopMemory> save(TopMemory[] users);
	
	public List<TopMemory> findTop5ByHostnameOrderByTimestampDesc(String hostname);
}
