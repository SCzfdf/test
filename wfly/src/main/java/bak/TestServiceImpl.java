package bak;

import java.util.List;

import org.springframework.stereotype.Service;

import com.wfly.dao.TestDao;
@Service("testService")
public class TestServiceImpl implements TestService{

    private TestDao testDao; 
	
//	public void printName(TestPo t) {
//		if(t==null){
//			return;
//		}
//		System.out.println("name="+t.getName());
//	}
//
//	public void insert(TestPo t) {
//		int result=testDao.insert(t);
//		System.out.println("执行插入后");
//		if (result>0) {
//			System.out.println("插入成功！result="+result);
//		}else{
//			System.out.println("插入失败！result="+result);
//		}
//	}
//
//	public TestPo getTestPo(int id) {
//		return testDao.selectByPrimaryKey(id);
//	}

	
	public List<MenuList> getMenuList(int menu) {
		return testDao.selectByMenu(menu);
	}

	public List<MenuList> getMenuList(int menu, int type) {
		return testDao.selectByMenuToType(menu,type);
	}
	
	
	public List<MenuList> getHotList() {
		return testDao.selectHotMenuTop4();
	}

}
