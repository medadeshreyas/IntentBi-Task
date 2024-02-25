package com.inentbi.task.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.inentbi.task.services.SalesDataServiceImpl;

@RestController
public class UploadController {

	@Autowired
	private SalesDataServiceImpl salesService;

	@PostMapping("/api/sales/upload")
	public ResponseEntity<String> uploadSalesData(@RequestParam("file") MultipartFile file) {
		return ResponseEntity.ok().body(salesService.saveSalesData(file));
	}

}
