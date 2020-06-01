package com.project.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.project.main.Disk;

@Repository
public interface DiskRepo extends MongoRepository<Disk, String>{
	public List<Disk> findAll();

	public Iterable<Disk> save(Disk[] users);
	
	public List<Disk> findTopByHostnameOrderByTimestampDesc(String hostname);
	
	public List<Disk> findByHostnameAndTimestamp(String hostname, String timestamp);
}
