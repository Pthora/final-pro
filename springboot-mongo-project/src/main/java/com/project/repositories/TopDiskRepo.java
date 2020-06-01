package com.project.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import com.project.main.TopDisk;

@Repository
public interface TopDiskRepo extends MongoRepository<TopDisk, String>{
	public List<TopDisk> findAll();
	
	public Iterable<TopDisk> save(TopDisk[] users);
	
	public List<TopDisk> findTop5ByHostnameOrderByTimestampDesc(String hostname);
}
