package com.example.demo.lop.Controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.lop.Model.HocSinh;
import com.example.demo.lop.Model.Lop;
import com.example.demo.lop.Respository.HocSinhRespository;
import com.example.demo.lop.Respository.LopRespository;
import com.google.gson.Gson;

@CrossOrigin
@RestController
public class LopController {

	@Autowired
	HocSinhRespository hsRespository;
	@Autowired
	LopRespository lrRespository;

	@GetMapping(value = "/lop", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Lop> getLop() {
		// System.out.println(lrRespository.findAll());
		return lrRespository.findAll();

	}

	private Boolean KtraLopTonTai(int lopId) {
		Optional<Lop> lop = lrRespository.findById(lopId);
		Optional<Lop> op = Optional.empty();
		if (lop == op) {
			return false;
		}
		return true;
	}

	private void XoaHocSinhThuocLop(int lopId) {
		List<HocSinh> hs = hsRespository.findAll().stream().filter(x -> x.getLop().getId() == lopId)
				.collect(Collectors.toList());
		hs.forEach(x -> hsRespository.deleteAll(hs));
	}

	@PostMapping(value = "/lop/them", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> themLop(@RequestBody String lop) {
		Gson gson = new Gson();
		Lop lopmoi = gson.fromJson(lop, Lop.class);
		ValidatorFactory vlf = Validation.buildDefaultValidatorFactory();
		Validator vlt = vlf.getValidator();
		List<ConstraintViolation<Lop>> val = vlt.validate(lopmoi).stream().collect(Collectors.toList());

		if (val.size() == 0) {
			lrRespository.save(lopmoi);
			return new ResponseEntity<String>("Add sucess!", HttpStatus.OK);
		}
		return new ResponseEntity<String>(val.get(0).getMessage(), HttpStatus.BAD_REQUEST);

	}

	@PutMapping(value = "/lop/sua")
	public ResponseEntity<String> suaLop(@RequestBody String lop) {
		Gson gson = new Gson();
		Lop lopsua = gson.fromJson(lop, Lop.class);
		if (!KtraLopTonTai(lopsua.getId())) {
			return new ResponseEntity<String>("Lop khong ton tai!", HttpStatus.BAD_REQUEST);

		}
		Lop lopCurrent = lrRespository.findById(lopsua.getId()).get();
         
		lopCurrent.setHoTen(lopsua.getHoTen());

		lopCurrent.setSiSo(lopsua.getSiSo());
		ValidatorFactory vlf = Validation.buildDefaultValidatorFactory();
		Validator vlt = vlf.getValidator();
		List<ConstraintViolation<Lop>> vio = vlt.validate(lopCurrent).stream().collect(Collectors.toList());

		if (vio.size() == 0) {

			lrRespository.save(lopCurrent);
			return new ResponseEntity<String>("Update success!",HttpStatus.OK);

		}
		return new ResponseEntity<String>(vio.get(0).getMessage(),HttpStatus.BAD_REQUEST);
	}

	@DeleteMapping(value = "/lop/xoa", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> xoaLop(@RequestParam int id) {
			if (!KtraLopTonTai(id)) {
				//System.out.println(lrRespository.findById(id).get().getHoTen());
				//lrRespository.deleteById(id);
				return new ResponseEntity<String>("Lop khong ton tai!",HttpStatus.BAD_REQUEST);
			}
			XoaHocSinhThuocLop(id);
			lrRespository.deleteById(id);
			return new ResponseEntity<String>("Delete Success!",HttpStatus.OK);

	}
	
	
	@GetMapping(value = "/lop/timkiemlop")
	public List<Lop> TimKiemLop(@RequestParam String hoTen) {
		List<Lop> lstlop = new ArrayList<Lop>();
		for (Lop x : lrRespository.findAll()) {

			if (x.getHoTen().toLowerCase().replaceAll("\\s+", "").contains(hoTen.toLowerCase())) {
				lstlop.add(x);
			} else {
				System.out.println("No find data!");
			}
		}
		return lstlop;
	}

}
