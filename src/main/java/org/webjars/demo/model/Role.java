package org.webjars.demo.model;

import java.util.List;

public class Role {
	
	private Integer id;
	
	private String name;
	
	private String code;
	
	private List<Resource> resource;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public List<Resource> getResource() {
		return resource;
	}

	public void setResource(List<Resource> resource) {
		this.resource = resource;
	}
	
	
	@Override
	public String toString() {
		return "Role [id=" + id + ", name=" + name + ", code=" + code + ", resource=" + resource + "]";
	}

	@Override
	public boolean equals(Object obj) {
		//判断俩个对象是否相同，只要判断id值是否一样去判断，所以我们重写比较方法
		
		if(this == obj )
			return true;
		if(obj==null)
			return false;
		if(!(obj instanceof Role ))
			return false;
		Role other=(Role) obj;
		
		if(id != other.id)
			return false;
		return true;
	}
	
	
}
