package com.example.demo.services;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.models.FileClass;
import com.example.demo.repositories.FileRepository;
import org.springframework.util.StringUtils;



@Service
public class FileService {
  
	
	@Autowired
	private FileRepository filerepo;
	
	public FileClass storeFileByCustomerId(MultipartFile file,long customerID) throws IOException {
		// Normalize file name
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        //FileClass fc = new FileClass(fileName, file.getContentType(), file.getBytes());
        FileClass fc = new FileClass(fileName, file.getContentType(), file.getBytes(), customerID);
		return filerepo.save(fc);
	}
	
	public FileClass storeFile(MultipartFile file) throws IOException {
		// Normalize file name
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        FileClass fc = new FileClass(fileName, file.getContentType(), file.getBytes());
		return filerepo.save(fc);
	}
	public FileClass getStoredFile(String docId) {
		return this.filerepo.findById(docId).get();
	}
	
	/*
	 * public List<FileClass> getStoredFileByCustomerID(long customerId) { return
	 * this.filerepo.findByCustomerId(customerId); }
	 */
	
}
