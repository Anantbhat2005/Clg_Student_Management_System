package com.student.sms.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="student_details")
@Getter
@Setter
@ToString
@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudDet 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name="Register_Number")
	private String regnum;
	
	@Column(name="Name")
	private String name;
	
	@Column(name="Gender")
	private String gender;
	
	@Column(name="Date_Of_Birth")
	private String dob;
	
	@Column(name="Email")
	private String email;
	
	@Column(name="Students_Phno")
	private String snum;
	
	@Column(name="Class")
	private String sclass;
	
	@Column(name="Branch")
	private String branch;
	
	@Column(name="Address")
	private String address;
	
	@Column(name="Fathers_Name")
	private String fname;
	
	@Column(name="Mothers_Name")
	private String mname;
	
	@Column(name="Fathers_Occ")
	private String focc;
	
	@Column(name="Mothers_Occ")
	private String mocc;
	
	@Column(name="Parents_Email")
	private String pemail;
	
	@Column(name="Parents_Phno")
	private String pnum;

}
