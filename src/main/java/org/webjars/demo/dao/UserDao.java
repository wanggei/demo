package org.webjars.demo.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


import org.springframework.stereotype.Repository;
import org.webjars.demo.model.User;

@Mapper
@Repository
public interface UserDao extends BaseDao{
	
	//根据用户名获取相对应的数据
	public User getUserByName(@Param("username") String username);
	
	//根据user的id查询到关联的role
	public User getUserByUid(@Param("uid") Integer uid);
	
	//获取所有数据
	
	public List<User> getSelectAll();
	
	
	//根据信息id去获取对应的用户信息，然后进行更新
	
	public User selectByUserId(@Param("uid") Integer uid);
	
	//模糊查询
	
	public List<User> blurSelUserByUe(@Param("username") String username);
	
	//判断用户名
	public User getUserByUe(@Param("username") String username);
	
	//修改密码
	public void updateByun(@Param("password") String password, @Param("username") String username);
	
	
}
