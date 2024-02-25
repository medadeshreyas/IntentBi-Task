package com.inentbi.task.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.inentbi.task.model.SalesData;

@Service
public interface SalesDataService {
	public String saveSalesData(MultipartFile salesData);

	Page<SalesData> getAllSalesData(Pageable pageable);

	SalesData getSalesDataById(Long id);

	SalesData updateSalesData(Long id, SalesData salesData);

	void deleteSalesData(Long id);
}
