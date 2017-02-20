package com.cidic.sdx.model;

import java.io.Serializable;

public class Student implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;
    private String name;
    private Gender gender;
    private int grade;
    
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Gender getGender() {
		return gender;
	}
	public void setGender(String gender) {
		if (gender.equals(Gender.MALE)){
			this.gender = Gender.MALE;
		}
		else{
			this.gender = Gender.FEMALE;
		}
	}
	public int getGrade() {
		return grade;
	}
	public void setGrade(int grade) {
		this.grade = grade;
	}
}
