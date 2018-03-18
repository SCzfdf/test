package com.wfly.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.wfly.model.Advertising;
import com.wfly.model.Company;
import com.wfly.model.DataList;
import com.wfly.model.DecorateCompany;
import com.wfly.model.ForeignExhibition;
import com.wfly.model.Invitation;
import com.wfly.model.Mbh_Brand;
import com.wfly.model.Mbh_Company;
import com.wfly.model.PackMaterials;
import com.wfly.model.Resume;
import com.wfly.model.School;
import com.wfly.model.Shoptransfer;

public interface DataDao {
	int selectDataCount(@Param("category")int category,@Param("type")int type);

	//Companys==============================================
	List<Company> selectCompanysByTnN(@Param("category")int category,@Param("type")int type,@Param("num")int num);
	Company selectCompanyById(@Param("dataId")Long dataId);

	//======================================================
	//Invitation============================================
	List<Invitation> selectInvitationByTnN(@Param("num")int num);
	Invitation selectInvitationById(Long dataId);

	//======================================================
	//Resume================================================
	List<Resume> selectResumeByNum(@Param("num")Integer num);
	Resume selectResumeById(Long dataId);

	//MbhCompanys=============================================
	List<Mbh_Company> selectMbhCompanysByNum(@Param("num")Integer num);
	Mbh_Company selectMbhCompanyById(Long dataId);

	//School=================================================
	List selectSchoolsByNum(@Param("num")Integer num);
	School selectSchoolById(Long dataId);

	//MbhBrand==============================================
	List selectMbhBrandByNum(@Param("dl")DataList dl);
	Resume selectMbhBrandById(Long dataId);

	//Logistics=============================================
	int selectDataCountLogistics(@Param("start")String start, @Param("end")String end);
	List seleceDataListLogistics(@Param("start")String start, @Param("end")String end, @Param("num")Integer num);

	//DecorateCompany=======================================
	List selectDecorateCompanyByNum(@Param("dl")DataList dl);
	DecorateCompany selectDecorateCompanyById(Long dataId);

	//Advertising===========================================
	List selectAdvertisingByNum(@Param("dl")DataList dl);
	Advertising selectAdvertisingById(Long dataId);

	//PackMaterials=========================================
	List selectPackMaterialsByNum(@Param("dl")DataList dl);
	PackMaterials selectPackMaterialsById(Long dataId);

	//ShopTransfer==========================================
	List selectShoptransferByNum(@Param("dl")DataList dl);
	Shoptransfer selectShoptransferById(Long dataId);

	//ForeignExhibition======================================
	List selectForeignExhibitionByNum(@Param("dl")DataList dl);
	ForeignExhibition selectForeignExhibitionById(Long dataId);
	
	//Information============================================
	int  selectInformationCount(@Param("dl")DataList dl);
	List selectInformationList(@Param("dl")DataList dl);
	
	//======================================================
	//======================================================
	//======================================================
	//======================================================
	//======================================================
	//======================================================
	//快递网点=================================================
	int selectDataCountExpressage(@Param("county")String county, @Param("town")String town);
	List seleceDataListExpressage(@Param("county")String county, @Param("town")String town, @Param("num")Integer num);

	

	

	






}