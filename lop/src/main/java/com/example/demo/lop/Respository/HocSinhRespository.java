package com.example.demo.lop.Respository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.lop.Model.HocSinh;

@Repository
public interface HocSinhRespository extends JpaRepository<HocSinh, Integer> {

}
