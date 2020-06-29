
package com.example.demo.controllers;



import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.models.FileClass;
import com.example.demo.services.FileService;

@RestController
public class UploadController {
	
	@Autowired
	private FileService fileservice;
	
	@Autowired
	private RestTemplate restTemplate;
	
	
	@PostMapping("/uploadFile")
	public String uploadFile(@RequestParam("file") MultipartFile file) throws IOException {
		FileClass dbfile = fileservice.storeFile(file);
		return "File is uploaded successfully";
	}
	
	@GetMapping("/downloadFile/{documentId}")
	public ResponseEntity<ByteArrayResource> getdownloadFile(@PathVariable("documentId") String documentId){
		FileClass dbfile = fileservice.getStoredFile(documentId);
		return ResponseEntity.ok()
				.contentType(MediaType.parseMediaType(dbfile.getFileType()))
				.body(new ByteArrayResource(dbfile.getData()));
	}
	@PostMapping("/uploadMultipleFiles")
	public List<String> uploadmultiplefiles(@RequestParam("files") MultipartFile[] files) {
		return Arrays.asList(files)
		.stream()
		.map(file -> {
			try {
				return uploadFile(file);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
		})
		.collect(Collectors.toList());
	}
	
	@PostMapping("/{customerId}/uploadFile")
	public String uploadFileByCustomerId(@PathVariable("customerId") Long customerID, @RequestParam("file") MultipartFile file) throws IOException {
		String customerResourceUrl = "http://localhost:7080/customer";
		ResponseEntity<String> response = restTemplate.getForEntity(customerResourceUrl + customerID, String.class);
		System.out.println(response.getBody());
		if (response.getStatusCode() == HttpStatus.OK) {
			FileClass dbfile = fileservice.storeFileByCustomerId(file,customerID);
			return "File is uploaded successfully";
		}else {
			return "No customer is existed with the customer-ID";
		}
	}
}
