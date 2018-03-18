package bak;

import java.sql.Timestamp;

/**
 * 返回主页列表信息
 * @author Administrator
 *
 */
public class MenuList {
	private Integer category;//类别>大
	private Integer type;//类型>小
	private String url;
	private Timestamp time;
	private String header;
	private String imageUrl;
	private String address;
	private String phone;
	private String context;
	private String Spare;//备用
	private Boolean ifHot;
	
	
	public Boolean getIfHot() {
		return ifHot;
	}
	public void setIfHot(Boolean ifHot) {
		this.ifHot = ifHot;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Timestamp getTime() {
		return time;
	}
	public void setTime(Timestamp time) {
		this.time = time;
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
	public String getHeader() {
		return header;
	}
	public void setHeader(String header) {
		this.header = header;
	}
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
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
	public String getContext() {
		return context;
	}
	public void setContext(String context) {
		this.context = context;
	}
	public String getSpare() {
		return Spare;
	}
	public void setSpare(String spare) {
		Spare = spare;
	}
	
}
