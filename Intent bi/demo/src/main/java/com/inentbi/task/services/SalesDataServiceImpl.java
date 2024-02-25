package com.inentbi.task.services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.inentbi.task.model.SalesData;
import com.inentbi.task.repo.SalesDataRepository;

@Service
public class SalesDataServiceImpl implements SalesDataService {

	@Autowired
	private SalesDataRepository salesDataRepo;

	public String saveSalesData(MultipartFile file) {
		if (file.isEmpty()) {
			return "Please upload an Excel file.";
		}

		try {
			Workbook workbook = WorkbookFactory.create(file.getInputStream());
			Sheet sheet = workbook.getSheetAt(0);

			for (Row row : sheet) {
				if (row.getRowNum() == 0) {
					continue;
				}
				SalesData salesData = new SalesData();
				salesData.setMarket(row.getCell(0).getStringCellValue());
				salesData.setCountry(row.getCell(1).getStringCellValue());
				salesData.setProduct(row.getCell(2).getStringCellValue());
				salesData.setDiscountBand(row.getCell(3).getStringCellValue());
				salesData.setUnitsSold(new BigDecimal(row.getCell(4).getNumericCellValue()));
				salesData.setManufacturingPrice(new BigDecimal(row.getCell(5).getNumericCellValue()));
				salesData.setSalePrice(new BigDecimal(row.getCell(6).getNumericCellValue()));
				salesData.setGrossSales(new BigDecimal(row.getCell(7).getNumericCellValue()));
				salesData.setSales(new BigDecimal(row.getCell(8).getNumericCellValue()));
				salesData.setCogs(new BigDecimal(row.getCell(9).getNumericCellValue()));
//				try {

				Cell profitCell = row.getCell(10);
				if (profitCell.getCellType() == CellType.STRING) {
					throw new IllegalStateException("Please Check Row Number :-" + (profitCell.getRowIndex() + 1)
							+ " Make sure to replace it as a Numeric value , if the field is blank or contains no profit , replace it with zero");

				}

				salesData.setProfit(new BigDecimal(row.getCell(10).getNumericCellValue()));

//				
				salesData.setDate(row.getCell(11).getDateCellValue());
				salesData.setMonthNumber((int) row.getCell(12).getNumericCellValue());
				salesData.setMonthName(row.getCell(13).getStringCellValue());
				salesData.setYear((int) row.getCell(14).getNumericCellValue());

				salesDataRepo.save(salesData);
			}
			workbook.close();
		} catch (IOException e) {
			e.printStackTrace();
			return "Failed to upload file: " + e.getMessage();
		}

		return "File uploaded successfully.";
	}

	@Override
	public SalesData getSalesDataById(Long id) {
		return salesDataRepo.findById(id).orElse(null);
	}

	@Override
	public SalesData updateSalesData(Long id, SalesData salesData) {
		SalesData existingSalesData = salesDataRepo.findById(id).orElse(null);
		if (existingSalesData != null) {
			existingSalesData.setMarket(salesData.getMarket());
			existingSalesData.setCogs(salesData.getCogs());
			existingSalesData.setCountry(salesData.getCountry());
			existingSalesData.setDate(salesData.getDate());
			existingSalesData.setDiscountBand(salesData.getDiscountBand());
			existingSalesData.setGrossSales(salesData.getGrossSales());
			existingSalesData.setManufacturingPrice(salesData.getManufacturingPrice());
			existingSalesData.setMonthName(salesData.getMonthName());
			existingSalesData.setMonthNumber(salesData.getMonthNumber());
			existingSalesData.setProduct(salesData.getProduct());
			existingSalesData.setProfit(salesData.getProfit());
			existingSalesData.setSalePrice(salesData.getSalePrice());
			existingSalesData.setSales(salesData.getSales());
			existingSalesData.setUnitsSold(salesData.getUnitsSold());
			existingSalesData.setYear(salesData.getYear());
			return salesDataRepo.save(existingSalesData);
		}
		return null;
	}

	@Override
	public void deleteSalesData(Long id) {
		salesDataRepo.deleteById(id);
	}

	@Override
	public Page<SalesData> getAllSalesData(Pageable pageable) {
	    return salesDataRepo.findAll(pageable);
	}

}
