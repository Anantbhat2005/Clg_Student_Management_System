package com.student.sms.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;



@Entity
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int  id;
	
	
	@NotBlank(message = "Name cannot be blank !!")
	@Size(min=2, max=20, message = "Min 2 and max 20 characters are allowed !!")
	private String name;
	
	@NotBlank(message = "Email cannot be blank !!")
	@Email(message = "Invalid email format")
	private String email;
	
	@NotBlank(message = "Password cannot be blank !!")
	@Size(min=8, message = "Password must be at least 8 characters long")
	private String password;
	
	@NotBlank(message = "Please confirm your password !!")
	private String confirmPassword;
	
	@NotBlank(message = "Please select your department !!")
	private String department;
	
	/*
	 * @ManyToMany
	 * 
	 * @JoinColumn(name = "deptcode") private Department department;
	 */
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
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
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	
	public String getConfirmPassword() {
		return confirmPassword;
	}
	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}
	
	
	

	
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	/*
	 * public Department getDepartment() { return department; } public void
	 * setDepartment(Department department) { this.department = department;
	 }*/
	public User(int id, String name, String email, String password,String confirmPassword) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
		this.confirmPassword = confirmPassword;
	
	}
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", email=" + email + ", password=" + password
				+ ", confirmPassword=" + confirmPassword + "]";
	}
	
	
	
	
	
}
