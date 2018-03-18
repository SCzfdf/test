package com.wfly.model;

public class DataList {
	private Integer category;
	private Integer type;
	private Integer num;

	
	
	public DataList() {
		super();
	}
	public DataList(Integer category, Integer type, Integer num) {
		super();
		this.category = category;
		this.type = type;
		this.num = num;
	}
	
	public Integer getCategory() {
		return category;
	}
	public void setCategory(Integer category) {
		this.category = category;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public Integer getNum() {
		return num;
	}
	public void setNum(Integer num) {
		this.num = num;
	}
	
	
}
