package com.wfly.util;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Scope(value="request",proxyMode=ScopedProxyMode.TARGET_CLASS)
@Component
public class ResultUtil {
	private JSONObject reSult = new JSONObject();
	private static String CODE = "code";
	private static String MSG = "msg";
	private static String DATA = "data";
	
	/**
	 * 205
	 */
	public ResultUtil C_ERROR() {
		reSult.put(CODE, 205);
		return this;
	}
	/**
	 * 201
	 */
	public ResultUtil C_WARN() {
		reSult.put(CODE, 201);
		return this;
	}
	/**
	 * 200
	 */
	public ResultUtil C_SUCCESS() {
		reSult.put(CODE, 200);
		return this;
	}
	public ResultUtil C_ERROR(String code) {
		reSult.put(CODE, code);
		return this;
	}
	
	
	/**
	 * 内部错误
	 */
	public ResultUtil M_ERROR() {
		reSult.put(MSG,"内部错误");
		return this;
	}
	/**
	 * 操作错误
	 */
	public ResultUtil M_WARN() {
		reSult.put(MSG,"操作错误");
		return this;
	}
	/**
	 * 登陆失败
	 */
	public ResultUtil M_LOG_ERROR() {
		reSult.put(MSG, "登陆失败");
		return this;
	}
	/**
	 * 密码错误
	 */
	public ResultUtil M_LOG_PAW_ERROR() {
		reSult.put(MSG, "密码错误");
		return this;
	}
	/**
	 * 账号不存在
	 */
	public ResultUtil M_LOG_USE_ERROR() {
		reSult.put(MSG, "账号不存在");
		return this;
	}
	public ResultUtil M_ERROR(String msg) {
		reSult.put(MSG, msg);
		return this;
	}
	/**
	 * 登陆成功
	 */
	public ResultUtil M_SUCCESS() {
		reSult.put(MSG,"操作成功");
		return this;
	}
	
	/**
	 * 登出页面
	 */
	public ResultUtil D_LOGOUT() {
		reSult.put(DATA, "/login/logout.do");
		return this;
	}
	/**
	 * 无权限
	 */
	public ResultUtil D_PERMISSION() {
		reSult.put(DATA, "/errorpage/405.html");
		return this;
	}
	/**
	 * 内部错误
	 */
	public ResultUtil D_ERROR() {
		reSult.put(DATA, "/errorpage/500.html");
		return this;
	}
	public ResultUtil D_SET(Object obj) {
		reSult.put(DATA, obj);
		return this;
	}
	
	
//	public ResultUtil SUCCESS() {
//		C_SUCCESS();
//		M_SUCCESS();
//	}
	/**
	 * 设置code,msg为成功
	 * @return
	 */
	public ResultUtil SUCCESS() {
		C_SUCCESS();
		M_SUCCESS();
		return this;
	}
	/**
	 * 设置code,msg为错误
	 * @return
	 */
	public ResultUtil ERROR() {
		C_ERROR();
		M_ERROR();
		return this;
	}
	/**
	 * 设置code,msg为警告
	 * @return
	 */
	public ResultUtil WARN() {
		C_WARN();
		M_WARN();
		return this;
	}
	
	public JSONObject getInstance() {
		return reSult;
	}
	
	public JSONObject SUCCESS(Object obj) {
		C_SUCCESS();
		M_SUCCESS();
		D_SET(obj);
		return getInstance();
 	}
	
	public ResultUtil setEntry(String k,Object v) {
		reSult.put(k, v);
		return this;
	}
}
