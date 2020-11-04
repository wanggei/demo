package org.webjars.demo.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;


import org.springframework.ui.Model;
import org.webjars.demo.dao.BaseDao;
import org.webjars.demo.dao.UserDao;
import org.webjars.demo.dao.UserRoleDao;
import org.webjars.demo.model.User;

@Service("userService")
public class UserServiceImpl extends BaseServiceImpl<User>implements UserService {
	
	@Autowired
	private UserDao userDao;
	
	@Autowired 
	private UserRoleDao userRoleDao;


	
	@Override
	public BaseDao baseDao() {
		
		return userDao;
	}

	@Override
	public void addUser(User u, Integer[] id) {
		
		

		this.addForNotMath(new Object[] {"username","password","email","phone","enabled","add_data"},
						   new Object[] {u.getUsername(),u.getPassword(),u.getEmail(),u.getPhone(),1,new Date()});
		//根据刚刚插入的数据的用户名获取对应的数据
		/*User user=userDao.getUserByName(u.getUsername());*/
		User userByName = userDao.getUserByName(u.getUsername());

		for(Integer ids: id) {
			userRoleDao.add("t_user_role",new Object[]{null,ids,userByName.getId()});
		}
		
	}

	@Override
	public List<User> getSelectAll() {
		
		return userDao.getSelectAll();
	}

	@Override
	public PageInfo<User> getUserPage(Integer pageNum, Integer pageSize) {
		Page<User> page=PageHelper.startPage(pageNum, pageSize);
		
		List<User> userList=userDao.getSelectAll();
		
		
		PageInfo<User> info=new PageInfo<>(userList);
		
		return info;
	}

	@Override
	public User selectByUserId(Integer uid) {
		
		return userDao.selectByUserId(uid);
	}

	@Override
	public void upUserRoleById(User u,Integer[] uid) {
		
		//更新相对应id的数据信息
		
		this.update(u);
		
		//更新t_user_role中的数据
		 	//1,先删除u.id对应的数据
			//2 添加刚刚更新的数据
		userRoleDao.deleteByUid(u.getId());
		
		for(Integer ids: uid) {
			userRoleDao.add("t_user_role",new Object[] {null,ids,u.getId()});
		}
		
	}

	@Override
	public void batchDelUserById(Integer[] integers) {
		
		for(Integer ids: integers) {
			userRoleDao.deleteByUid(ids);
			
			this.delete(ids);
			
		}
		
	}

	//模糊查询
	@Override
	public PageInfo<User> getUserPagese(Integer pageSize, Integer pageNum, String stuid) {
		
		
			Page<User> page=PageHelper.startPage(pageSize, pageNum);
			
			
			
			List<User> userList=userDao.blurSelUserByUe(stuid);
			
			
			PageInfo<User> info=new PageInfo<>(userList);
			
			return info;
			
		}

	@Override
	public User login(String username) {


		User user=userDao.getUserByUe(username);//判断是否有这个用户名


		return user;
	}

//密码更新操作
	@Override
	public void updatePsByUn(String password, String username) {
		userDao.updateByun(password, username);

	}

}
