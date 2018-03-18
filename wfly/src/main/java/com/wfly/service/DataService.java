package com.wfly.service;

import java.util.List;

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

public interface DataService {
//	List<Company> selectCompanysByNum(int category, int type, int num);
//	
//	List<Invitation> selectInvitationByNum(int num);

	Company selectCompanysById(Long dataId);

	int selectDataCount(int category, int type);

	Invitation selectInvitationById(Long dataId);
	
//	List<Resume> selectResumeByNum(Integer num);

	Resume selectResumeById(Long dataId);

//	List<Mbh_Company> selectMbhCompanysByNum(Integer category, Integer type, Integer num);

	Mbh_Company selectMbhCompanyById(Long dataId);

//	List<School> selectSchoolsByNum(Integer category, Integer type, Integer num);

	School selectSchoolById(Long dataId);

//	List<Mbh_Brand> selectMbhBrandByNum(Integer category, Integer type, Integer num);

	List seleceDataList(Integer category, Integer type, Integer num);

	Resume selectMbhBrandById(Long dataId);

	int selectDataCountLogistics(String start, String end);

	List seleceDataListLogistics(String start, String end, Integer num);

	DecorateCompany selectDecorateCompanyById(Long dataId);

	Advertising selectAdvertisingById(Long dataId);

	PackMaterials selectPackMaterialsById(Long dataId);

	int selectDataCountExpressage(String county, String town);

	List seleceDataListExpressage(String county, String town, Integer num);

	Shoptransfer selectShoptransferById(Long DataId);

	ForeignExhibition selectForeignExhibitionById(Long dataId);

	int getInformationCount(DataList dl);

	List getInformationList(DataList dl);

	
}
