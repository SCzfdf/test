package com.wfly.controller;

import javax.annotation.Resource;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wfly.model.SimpleUser;
import com.wfly.util.ResultUtil;

import net.sf.json.JSONObject;

@RequestMapping("/user")
@Controller
public class UserController {
	
	@Resource
	ResultUtil reSult;

	//user/getUser.do
	@RequestMapping("/getUser.do")
	@ResponseBody
	public JSONObject getUser() {

		Session session = SecurityUtils.getSubject().getSession();
		
		long startAccessTime = session.getStartTimestamp().getTime();
		if( System.currentTimeMillis() - startAccessTime <= 3*60*60*1000L) {
			SimpleUser simpleUser = (SimpleUser)session.getAttribute("simpleUser");
			
			return simpleUser==null ?  reSult.WARN().D_SET("/login.html").getInstance(): reSult.SUCCESS(simpleUser) ;
		}else {
			session.removeAttribute("simpleUser");
			return reSult.ERROR().D_SET("/login.html").getInstance();
		}
	}
}
