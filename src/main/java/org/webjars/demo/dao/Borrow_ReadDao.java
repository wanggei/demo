package org.webjars.demo.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import org.springframework.stereotype.Repository;
import org.webjars.demo.model.Borrow_Read;
@Mapper
@Repository
public interface Borrow_ReadDao extends BaseDao{

	List<Borrow_Read> selectUsinfo(@Param("username") String username);

	void updateByBe(@Param("bookname") String bookname,@Param("username") String username);

	void addInfo(@Param("objects") Object[] objects);


}
