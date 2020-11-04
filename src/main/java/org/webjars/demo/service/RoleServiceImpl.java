package org.webjars.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import org.webjars.demo.dao.BaseDao;
import org.webjars.demo.dao.RoleDao;
import org.webjars.demo.model.Role;

@Service("roleService")
public class RoleServiceImpl extends BaseServiceImpl<Role> implements RoleService{

	@Autowired
	private RoleDao roleDao;
	
	@Override
	public BaseDao baseDao() {
		// TODO Auto-generated method stub
		return roleDao;
	}

	

}
