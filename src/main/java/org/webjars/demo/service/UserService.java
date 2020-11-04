package org.webjars.demo.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.github.pagehelper.PageInfo;


import org.webjars.demo.model.User;

public interface UserService extends BaseService<User>{
	
	public void addUser(User u, Integer[] id);
	
	//获取所有用户信息，关联了role表
	public List<User> getSelectAll();
	
	//获取所有用户信息，并且实现分页
	
	public PageInfo<User> getUserPage(Integer pagesize, Integer pagenum);
	
	
	//根据信息id去获取对应的用户信息，然后进行更新
	public User selectByUserId(Integer uid);

	public void upUserRoleById(User u, Integer[] uid);

	public void batchDelUserById(Integer[] integers);

	//模糊查询
	PageInfo<User> getUserPagese(Integer pagesize, Integer pagenum, String stuid);

	//登录操作
	public User login(String username);

	public void updatePsByUn(String password, String username);
}
