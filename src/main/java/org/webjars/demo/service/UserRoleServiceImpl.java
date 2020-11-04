package org.webjars.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import org.webjars.demo.dao.BaseDao;
import org.webjars.demo.dao.UserRoleDao;
import org.webjars.demo.model.UserRole;

@Service("userRoleService")
public class UserRoleServiceImpl extends BaseServiceImpl<UserRole> implements UserRoleService{

	@Autowired
	
	private UserRoleDao userRoleDao;
	
	@Override
	public BaseDao baseDao() {
		
		return userRoleDao;
	}

	@Override
	public void deleteByUid(Integer id) {
		
		userRoleDao.deleteByUid(id);
		
	}

	
	
}
