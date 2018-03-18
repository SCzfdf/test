package com.wfly.model;

import java.sql.Timestamp;

import com.wfly.util.SymmetricEncoder;

/**
 * 简单user
 * 用于返回前端
 * @author Administrator
 *
 */
public class SimpleUser {
	protected String user_id;//User因为要存数据库 所以是Long  simpleUser因为只需要和服务器交互所以用String
	protected String login_name;
	protected String nick_name;
	protected int qq;
	protected String email;
	protected String phone;
	protected String province;
	protected String city;
	protected Timestamp create_time;
	protected Timestamp updata_time;
	protected Timestamp last_login_time;
	
	public SimpleUser() {
		super();
	}
	public SimpleUser(User user) {
		super();
		this.user_id = SymmetricEncoder.AESEncode(user.getSalt(), user.getUser_id()+"");//返回加密过的id给客户端
		
		this.login_name = user.getLogin_name();
		this.nick_name = user.getNick_name();
		this.qq = user.getQq();
		this.email = user.getEmail();
		this.phone = user.getPhone().substring(0, 6)+"*****";
		this.province = user.getProvince();
		this.city = user.getCity();
		this.create_time = user.getCreate_time();
		this.updata_time = user.getUpdata_time();
		this.last_login_time = user.getLast_login_time();
	}
	
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getLogin_name() {
		return login_name;
	}
	public void setLogin_name(String login_name) {
		this.login_name = login_name;
	}
	public String getNick_name() {
		return nick_name;
	}
	public void setNick_name(String nick_name) {
		this.nick_name = nick_name;
	}
	public int getQq() {
		return qq;
	}
	public void setQq(int qq) {
		this.qq = qq;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public Timestamp getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Timestamp create_time) {
		this.create_time = create_time;
	}
	public Timestamp getUpdata_time() {
		return updata_time;
	}
	public void setUpdata_time(Timestamp updata_time) {
		this.updata_time = updata_time;
	}
	public Timestamp getLast_login_time() {
		return last_login_time;
	}
	public void setLast_login_time(Timestamp last_login_time) {
		this.last_login_time = last_login_time;
	}
	
}
