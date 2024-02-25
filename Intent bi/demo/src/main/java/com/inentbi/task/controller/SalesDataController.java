package com.inentbi.task.controller;

import java.awt.print.Pageable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.inentbi.task.model.SalesData;
import com.inentbi.task.services.SalesDataService;

@RestController
@CrossOrigin
public class SalesDataController {

	@Autowired
	private SalesDataService salesDataService;

	@GetMapping("/{id}")
	public ResponseEntity<SalesData> getSalesDataById(@PathVariable Long id) {
		SalesData salesData = salesDataService.getSalesDataById(id);
		if (salesData != null) {
			return ResponseEntity.ok().body(salesData);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<SalesData> updateSalesData(@PathVariable Long id, @RequestBody SalesData salesData) {
		SalesData updatedSalesData = salesDataService.updateSalesData(id, salesData);
		if (updatedSalesData != null) {
			return ResponseEntity.ok().body(updatedSalesData);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteSalesData(@PathVariable Long id) {
		salesDataService.deleteSalesData(id);
		return ResponseEntity.noContent().build();
	}

	@GetMapping("/data")
	public ResponseEntity<List<SalesData>> getAllSalesData(@RequestParam(defaultValue = "0") Integer page,
			@RequestParam(defaultValue = "10") Integer size, @RequestParam(defaultValue = "id") String sortBy,
			@RequestParam(defaultValue = "asc") String direction) {

		PageRequest pageable = PageRequest.of(page, size, Sort.Direction.fromString(direction), sortBy);

		Page<SalesData> salesDataPage = salesDataService.getAllSalesData(pageable);

		List<SalesData> salesDataList = salesDataPage.getContent();
		return ResponseEntity.ok().header("X-Total-Count", String.valueOf(salesDataPage.getTotalElements()))
				.body(salesDataList);
	}
}
