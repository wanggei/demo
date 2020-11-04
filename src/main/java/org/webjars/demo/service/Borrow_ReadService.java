package org.webjars.demo.service;

import java.util.List;


import com.github.pagehelper.PageInfo;
import org.apache.ibatis.annotations.Param;
import org.webjars.demo.model.Borrow_Read;

public interface Borrow_ReadService extends BaseService<Borrow_Read>{

	List<Borrow_Read> selectUsername(String username);

	void updateByBookName(String bookname,String username);

	void addInfo( Object[] objects);

}
