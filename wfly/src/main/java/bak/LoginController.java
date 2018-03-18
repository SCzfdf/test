package bak;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.ExpiredCredentialsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wfly.model.SimpleUser;
import com.wfly.service.LoginService;

import net.sf.json.JSONObject;

@Controller
@RequestMapping(value = "/login")
public class LoginController {
	
	@Resource
	LoginService loginService;
	
	/** 
	 * 实际的登录代码 
	 * 如果登录成功，跳转至首页；登录失败，则将失败信息反馈对用户 
	 *  
	 * @param request 
	 * @param model 
	 * @return 
	 * @throws IOException 
	 */  
	@RequestMapping(value = "/dologin.do" ,method=RequestMethod.POST ) 
	@ResponseBody//重定向通过前端js操作,只返回路径名称     ,produces = "text/plain;charset=UTF-8"
	public JSONObject  doLogin(HttpServletRequest request , HttpServletResponse response, Model model) throws IOException { 
		String msg = "./index.html";
		JSONObject re = new JSONObject();
		
		System.out.println("dologin");
		String username = request.getParameter("u");  
		String password = request.getParameter("p");  
//		System.out.println("u:"+username+"   p:"+password);
		
		
		UsernamePasswordToken token = new UsernamePasswordToken(username, password);
		token.setRememberMe(true); 
		
		Subject subject = SecurityUtils.getSubject();
		
		try {  
			subject.login(token);
			
			re.accumulate("code", 200);
			re.accumulate("msg", msg);
			return re;
			
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
			model.addAttribute("message", msg);  
		}  
		
		re.accumulate("code", 201);
		re.accumulate("msg", msg);
		return re;  
	}
	
	@RequestMapping(value = "/isLogin.do" ) 
	@ResponseBody
	public SimpleUser isLogin(HttpServletRequest request) {
		System.out.println("isLogin");
		SimpleUser simpleUser = null;
		simpleUser = (SimpleUser) request.getSession().getAttribute("simpleUser");
		
		return simpleUser;
	}
	
	
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
//	@RequestMapping(value = "/register.do" ) 
//	@ResponseBody
//	public JSONObject register(HttpServletRequest request) {
//		System.out.println("register");
//		JSONObject re = new JSONObject();
//		
//		
//		User user = new User();
//		user.setUsername(request.getParameter("u"));
//		String sourcePassword = request.getParameter("p");
//		
//		//判断数据库是否已经存在该用户名
//		if(loginService.isRegister(user)) {
//			
//			Object result = new SimpleHash("MD5", user.getUsername()+sourcePassword, user.getUsername() , 1);
////			user.setId(result.toString());
//			user.setNickname(result.toString().substring(0,5));
//			//密码加密
//			user.setPassword(new SimpleHash("MD5", sourcePassword , user.getId() , 2).toString());
//			
//			//判断插入数据库是否成功
//			if(loginService.insertUser(user) >= 1) {
//				//赋予角色
////				loginService.addUseRoleOnId(user.getId(), 2);//1管理员,2普通用户
//				
//				
//				re.accumulate("code", 200);
//				re.accumulate("msg", "./index.html");
//				
//				Subject subject = SecurityUtils.getSubject();
//				
//				UsernamePasswordToken token = new UsernamePasswordToken(user.getUsername(), sourcePassword);  
//				subject.login(token);
//				
//				
//				return re;
//			}else {
//				re.accumulate("msg", "注册失败");
//			}
//		}else {
//			re.accumulate("msg", "注册失败,账号已存在");
//		}
//		re.accumulate("code", 201);
//		
//		return re;
//	}
}
