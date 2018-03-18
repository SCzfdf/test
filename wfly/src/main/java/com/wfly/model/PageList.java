package com.wfly.model;

/**
 * 查询List需要的参数
 * @author Administrator
 *
 */
public class PageList {
	private Integer category;
	private Integer type;
	private Integer num;
	
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
