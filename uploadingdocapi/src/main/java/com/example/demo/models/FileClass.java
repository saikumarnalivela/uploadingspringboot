package com.example.demo.models;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "File_upload_Table")
public class FileClass {
	
	@Id
	@GeneratedValue(generator="system-uuid")
	@GenericGenerator(name="system-uuid", strategy = "uuid")
	@Column(name="Document_Id")
	private String documnetId;
	
	@Column(name = "Document_Name")
	private String documentName;
	
	@Column(name = "Document_Type")
	private String fileType;
	
	@Lob
	@Column(name = "data")
	private byte[] data;
	
	@Column(name = "customer_ID")
	private Long CustomerId;

	public String getDocumnetId() {
		return documnetId;
	}

	public void setDocumnetId(String documnetId) {
		this.documnetId = documnetId;
	}

	public String getDocumentName() {
		return documentName;
	}

	public void setDocumentName(String documentName) {
		this.documentName = documentName;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public byte[] getData() {
		return data;
	}

	public void setData(byte[] data) {
		this.data = data;
	}

	public FileClass(String documentName, String fileType, byte[] data) {
		super();
		this.documentName = documentName;
		this.fileType = fileType;
		this.data = data;
	}

	public FileClass() {
		super();
	}

	public Long getCustomerId() {
		return CustomerId;
	}

	public void setCustomerId(Long customerId) {
		CustomerId = customerId;
	}

	public FileClass(String documentName, String fileType, byte[] data, Long customerId) {
		super();
		this.documentName = documentName;
		this.fileType = fileType;
		this.data = data;
		CustomerId = customerId;
	}
	
	
	
	

	
	
	
	

}
