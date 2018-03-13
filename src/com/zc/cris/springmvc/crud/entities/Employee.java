package com.zc.cris.springmvc.crud.entities;

public class Employee {

	private Integer id;
	private String name;
	private String email;
	private Integer age;
	private Department department;
	private String gender;

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public Employee(Integer id, String name, String email, Integer age, Department department, String gender) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.age = age;
		this.department = department;
		this.gender = gender;
	}

	public Employee() {
		super();

	}

}
