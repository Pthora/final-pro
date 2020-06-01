package com.project.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.project.main.Memory;
import com.project.main.Power;

@Repository
public interface PowerRepo extends MongoRepository<Power, String> {

	public List<Power> findAll();

	public Iterable<Power> save(Power[] users);

	public List<Power> findTopByHostnameOrderByTimestampDesc(String hostname);
	
	//for charts
	public List<Power> findAllByHostnameAndTimestampBetween(String hostname,String timestamp1, String timestamp2);

}
