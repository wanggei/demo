package org.webjars.demo.service;

import java.util.List;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import org.webjars.demo.dao.BaseDao;
import org.webjars.demo.dao.Borrow_ReadDao;
import org.webjars.demo.model.Borrow_Read;

@Service("borrow_ReadService")
public class Borrow_ReadServiceImpl extends BaseServiceImpl<Borrow_Read> implements Borrow_ReadService{

	
	@Autowired
	private Borrow_ReadDao borrow_Read;
	
	@Override
	public BaseDao baseDao() {
		
		return borrow_Read;
	}

	@Override
	public List<Borrow_Read> selectUsername(String username) {

		List<Borrow_Read> borrow =borrow_Read.selectUsinfo(username);
		
		return borrow;
	}

	@Override
	public void updateByBookName(String bookname ,String username) {
		
		borrow_Read.updateByBe(bookname,username);
		
	}

	@Override
	public void addInfo(Object[] objects) {

		borrow_Read.addInfo(objects);
	}

}
