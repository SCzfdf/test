package com.wfly.dao;

import java.util.List;

import bak.MenuList;

public interface TestDao {
    
    List<MenuList> selectByMenu(Integer menu);
    
    List<MenuList> selectByMenuToType(Integer menu, Integer type);

	List<MenuList> selectHotMenuTop4();
}