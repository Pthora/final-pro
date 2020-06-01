package com.project.main;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.repositories.CpuRepo;
import com.project.repositories.DiskRepo;
import com.project.repositories.MemoryRepo;
import com.project.repositories.PowerRepo;
import com.project.repositories.TopDiskRepo;
import com.project.repositories.TopMemoryRepo;

//@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/infra")
public class WebController {
	@Autowired
	private MemoryRepo memoryRepo;

	@Autowired
	private DiskRepo diskRepo;

	@Autowired
	private TopMemoryRepo topMemoryRepo;

	@Autowired
	private TopDiskRepo topDiskRepo;

	@Autowired
	private CpuRepo cpuRepo;

	@Autowired
	private PowerRepo powerRepo;

	@GetMapping("/memory")
	public List<Memory> getAllMemory() {
		return memoryRepo.findAll();
	}

	@GetMapping("/disk")
	public List<Disk> getAllDisk() {
		return diskRepo.findAll();
	}

	@GetMapping("/topmemory")
	public List<TopMemory> getMemoryList() {
		return topMemoryRepo.findAll();
	}

	@GetMapping("/topDisk")
	public List<TopDisk> getDiskList() {
		return topDiskRepo.findAll();
	}

	@GetMapping("/cpu")
	public List<Cpu> getAllCpu() {
		return cpuRepo.findAll();
	}

	@GetMapping("/power")
	public List<Power> getPower() {
		return powerRepo.findAll();
	}

	@GetMapping("/memory/{hostname}")
	public List<Memory> getMemorybyHostname(@PathVariable("hostname") String hostname) {

		return memoryRepo.findTopByHostnameOrderByTimestampDesc(hostname);
	}

	@GetMapping("/topDisk/{hostname}")
	public List<TopDisk> getDiskListByHostname(@PathVariable("hostname") String hostname) {

		return topDiskRepo.findTop5ByHostnameOrderByTimestampDesc(hostname);
	}

	@GetMapping("/topMemory/{hostname}")
	public List<TopMemory> getMemListByHostname(@PathVariable("hostname") String hostname) {

		return topMemoryRepo.findTop5ByHostnameOrderByTimestampDesc(hostname);
	}

	@GetMapping("/disk/{hostname}")
	public List<Disk> getDiskbyHostname(@PathVariable("hostname") String hostname) {

		String values[] = diskRepo.findTopByHostnameOrderByTimestampDesc(hostname).toString().split(" ");
		String date = values[0].replace("[", "");
		return diskRepo.findByHostnameAndTimestamp(hostname, date);

	}

	@GetMapping("/cpu/{hostname}")
	public List<Cpu> getCpubyHostname(@PathVariable("hostname") String hostname) {

		// System.out.println(cpuRepo.findTopByHostnameOrderByTimestampDesc(hostname).toString());
		String values[] = cpuRepo.findTopByHostnameOrderByTimestampDesc(hostname).toString().split(" ");
		String date = values[0].replace("[", "");
		// System.out.println(date+" hey "+hostname);

		return cpuRepo.findByHostnameAndTimestamp(hostname, date);
	}

	@GetMapping("/power/{hostname}")
	public List<Power> getPowerbyHostname(@PathVariable("hostname") String hostname) {

		return powerRepo.findTopByHostnameOrderByTimestampDesc(hostname);
	}
	
	@GetMapping("/memoryCharts/{hostname}/{timestamp1}/{timestamp2}")
	public List<Memory> getMemoryCharts(@PathVariable("hostname") String hostname, @PathVariable("timestamp1") String timestamp1, @PathVariable("timestamp2") String timestamp2) {
		
		return memoryRepo.findAllByHostnameAndTimestampBetween(hostname, timestamp1, timestamp2);
		
	}
	
	@GetMapping("/powerChart/{hostname}/{timestamp1}/{timestamp2}")
	public List<Power> getPowerCharts(@PathVariable("hostname") String hostname, @PathVariable("timestamp1") String timestamp1, @PathVariable("timestamp2") String timestamp2) {
		
		return powerRepo.findAllByHostnameAndTimestampBetween(hostname, timestamp1, timestamp2);
		
	}
}
