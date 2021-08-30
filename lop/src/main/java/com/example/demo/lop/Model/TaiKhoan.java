package com.example.demo.lop.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

@Entity
@Table(name = "user")
public class TaiKhoan {
   
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	
	@Column
	@Size(max = 20, message = "Ten nguoi dang ky khong duoc qua 20 ky tu!")
	private String fullName;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

	@Column(length = 100)
	@Size(max = 100, message ="Ten email khong duoc qua 100 ky tu!" )
	private String email;
	
	@Column
	@Size(max = 20, message = "Ten tai khoan khong duoc qua 20 ky tu!")
	private String userName;
	
	@Column
	@Size(max = 20, message = "Ten password khong duoc qua 20 ky tu!")
	private String passWord;
}
