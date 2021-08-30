package com.example.demo.lop.Model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="lop")
public class Lop {
  
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getHoTen() {
		return hoTen;
	}

	public void setHoTen(String hoTen) {
		this.hoTen = hoTen;
	}

	public int getSiSo() {
		return siSo;
	}

	public void setSiSo(int siSo) {
		this.siSo = siSo;
	}

	public Set<HocSinh> getHocSinh() {
		return hocSinh;
	}

	public void setHocSinh(Set<HocSinh> hocSinh) {
		this.hocSinh = hocSinh;
	}

	@Column
	@Size(max = 10, message = "Ten lop toi da dai 10 ky tu!")
    private String hoTen;
	
	@Column
	@Max(value = 20, message = "Si so toi da la 20 hoc sinh!")
	private int siSo;
	
	@OneToMany(mappedBy = "lop")
	@JsonIgnoreProperties(value = "lop")
	Set<HocSinh> hocSinh;
	
	
	
}
