package bak;

import java.util.List;

public interface TestService {
	
//	void printName(TestPo t);
//	
//	void insert(TestPo t);
//	
//	TestPo getTestPo(int id);

	List<MenuList> getMenuList(int menu);
	
	List<MenuList> getMenuList(int menu,int type);

	List<MenuList> getHotList();
}
