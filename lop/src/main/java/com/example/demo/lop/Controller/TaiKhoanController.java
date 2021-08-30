package com.example.demo.lop.Controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.lop.Model.TaiKhoan;
import com.example.demo.lop.Respository.TaiKhoanRespository;
import com.google.gson.Gson;

@CrossOrigin
@RestController
public class TaiKhoanController {
	@Autowired
	TaiKhoanRespository tkRespository;

	@GetMapping(value = "/user", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<TaiKhoan> getUser() {
		return tkRespository.findAll();
	}
	
	@PostMapping(value = "/user/add")
	public ResponseEntity<String> addUser(@RequestBody String user){
		Gson gson = new Gson();
		TaiKhoan tkmoi = gson.fromJson(user, TaiKhoan.class);
		ValidatorFactory vlf = Validation.buildDefaultValidatorFactory();
		Validator vlt = vlf.getValidator();
		List<ConstraintViolation<TaiKhoan>> val = vlt.validate(tkmoi).stream().collect(Collectors.toList());
		
		if(val.size() == 0) {
			tkRespository.save(tkmoi);
			return new ResponseEntity<String>("add user success!", HttpStatus.OK);
		}
		return new ResponseEntity<String>(val.get(0).getMessage(),HttpStatus.BAD_REQUEST);
	}

}
