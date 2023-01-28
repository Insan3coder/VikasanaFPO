package com.Project.demo.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "COMMODITY")
public class Commodity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "SL_NO", nullable = false, unique = true)
	private Long slNo;

	@Column(name = "COMMODITY_TYPE")
	private String commodityType;

	@Column(name = "COMMODITY_NAME")
	private String commodityName;

	@Column(name = "QUANTITY")
	private Float quantity;
	
	@Column(name = "PRICE")
	private Float price;

	public Long getSlNo() {
		return slNo;
	}

	public void setSlNo(Long slNo) {
		this.slNo = slNo;
	}

	public String getCommodityType() {
		return commodityType;
	}

	public void setCommodityType(String commodityType) {
		this.commodityType = commodityType;
	}

	public String getCommodityName() {
		return commodityName;
	}

	public void setCommodityName(String commodityName) {
		this.commodityName = commodityName;
	}

	public Float getQuantity() {
		return quantity;
	}

	public void setQuantity(Float quantity) {
		this.quantity = quantity;
	}

	public Float getPrice() {
		return price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}

}
