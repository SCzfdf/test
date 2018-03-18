package com.wfly.controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.ExpiredCredentialsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wfly.model.SimpleUser;
import com.wfly.model.User;
import com.wfly.service.LoginService;
import com.wfly.util.ResultUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller
@RequestMapping("/login")
public class LoginController {
	
	@Resource
	LoginService loginService;
	@Resource
	ResultUtil reSult;
	
	
	/**
	 * 注册逻辑
	 * 获取前台传入的账号密码(前台密码经过一次md5加密)
	 * 检查用户名是否存在,存在继续进行
	 * 用前台账号密码作为盐,MD5加密1次用户名作为id
	 * id前5位作为用户默认昵称
	 * 随后加密密码存入数据库 和赋予默认角色
	 * 插入成功则登陆跳转,不成功返回错误信息
	 * @param request
	 * @return
	 */
//	http://localhost:8080/login/register.do
	@RequestMapping(value="/register.do" ,method=RequestMethod.POST)
	@ResponseBody
	public JSONObject register(HttpServletRequest request) {
		JSONObject reSult = new JSONObject();
		int code = 201;
		String msg = "注册失败";
		
		String userNama = request.getParameter("u");
		String sourcePwd = request.getParameter("p");
		User user = new User();
		user.setPhone(userNama);//手机是注册名
		
		boolean register = loginService.isRegister(user);
		if(register) {//查询数据库
			user.setCreate_time(new Timestamp(System.currentTimeMillis()));
			user.setUpdata_time(new Timestamp(System.currentTimeMillis()));
			user.setLast_login_time(new Timestamp(System.currentTimeMillis()));
			user.setLogin_name(userNama);
			user.setNick_name(userNama.substring(0,5));//设置前5位位昵称
			
			Object result = new SimpleHash("MD5", user.getPhone()+sourcePwd, new Date().getTime()+"" , 1);
			user.setSalt(result.toString());
			user.setUser_pwd(new SimpleHash("MD5",sourcePwd,result.toString(),2).toString());
			user.setStatus(0);
			
			if(loginService.insertUser(user) == 1) {//插入数据库
				//赋予角色
				//先不做
				
				Subject subject = SecurityUtils.getSubject();
				UsernamePasswordToken token = new UsernamePasswordToken(userNama, sourcePwd);  
				subject.login(token);
				msg = "./index.html";
				code = 200;
			}else 
				msg = "注册失败";
		}else 
			msg = "注册失败,账号已存在";
		
		reSult.accumulate("code",code);
		reSult.accumulate("msg",msg);
		
		return reSult;
	}
	
	@RequestMapping(value = "/doLogin.do" ,method=RequestMethod.POST ) 
	@ResponseBody//重定向通过前端js操作,只返回路径名称     ,produces = "text/plain;charset=UTF-8"
	public JSONObject  doLogin(HttpServletRequest request) throws IOException {
		JSONObject reSult = new JSONObject();
		String msg = "登陆失败";
		int code = 201;
		String username = request.getParameter("u");  
		String password = request.getParameter("p");  
		
		UsernamePasswordToken token = new UsernamePasswordToken(username, password);
//		token.setRememberMe(true); 
		
		Subject subject = SecurityUtils.getSubject();
		try {  
			subject.login(token);
			
			msg = "./index.html";
			code = 200;
			
		} catch (UnknownAccountException e) {  
			msg = "帐号不存在";  
		} catch (IncorrectCredentialsException e) {  
			msg = "登录密码错误";  
		} catch (ExcessiveAttemptsException e) {  
			msg = "登录失败次数过多";  
		} catch (LockedAccountException e) {  
			msg = "帐号已被锁定";  
		} catch (DisabledAccountException e) {  
			msg = "帐号已被禁用";  
		} catch (ExpiredCredentialsException e) {  
			msg = "帐号已过期";  
		} catch (UnauthorizedException e) {  
			msg = "您没有得到相应的授权！" + e.getMessage();  
//			model.addAttribute("message", msg);  
		}  
		
		reSult.accumulate("code", code);
		reSult.accumulate("msg", msg);
		return reSult; 
	}
	
//	http://localhost:8080/login/isLogin.do
	@RequestMapping(value = "/isLogin.do" ,method=RequestMethod.GET ) 
	@ResponseBody
	public JSONObject isLogin(HttpServletRequest request) throws IOException {
		System.out.println("isLogin");
		
		long startTimestamp = SecurityUtils.getSubject().getSession().getStartTimestamp().getTime();//获取session创建时间
//		Date data = new Date(startTimestamp);
		
		if( System.currentTimeMillis() - startTimestamp >= 3*60*60*1000L) {//没超过3小时
			return reSult.ERROR().getInstance();
		}else {
			SimpleUser attribute = (SimpleUser) SecurityUtils.getSubject().getSession().getAttribute("simpleUser");
			return attribute==null ?  reSult.WARN().D_SET("./login.html").getInstance(): reSult.SUCCESS(JSONObject.fromObject(attribute)) ;
		}
	}
	
//	http://localhost:8080/user/personal.html?k88aDxUrn5Z5EU6PNGQKMA==
//	@RequestMapping(value = "/personal.do" ,method=RequestMethod.GET ) 
//	public String personal(HttpServletRequest request) throws IOException {
//		System.out.println("isLogin");
//		SimpleUser simpleUser = null;
//		simpleUser = (SimpleUser) request.getSession().getAttribute("simpleUser");
//		
//		return "";
//	}
}

















