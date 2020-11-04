package org.webjars.demo.service;

import java.util.List;


import org.webjars.demo.model.Resource;

public interface ResourceService extends BaseService<Resource>{

	void initPermisstions(List<String> resources);

}
