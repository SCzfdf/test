package com.wfly.model;

public class Role{  

	private int id;
	private String name;
	private Boolean available;//是否可用
	private int type;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Boolean getAvailable() {
		return available;
	}
	public void setAvailable(Boolean available) {
		this.available = available;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}


}  
