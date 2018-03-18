package com.wfly.model;
/**
 * 公司实体
 * @author Administrator
 *
 */
public class Company{
	
	private Long id;
	private String shop_name;//店名
	private String address;//店铺的地址
	private String phone;//店铺的电话
	private String businessHours;//营业时间
	private String image_url;//图片的链接地址
	private int province;
	private int city;
	private String profile;//简介
	private int status;//状态码
	private int type;
	private Long up_date;
//	private String source_url;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getShop_name() {
		return shop_name;
	}
	public void setShop_name(String shop_name) {
		this.shop_name = shop_name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getBusinessHours() {
		return businessHours;
	}
	public void setBusinessHours(String businessHours) {
		this.businessHours = businessHours;
	}
	public String getImage_url() {
		return image_url;
	}
	public void setImage_url(String image_url) {
		this.image_url = image_url;
	}
	public int getProvince() {
		return province;
	}
	public void setProvince(int province) {
		this.province = province;
	}
	public int getCity() {
		return city;
	}
	public void setCity(int city) {
		this.city = city;
	}
	public String getProfile() {
		return profile;
	}
	public void setProfile(String profile) {
		this.profile = profile;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
//	public String getSource_url() {
//		return source_url;
//	}
//	public void setSource_url(String source_url) {
//		this.source_url = source_url;
//	}
	public Long getUp_date() {
		return up_date;
	}
	public void setUp_date(Long up_date) {
		this.up_date = up_date;
	}
	
}