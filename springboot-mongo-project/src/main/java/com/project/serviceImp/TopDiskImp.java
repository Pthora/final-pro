package com.project.serviceImp;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.project.main.TopDisk;
import com.project.repositories.TopDiskRepo;
import com.project.service.TopDiskService;

@Service
public class TopDiskImp implements TopDiskService{

    @Autowired
    private TopDiskRepo repository;

	@Override
	public List<TopDisk> readRecords() {
		return repository.findAll();
	}
	
	@Override
	 public Iterable<TopDisk> putRecords(TopDisk[] users){
		return repository.save(users);
		
	}

	@Override
	public List<TopDisk> findByHostnameOrderByTimestampDesc(String hostname) {
		return repository.findTop5ByHostnameOrderByTimestampDesc(hostname);
	}

}
