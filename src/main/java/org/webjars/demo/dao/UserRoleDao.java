package org.webjars.demo.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserRoleDao extends BaseDao{
	
	
	//根据uid去删除对应的数据
	void deleteByUid(@Param("uid") Integer id);

}
