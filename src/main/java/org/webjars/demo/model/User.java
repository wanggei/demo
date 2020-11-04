package org.webjars.demo.model;

import java.util.Date;
import java.util.List;

public class User {

	private Integer id;
	
	private String username;
	
	private String password;
	
	private String email;
	
	private String phone;
	
	private Integer enabled;

	private Date add_data;
	
	private List<Role> roles;


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Integer getEnabled() {
		return enabled;
	}

	public void setEnabled(Integer enabled) {
		this.enabled = enabled;
	}

	public Date getAdd_data() {
		return add_data;
	}

	public void setAdd_data(Date add_data) {
		this.add_data = add_data;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public User() {
	}

	@Override
	public String toString() {
		return "User{" +
				"id=" + id +
				", username='" + username + '\'' +
				", password='" + password + '\'' +
				", email='" + email + '\'' +
				", phone='" + phone + '\'' +
				", enabled=" + enabled +
				", add_data=" + add_data +
				", roles=" + roles +
				'}';
	}
}
