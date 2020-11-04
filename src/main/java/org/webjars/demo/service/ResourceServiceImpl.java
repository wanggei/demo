package org.webjars.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import org.webjars.demo.dao.BaseDao;
import org.webjars.demo.dao.ResourceDao;
import org.webjars.demo.model.Resource;

@Service("resourceService")
public class ResourceServiceImpl extends BaseServiceImpl<Resource> implements ResourceService {
	
	@Autowired
	private ResourceDao resourceDao;
	
	@Override
	public BaseDao baseDao() {
		
		return resourceDao;
	}

	@Override
	public void initPermisstions(List<String> resources) {
		
		int num=0;
		
		for(String path:resources) {
			
			num=resourceDao.getSelectByResId(path);
			
			if(num==0) {
				this.add(new Resource(path));
			}
			
		}
		
	}

}
