package org.webjars.demo.model;

public class Resource {

	
	private Integer id;
	
	private String path;
	


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public Resource(String path) {
		
		this.path = path;
	}
	
	public Resource() {
		
		
	}

	@Override
	public String toString() {
		return "Resource [id=" + id + ", path=" + path + "]";
	}
	
	
}
