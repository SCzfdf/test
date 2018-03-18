package bak;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/user")
@Controller
public class UserController {
	@RequestMapping("/")
	public String test(HttpServletRequest request) {
		System.out.println("ss");
		return "user/user";
	}
	
	
	@RequestMapping("/user.do")
	public String toUser(HttpServletRequest request) {
		return "user/user";
	}
}
