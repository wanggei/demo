package org.webjars.demo.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface ResourceDao extends BaseDao{

	int getSelectByResId(@Param("path") String path);

}
