package com.inentbi.task.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SalesData {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String market;
	private String country;
	private String product;
	private String discountBand;
	private BigDecimal unitsSold;
	private BigDecimal manufacturingPrice;
	private BigDecimal salePrice;
	private BigDecimal grossSales;
	private BigDecimal sales;
	private BigDecimal cogs;
	private BigDecimal profit;
	private Date date;
	private int monthNumber;
	private String monthName;
	private int year;

}
