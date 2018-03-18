package com.wfly.controller;

import java.lang.reflect.Method;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;
import javax.naming.spi.DirStateFactory.Result;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wfly.model.Advertising;
import com.wfly.model.Company;
import com.wfly.model.DataList;
import com.wfly.model.ForeignExhibition;
import com.wfly.model.Information;
import com.wfly.model.Invitation;
import com.wfly.model.Mbh_Company;
import com.wfly.model.Resume;
import com.wfly.model.School;
import com.wfly.model.Shoptransfer;
import com.wfly.service.DataService;
import com.wfly.util.Kuaidi100;
import com.wfly.util.ResultUtil;

import net.sf.json.JSONObject;

@RequestMapping("/data")
@Controller
public class DataController {

	@Resource
	ResultUtil reSult;

	@Resource
	DataService dataService;

	/**
	 * 用category判断查询那个表
	 * type 作为条件
	 * 用mun决定limit
	 * 
	 * @param category
	 * @param type
	 * @param num
	 * @return
	 */
	@RequestMapping("/dataList.do")
	@ResponseBody
	public JSONObject getDataList(
			@RequestParam(value="category",defaultValue="1") Integer category,
			@RequestParam(value="type",defaultValue="1") Integer type,
			@RequestParam(value="num",defaultValue="1") Integer num) {

		num = (num-1)*10;
		//查询条目数
		int totalSize = dataService.selectDataCount(category,type);

		List reList = dataService.seleceDataList(category,type, num);

		if(reList != null && reList.size() > 0) {
			return reSult.setEntry("totalSize", totalSize).setEntry("totalPage", Math.ceil(totalSize/10.0)).SUCCESS(reList);
		}else
			return reSult.ERROR().getInstance();
	}

	@RequestMapping(value="/dataListLogistics.do",method=RequestMethod.POST)
	@ResponseBody
	public JSONObject getDataListLogistics(
			@RequestParam(value="start" ,defaultValue="") String start,
			@RequestParam(value="end" ,defaultValue="") String end,
			@RequestParam(value="num",defaultValue="1") Integer num
			) {

		num = (num-1)*10;

		if(start.isEmpty() && end.isEmpty()) {
			return reSult.ERROR().getInstance();
		}else {
			//查询条目数
			int totalSize = dataService.selectDataCountLogistics(start,end);
			List reList = dataService.seleceDataListLogistics(start,end,num);
			if(reList != null && reList.size() > 0) {
				reList.get(0);
				return reSult.setEntry("totalSize", totalSize).setEntry("totalPage", totalSize/10).SUCCESS(reList);
			}else
				return reSult.ERROR().getInstance();
		}
	}

	//获取主体数据
	@RequestMapping("/getData.do")
	@ResponseBody
	public JSONObject getDataSubject(@RequestParam(value="d",defaultValue="5143219889") Long DataId) {
		Company re = dataService.selectCompanysById(DataId);

		return reData(re);
	}

	@RequestMapping("/getMbhCompany.do")
	@ResponseBody
	public JSONObject getMbhCompany(@RequestParam(value="d") Long DataId) {
		Mbh_Company re = dataService.selectMbhCompanyById(DataId);

		return reData(re);
	}

	@RequestMapping("/getSchool.do")
	@ResponseBody
	public JSONObject getSchool(@RequestParam(value="d") Long DataId) {
		School re = dataService.selectSchoolById(DataId);

		return reData(re);
	}

	@RequestMapping("/getInvitation.do")
	@ResponseBody
	public JSONObject getInvitation(@RequestParam(value="d") Long DataId) {
		Invitation re = dataService.selectInvitationById(DataId);

		return reData(re);
	}

	@RequestMapping("/getResume.do")
	@ResponseBody
	public JSONObject getResume(@RequestParam(value="d") Long DataId) {
		Resume re = dataService.selectResumeById(DataId);

		return reData(re);
	}

	@RequestMapping("/getMbhBrand.do")
	@ResponseBody
	public JSONObject getMbhBrand(@RequestParam(value="d") Long DataId) {
		Resume re = dataService.selectMbhBrandById(DataId);

		return reData(re);
	}

	@RequestMapping(value="/queryExpressage.do",method=RequestMethod.POST)
	@ResponseBody
	public JSONObject queryExpressage(
			@RequestParam(value="com",defaultValue="auto") String com,
			@RequestParam(value="num") String num
			) {
		
		if(!num.isEmpty()) {
			if("auto".equals(com)) {
				List<String> autonumberAll = Kuaidi100.AutonumberAll(num);
				for (String string : autonumberAll) {
					JSONObject query = Kuaidi100.Query(num, string);
					if("ok".equals(query.getString("message").trim())) {
						return reData(query.get("data"));
					}
				}
			}else {
				JSONObject query = Kuaidi100.Query(num, com);
				return reData(query.getJSONObject("data"));
			}
		}
		
		return reSult.ERROR().getInstance();
		
	}
	
	@RequestMapping("/getAdvertising.do")
	@ResponseBody
	public JSONObject getAdvertising(@RequestParam(value="d") Long DataId) {
		Advertising re = dataService.selectAdvertisingById(DataId);
		return reData(re);
	}
	
	@RequestMapping("/getShoptransfer.do")
	@ResponseBody
	public JSONObject getShoptransfer(@RequestParam(value="d") Long DataId) {
		Shoptransfer re = dataService.selectShoptransferById(DataId);
		return reData(re);
	}
	
	@RequestMapping("/getForeignExhibition.do")
	@ResponseBody
	public JSONObject getForeignExhibition(@RequestParam(value="d") Long DataId) {
		ForeignExhibition re = dataService.selectForeignExhibitionById(DataId);
		return reData(re);
	}
	
	@RequestMapping(value="/dataListExpressage.do",method=RequestMethod.POST)
	@ResponseBody
	public JSONObject getDataListExpressage(
			@RequestParam(value="county" ,defaultValue="") String county,
			@RequestParam(value="town" ,defaultValue="") String town,
			@RequestParam(value="num",defaultValue="1") Integer num) {
		
		//TODO 如果2个都上传了就用county的  查询的还是要用全文检索啊...
		if(!"".equals(county) && !"".equals(town)) {
			town="";
		}
		
		int totalSize = dataService.selectDataCountExpressage(county,town);
		List reList = dataService.seleceDataListExpressage(county,town,num);
		
		if(reList != null && reList.size() > 0) {
			reList.get(0);
			return reSult.setEntry("totalSize", totalSize).setEntry("totalPage", totalSize/10).SUCCESS(reList);
		}else
			return reSult.ERROR().getInstance();
	}
	
	@RequestMapping("/getInformationList.do")
	@ResponseBody
	public JSONObject getInformationList(
			@RequestParam(value="type",defaultValue="1") Integer type,
			@RequestParam(value="num",defaultValue="1") Integer num) {

		num = (num-1)*100;
		DataList dl = new DataList(null,type,num);
		//查询条目数
		//TODO:controller进行参数转换
		int totalSize = dataService.getInformationCount(dl);

		List<Information> reList = dataService.getInformationList(dl);
		Collections.sort(reList,new Comparator<Information>() {
			@Override
			public int compare(Information o1, Information o2) {
				
				String regEx="[^0-9]";  
				Pattern p = Pattern.compile(regEx);   
				//TODO:补0是为了不为空 后面还是专门加个字段吧= =
				String m1 = p.matcher(o1.getMessage()+0).replaceAll("").trim();   
				String m2 = p.matcher(o2.getMessage()+0).replaceAll("").trim(); 
				
				return new Long(m2).compareTo(new Long(m1));
			}
		});

		if(reList != null && reList.size() > 0) 
			return reSult.setEntry("totalSize", totalSize).setEntry("totalPage", Math.ceil(totalSize/100.0)).SUCCESS(reList);
		else
			return reSult.ERROR().getInstance();
	}
	
//	@RequestMapping(value="test.do")
//	@ResponseBody
//	private void test() {
//		System.out.println("s");
//	}
	
	private JSONObject reData(Object re) {
		return re!=null ? reSult.SUCCESS(re) :reSult.ERROR().getInstance();
	}

	

}


















