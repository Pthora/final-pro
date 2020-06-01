package com.project.serviceImp;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.project.main.Disk;
import com.project.repositories.DiskRepo;
import com.project.service.DiskService;

@Service
public class DiskImp implements DiskService{

    @Autowired
    private DiskRepo repository;

	@Override
	public List<Disk> readRecords() {
		return repository.findAll();
	}

	@Override
	 public Iterable<Disk> putRecords(Disk[] users){
		return repository.save(users);
		
	}

	@Override
	public List<Disk> findByHostnameTimestamp(String hostname) {
		return repository.findTopByHostnameOrderByTimestampDesc(hostname);
	}

	@Override
	public List<Disk> findByHostAndTime(String hostname, String timestamp) {
		return repository.findByHostnameAndTimestamp(hostname, timestamp);
	}

}
