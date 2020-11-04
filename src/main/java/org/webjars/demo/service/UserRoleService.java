package org.webjars.demo.service;

import org.apache.ibatis.annotations.Param;


import org.webjars.demo.model.UserRole;

public interface UserRoleService extends BaseService<UserRole>{
	void deleteByUid(@Param("uid") Integer id);
}
