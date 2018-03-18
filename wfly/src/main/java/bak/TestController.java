package bak;

import java.util.List;

import javax.annotation.Resource;

import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@RequestMapping("/test")
@Controller  
public class TestController {
	@Resource
	TestService testService;

	//	@RequestMapping("/testPage.do")
	//	public String viewTestPage(){
	//		System.out.println("view test page!");
	//		testService.printName(new TestPo());
	//		return "test";
	//	}

	@RequestMapping("/t.do")
	@ResponseBody
	public JSONObject test() {
		System.err.println("s");
		String string = JSONObject.fromObject("{\"code\":\"200\",\"data\":[{\"h\":\"1\",\"c\":\"5\"}]}").toString();
		System.out.println(string);
		return JSONObject.fromObject("{\"code\":\"200\",\"data\":[{\"h\":\"1\",\"c\":\"5\"}]}");
	}

	@RequestMapping(value="/{menu}/indexList.do",method=RequestMethod.GET)
	@ResponseBody
	public JSONObject getMenuList(@PathVariable int menu) {
		System.err.println("getMenuList:"+menu);
		//		MenuList [] menuList = new MenuList [1];
		List<MenuList> menuList = testService.getMenuList(menu);

		//		ResponseData<MenuList> d = new ResponseData<>();
		//		d.setCode(200);
		//		d.setData(menuList);
		JSONArray re = new JSONArray();
		for (MenuList m : menuList) {
			JSONObject fromObject = JSONObject.fromObject(m);
			re.add(fromObject);
		}

		System.out.println(re.toString());
		JSONObject res = new JSONObject();
		res.element("code", "200");
		res.element("data", re);
		return res;
	}

	@RequestMapping(value="/{menu}/{type}/indexList.do",method=RequestMethod.GET)
	@ResponseBody
	@RequiresRoles(value="user")
	public JSONObject getMenuList(@PathVariable int menu,@PathVariable int type) {
		System.err.println("getMenuList:"+menu+"|"+type);
		//		MenuList [] menuList = new MenuList [1];
		List<MenuList> menuList = testService.getMenuList(menu,type);

		//		ResponseData<MenuList> d = new ResponseData<>();
		//		d.setCode(200);
		//		d.setData(menuList);
		JSONArray re = new JSONArray();
		for (MenuList m : menuList) {
			JSONObject fromObject = JSONObject.fromObject(m);
			re.add(fromObject);
		}

		System.out.println(re.toString());
		JSONObject res = new JSONObject();
		res.element("code", "200");
		res.element("data", re);
		return res;
	}


	/**
	 * 获取热门资讯
	 * 共4*4条
	 * @return
	 */
	@RequestMapping(value="hotList.do",method=RequestMethod.GET)
	@ResponseBody
	public JSONObject getHotList() {
		System.err.println("getHotList:");
		List<MenuList> hotList = testService.getHotList();

		JSONArray re = new JSONArray();
		for (MenuList m : hotList) {
			JSONObject fromObject = JSONObject.fromObject(m);
			re.add(fromObject);
		}

		System.out.println(re.toString());
		JSONObject res = new JSONObject();
		res.element("code", "200");
		res.element("data", re);

		return res;
	}

	@RequestMapping(value="isRole.do",method=RequestMethod.GET)
	@ResponseBody
	public JSONObject testRole() {
		
		return null;
	}
}
