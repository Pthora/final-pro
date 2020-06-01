package com.project.main;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import static java.nio.file.StandardWatchEventKinds.*;
import static java.nio.file.StandardWatchEventKinds.OVERFLOW;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.repositories.CpuRepo;
import com.project.repositories.DiskRepo;
import com.project.repositories.MemoryRepo;
import com.project.repositories.PowerRepo;
import com.project.repositories.TopDiskRepo;
import com.project.repositories.TopMemoryRepo;

@Service
public class FileWatcherService implements Runnable {

	@Autowired
	DiskRepo diskRepo;
	
	@Autowired
	MemoryRepo memoryRepo;
	
	@Autowired
	CpuRepo cpuRepo;
	
	@Autowired
	TopDiskRepo topDiskRepo;
	
	@Autowired
	TopMemoryRepo topMemoryRepo;
	
	@Autowired
	PowerRepo powerRepo;

	private static final Logger LOGGER = Logger.getLogger("FileWatcherService");
	private final WatchService watcher;

	private final Path path = Paths.get("C:\\Users\\pihut\\Desktop\\Project\\ScriptResult\\Disk");
	private final Path pathDisk = Paths.get("C:\\Users\\pihut\\Desktop\\Project\\ScriptResult\\Disk\\disk.json");
	private final Path pathMemory = Paths.get("C:\\Users\\pihut\\Desktop\\Project\\ScriptResult\\Memory\\memory.json");
	private final Path pathTopMemory = Paths.get("C:\\Users\\pihut\\Desktop\\Project\\ScriptResult\\MemoryList\\memoryList.json");
	private final Path pathTopDisk = Paths.get("C:\\Users\\pihut\\Desktop\\Project\\ScriptResult\\DiskList\\diskList.json");
	private final Path pathCpu = Paths.get("C:\\Users\\pihut\\Desktop\\Project\\ScriptResult\\Cpu\\cpu.json");
	private final Path pathPower = Paths.get("C:\\Users\\pihut\\Desktop\\Project\\ScriptResult\\Power\\power.json");
	
	private Path directory;
	private Path directoryDisk;
	private Path directoryMemory;
	private Path directoryTopMem;
	private Path directoryTopDisk;
	private Path directoryCpu;
	private Path directoryPower;

	public FileWatcherService() throws IOException {
		directory = path.getParent(); // directory remains same for every file
		directoryDisk = pathDisk.getParent();
		directoryMemory = pathMemory.getParent();
		directoryTopMem = pathTopMemory.getParent();
		directoryTopDisk = pathTopDisk.getParent();
		directoryCpu = pathCpu.getParent();
		directoryPower = pathPower.getParent();
		
		//register the directories in the watcher
		watcher = FileSystems.getDefault().newWatchService();
		directoryDisk.register(watcher, ENTRY_MODIFY, ENTRY_CREATE, ENTRY_DELETE);
		directoryMemory.register(watcher, ENTRY_MODIFY, ENTRY_CREATE, ENTRY_DELETE);
		directoryTopDisk.register(watcher, ENTRY_MODIFY, ENTRY_CREATE, ENTRY_DELETE);
		directoryTopMem.register(watcher, ENTRY_MODIFY, ENTRY_CREATE, ENTRY_DELETE);
		directoryCpu.register(watcher, ENTRY_MODIFY, ENTRY_CREATE, ENTRY_DELETE);
		directoryPower.register(watcher, ENTRY_MODIFY, ENTRY_CREATE, ENTRY_DELETE);

	//	LOGGER.info("Added file watcher for: " + pathDisk.toAbsolutePath());
		

		try {
			Thread thread = new Thread(this);
			thread.start();

			// LOGGER.info("Added file watcher for: " + path.toAbsolutePath());
		} catch (Exception ex) {
			LOGGER.warning("Error adding file watcher for a path - " + ex);
		}
	}

	public void diskChanged(Path diskPath) {
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			System.out.println();
			Disk[] disk = objectMapper.readValue(diskPath.toFile(), Disk[].class);
			for (int i = 0; i < disk.length; i++) {
				diskRepo.save(disk[i]);
			}

			System.out.println("Disk Saved!");
		} catch (IOException e) {
			System.out.println("Unable to save users: " + e.getMessage());
		}
	}

	public void memoryChanged(Path memoryPath) {
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			Memory[] memory = objectMapper.readValue(memoryPath.toFile(), Memory[].class);
			System.out.println(memory);
			memoryRepo.save(memory[0]);
			System.out.println("Users Memory Saved!");
		} catch (IOException e) {
			System.out.println("Unable to save memory: " + e.getMessage());
		}
	}

	public void cpuChanged(Path cpuPath) {
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			Cpu[] cpu = objectMapper.readValue(cpuPath.toFile(), Cpu[].class);
			for (int i = 0; i < cpu.length; i++) {
				cpuRepo.save(cpu[i]);
			}

			System.out.println("Cpu Saved!");
		} catch (IOException e) {
			System.out.println("Unable to save cpu: " + e.getMessage());
		}
	}
	
	public void diskListChanged(Path topDiskPath) {
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			TopDisk[] diskList = objectMapper.readValue(topDiskPath.toFile(), TopDisk[].class);
			for (int i = 0; i < diskList.length; i++) {
				topDiskRepo.save(diskList[i]);
			}

			System.out.println("Disk List Saved!");
		} catch (IOException e) {
			System.out.println("Unable to save disk: " + e.getMessage());
		}
	}
	
	public void memListChanged(Path topMemoryPath) {
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			TopMemory[] memoryList = objectMapper.readValue(topMemoryPath.toFile(), TopMemory[].class);
			for (int i = 0; i < memoryList.length; i++) {
				topMemoryRepo.save(memoryList[i]);
			}

			System.out.println("Memory Saved!");
		} catch (IOException e) {
			System.out.println("Unable to save memory: " + e.getMessage());
		}
	}
	
	public void powerChanged(Path powerPath) {
		try {
			System.out.println("hey");
			ObjectMapper objectMapper = new ObjectMapper();
			Power[] power = objectMapper.readValue(powerPath.toFile(), Power[].class);
			System.out.println(power);
			System.out.println("there");
			powerRepo.save(power[0]);
			System.out.println("Power Saved!");
		} catch (IOException e) {
			System.out.println("Unable to save power: " + e.getMessage());
		}
	}

	@Override
	public void run() {

		for (;;) {
			WatchKey key;

			/**
			 * Wait for a modification to occur.
			 */
			try {
				key = watcher.take();
			} catch (InterruptedException ex) {
				return;
			}

			/**
			 * Wait a bit for events to accumulate. This prevents several consecutive
			 * modifications (e.g. several writes or change of file modification date) from
			 * all triggering the listener.
			 */
			try {
				Thread.sleep(2000);
			} catch (InterruptedException ex) {
				return;
			}

			for (WatchEvent<?> event : key.pollEvents()) {
				WatchEvent.Kind<?> kind = event.kind();

				if (kind == OVERFLOW) {
					continue;
				}

				WatchEvent<Path> ev = (WatchEvent<Path>) event;
				Path fileName = ev.context();
				Path changedFile = directory.resolve(fileName);
				
				if (changedFile.toString().contains("DiskList")) {
					
					Path pathAbsolute = Paths.get(pathTopDisk.getParent().toString() + "\\" + fileName.toString());
					//System.out.println("File changed:" + pathAbsolute);
					//System.out.println("file name" + changedFile.getFileName());
					diskListChanged(pathAbsolute);
				}// if top disk
				
				else if (changedFile.toString().contains("MemoryList")) {
					
					Path pathAbsolute = Paths.get(pathTopMemory.getParent().toString() + "\\" + fileName.toString());
					//System.out.println("File changed:" + pathAbsolute);
					//System.out.println("file name" + changedFile.getFileName());
					memListChanged(pathAbsolute);
				}// if top disk

				else if (changedFile.toString().contains("Disk")) {
					
					Path pathAbsolute = Paths.get(pathDisk.getParent().toString() + "\\" + fileName.toString());
					//System.out.println("File changed:" + pathAbsolute);
					//System.out.println("file name" + changedFile.getFileName());
					diskChanged(pathAbsolute);
				} // if for Disk

				else if (changedFile.toString().contains("Memory")) {

					Path pathAbsolute = Paths.get(pathMemory.getParent().toString() + "\\" + fileName.toString());
					//System.out.println("File changed:" + pathAbsolute);
					//System.out.println("file name" + changedFile.getFileName());
					memoryChanged(pathAbsolute);
				} // if memory

				else if (changedFile.toString().contains("Cpu")) {
					
					Path pathAbsolute = Paths.get(pathCpu.getParent().toString() + "\\" + fileName.toString());
					//System.out.println("File changed:" + pathAbsolute);
					//System.out.println("file name" + changedFile.getFileName());
					cpuChanged(pathAbsolute);
				}//if cpu
				
				else if (changedFile.toString().contains("power")) {
					Path pathAbsolute = Paths.get(pathPower.getParent().toString() + "\\" + fileName.toString());
					System.out.println("File changed:" + pathAbsolute);
					System.out.println("file name" + changedFile.getFileName());
					powerChanged(pathAbsolute);
				}//if cpu
			}

			boolean valid = key.reset();
			if (!valid) {
				break;
			}
		}
	}
}// class
