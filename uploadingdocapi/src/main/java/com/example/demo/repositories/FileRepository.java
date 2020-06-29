package com.example.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.models.FileClass;


public interface FileRepository extends JpaRepository<FileClass, String>{
	
	
	/*
	 * @Query("select a from File_upload_Table a where a.customer_ID = 1") public
	 * List<FileClass> findByCustomerId(@Param("customerId") long customerId);
	 */
}
