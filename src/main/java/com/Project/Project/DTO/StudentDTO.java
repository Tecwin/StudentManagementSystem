package com.Project.Project.DTO;



import com.Project.Project.POJO.Roles;



public class StudentDTO {
	
	private Integer id;
	

	private String name;
	

	private String userName;
	

	private String password;
	
	private String phoneNo;
	

	private String address;
	

	private Roles roles;

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

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Roles getRoles() {
		return roles;
	}

	public void setRoles(Roles roles) {
		this.roles = roles;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "StudentDTO [id=" + id + ", name=" + name + ", userName=" + userName + ", password=" + password
				+ ", phoneNo=" + phoneNo + ", address=" + address + ", roles=" + roles + "]";
	}

}
