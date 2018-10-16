package cn.zyf.spring.jdbc;

public class Employee {

	private Integer id;
	private String lastName;
	private String email;
	
	private Integer deptID;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getDeptID() {
		return deptID;
	}

	public void setDeptID(Integer deptID) {
		this.deptID = deptID;
	}

	@Override
	public String toString() {
		return "Employee [id=" + id + ", lastName=" + lastName + ", email=" + email + ", deptID=" + deptID + "]";
	}
	
}
