package bak;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/data/")
@Controller  
public class IndexController {
//	http://localhost:8080/wfly/data/index2.html?category=1
	@RequestMapping("index2.do")
	public String toIndex2(HttpServletRequest request) {
		String category = (String)request.getParameter("category");
		
		String type = (String)request.getParameter("type");
//		String string = JSONObject.fromObject("{\"code\":\"200\",\"data\":[{\"h\":\"1\",\"c\":\"5\"}]}").toString();
//		System.out.println(string);
//		return JSONObject.fromObject("{\"code\":\"200\",\"data\":[{\"h\":\"1\",\"c\":\"5\"}]}");
		System.out.println("s");
//		return "redirect:"+request.getContextPath()+"/data/index2.html";
//		System.out.println(modelAndView.set);
//		return new ModelAndView("index2.html");
//		return "data/index2";
		return "forward:/WEB-INF/view/data/index2.html?category="+category+"&typr="+type;
	}
	
	@RequestMapping("1.do")
	public String test(HttpServletRequest request) {
		
		System.out.println("1");
//		String string = JSONObject.fromObject("{\"code\":\"200\",\"data\":[{\"h\":\"1\",\"c\":\"5\"}]}").toString();
//		System.out.println(string);
//		return JSONObject.fromObject("{\"code\":\"200\",\"data\":[{\"h\":\"1\",\"c\":\"5\"}]}");
//		return "redirect:"+request.getContextPath()+"/data/index2.html";
//		System.out.println(modelAndView.set);
//		return new ModelAndView("index2.html");
		return "data/details";
	}
}
