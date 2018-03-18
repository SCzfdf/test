package com.wfly.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.wfly.dao.DataDao;
import com.wfly.model.Advertising;
import com.wfly.model.Company;
import com.wfly.model.DataList;
import com.wfly.model.DecorateCompany;
import com.wfly.model.ForeignExhibition;
import com.wfly.model.Invitation;
import com.wfly.model.Mbh_Company;
import com.wfly.model.PackMaterials;
import com.wfly.model.Resume;
import com.wfly.model.School;
import com.wfly.model.Shoptransfer;
import com.wfly.service.DataService;

@Service("dataService")
public class DataServiceImpl implements DataService{

	@Resource //自动注入  
	private DataDao dataDao;
	
	@Override	
	public int selectDataCount(int category,int type) {
		return dataDao.selectDataCount(category,type);
	}
	@Override
	public int selectDataCountLogistics(String start, String end) {
		return dataDao.selectDataCountLogistics(start, end);
	}
	//列表==============================================================================================
//	@Override
//	public List<Company> selectCompanysByNum(int category, int type, int num) {
//		return dataDao.selectCompanysByTnN(category,type, num);
//	}
//	
//	@Override
//	public List<Invitation> selectInvitationByNum(int num) {
//		return dataDao.selectInvitationByTnN(num);
//	}
//	
//	@Override
//	public List<Resume> selectResumeByNum(Integer dataId) {
//		return dataDao.selectResumeByNum(dataId);
//	}
//	@Override
//	public List<Mbh_Company> selectMbhCompanysByNum(Integer category, Integer type, Integer num) {
//		return dataDao.selectMbhCompanysByNum(num);
//	}
//	@Override
//	public List<School> selectSchoolsByNum(Integer category, Integer type, Integer num) {
//		return dataDao.selectSchoolsByNum(num);
//	}
//	@Override
//	public List<Mbh_Brand> selectMbhBrandByNum(Integer category, Integer type, Integer num) {
//		return dataDao.selectMbhBrandByNum(num);
//	}
	/**
	 * TODO 通过传表名的方法动态传参,再动态获取返回值 取消一系列的DAO  现在太不优雅了
	 */
	public List seleceDataList(Integer category, Integer type, Integer num) {
		List reList = null;
		
		DataList dl = new DataList(category,type,num);
		
		switch (category) {
		case 1:
			reList = dataDao.selectMbhBrandByNum(dl);
			break;
		case 2:
			reList = dataDao.selectSchoolsByNum(num);
			break;
		case 3:
			if(type==1) {
				
			}else if(type==2) {
				reList = dataDao.selectForeignExhibitionByNum(dl);
			}else {
				reList = dataDao.selectMbhCompanysByNum(num);
			}
			break;
		case 4:
			if(type==1) {
				
			}else if(type==2) {
				reList = dataDao.selectForeignExhibitionByNum(dl);
			}
			break;
		case 5:
			if(type==1)
				reList = dataDao.selectInvitationByTnN(num);
			else
				reList = dataDao.selectResumeByNum(num);
			break;
		case 6:
			reList = dataDao.selectShoptransferByNum(dl);
			break;
		case 7:
			break;
		case 8:
			if(type==1)
				reList = dataDao.selectCompanysByTnN(category,type, num);
			else if(type==2)
				reList = dataDao.selectDecorateCompanyByNum(dl);
			else if(type==3)
				reList = dataDao.selectAdvertisingByNum(dl);
			else if(type==4)
				reList = dataDao.selectPackMaterialsByNum(dl);
			break;
		default:
			break;
		}
		return reList;
	}
	
	@Override
	public List seleceDataListLogistics(String start, String end, Integer num) {
		return dataDao.seleceDataListLogistics(start,end,num);
	}
	//=================================================================================================
	
	//详情==============================================================================================
	@Override
	public Company selectCompanysById(Long DataId) {
		return dataDao.selectCompanyById(DataId);
	}

	@Override
	public Invitation selectInvitationById(Long dataId) {
		return dataDao.selectInvitationById(dataId);
	}
	@Override
	public Resume selectResumeById(Long dataId) {
		return dataDao.selectResumeById(dataId);
	}
	@Override
	public Mbh_Company selectMbhCompanyById(Long dataId) {
		return dataDao.selectMbhCompanyById(dataId);
	}
	@Override
	public School selectSchoolById(Long dataId) {
		return dataDao.selectSchoolById(dataId);
	}
	@Override
	public Resume selectMbhBrandById(Long dataId) {
		return dataDao.selectMbhBrandById(dataId);
	}
	@Override
	public DecorateCompany selectDecorateCompanyById(Long dataId) {
		return dataDao.selectDecorateCompanyById(dataId);
	}
	@Override
	public Advertising selectAdvertisingById(Long dataId) {
		return dataDao.selectAdvertisingById(dataId);
	}
	@Override
	public PackMaterials selectPackMaterialsById(Long dataId) {
		return dataDao.selectPackMaterialsById(dataId);
	}
	@Override
	public Shoptransfer selectShoptransferById(Long DataId) {
		return dataDao.selectShoptransferById(DataId);
	}

	//快递网点========================================================================================
	@Override
	public int selectDataCountExpressage(String county, String town) {
		return dataDao.selectDataCountExpressage(county, town);
	}
	@Override
	public List seleceDataListExpressage(String county, String town, Integer num) {
		return dataDao.seleceDataListExpressage(county,town,num);
	}
	//==================================================================================================
	@Override
	public ForeignExhibition selectForeignExhibitionById(Long dataId) {
		return dataDao.selectForeignExhibitionById(dataId);
	}
	//==================================================================================================
	//==================================================================================================
	//==================================================================================================
	//==================================================================================================
	//==================================================================================================
	//==================================================================================================
	//==================================================================================================
	//==================================================================================================
	//==================================================================================================
	//==================================================================================================
	//==================================================================================================
	//==================================================================================================
	//==================================================================================================
	//==================================================================================================
	//==================================================================================================
	//==================================================================================================
	//==================================================================================================
	//==================================================================================================
	//==================================================================================================
	//==================================================================================================
	@Override
	public int getInformationCount(DataList dl) {
		return dataDao.selectInformationCount(dl);
	}
	@Override
	public List getInformationList(DataList dl) {
		return dataDao.selectInformationList(dl);
	}
	

	

}
