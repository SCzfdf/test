package bak;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.wfly.model.Company;

import bak.MenuList;

public interface DataDao {

	List<Company>selectCompanysByNum();
}