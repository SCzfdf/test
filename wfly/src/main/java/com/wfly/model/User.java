package com.wfly.model;

import java.sql.Timestamp;

/*** 
 * 用户表 
 *  
 */  
public class User{  
/*
CREATE TABLE `User` (
`user_id`  bigint(20) NOT NULL AUTO_INCREMENT COMMENT '用户id' ,
`login_name`  varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户登陆名' ,
`nick_name`  varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '昵称' ,
`real_name`  varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`id_number`  varchar(24) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`user_pwd`  varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '登陆密码' ,
`trade_pwd`  varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '交易密码-备用' ,
`qq`  int(16) NULL DEFAULT NULL ,
`email`  varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`phone`  int(16) NULL DEFAULT NULL ,
`province`  varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '省份' ,
`city`  varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`create_time`  datetime NULL DEFAULT NULL ,
`updata_time`  datetime NULL DEFAULT NULL ,
`last_login_time`  datetime NULL DEFAULT NULL ,
`login_fail_count`  int(2) NULL DEFAULT NULL COMMENT '登陆密码错误次数' ,
`status`  int(2) NULL DEFAULT NULL COMMENT '状态  1：正常  0：待审核 2：冻结  3：删除 4:密码输入错误次数过多 冻结' ,
`salt`  varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '盐' ,
PRIMARY KEY (`user_id`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
AUTO_INCREMENT=13111919213
ROW_FORMAT=DYNAMIC
;
	 */

	private Long user_id;//图片用AES加密过的id作为作为图片名
	private String login_name;//登录名
	private String nick_name;//昵称
	private String real_name;//真名
	private String id_number;//身份证
	private String user_pwd;//密码
	private String trade_pwd;//二级密码
	private int qq;
	private String email;
	private String phone;
	private String province;
	private String city;
	private Timestamp create_time;
	private Timestamp updata_time;
	private Timestamp last_login_time;
	private int status;
	private String salt;
	
	
	public Long getUser_id() {
		return user_id;
	}
	public void setUser_id(Long user_id) {
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
	public String getReal_name() {
		return real_name;
	}
	public void setReal_name(String real_name) {
		this.real_name = real_name;
	}
	public String getId_number() {
		return id_number;
	}
	public void setId_number(String id_number) {
		this.id_number = id_number;
	}
	public String getUser_pwd() {
		return user_pwd;
	}
	public void setUser_pwd(String user_pwd) {
		this.user_pwd = user_pwd;
	}
	public String getTrade_pwd() {
		return trade_pwd;
	}
	public void setTrade_pwd(String trade_pwd) {
		this.trade_pwd = trade_pwd;
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
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getSalt() {
		return salt;
	}
	public void setSalt(String salt) {
		this.salt = salt;
	}
	
	
	
}  