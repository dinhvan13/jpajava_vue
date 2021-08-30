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
import com.google.gson.GsonBuilder;

@CrossOrigin
@RestController
public class HocSinhController {
	@Autowired
	HocSinhRespository hsRespository;
	@Autowired
	LopRespository lRespository;

	@GetMapping(value = "/hocsinh", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<HocSinh> getHocSinh() {
		return hsRespository.findAll();
	}

	private Boolean KtraHSTonTai(int id) {
		Optional<HocSinh> op = Optional.empty();
		Optional<HocSinh> hs = hsRespository.findById(id);
		if (hs == op) {
			return false;
		}
		return true;
	}

	private Boolean KtraLopTonTai(int id) {
		Optional<Lop> op = Optional.empty();
		Optional<Lop> lop = lRespository.findById(id);
		if (lop == op) {
			return false;
		}
		return true;
	}

	public void CapNhatSiSo(int lopID) {
		Lop lop = lRespository.findById(lopID).get();
		int siSo = 0;
		for (HocSinh hs : hsRespository.findAll()) {
			if (hs.getLop().getId() == lopID) {
				siSo++;
			}
		}
		lop.setSiSo(siSo);
		lRespository.save(lop);
	}

	public boolean KiemTra(int lopID) {
		Lop lp = lRespository.findById(lopID).get();
		int siSo = lp.getSiSo() + 1;
		lp.setSiSo(siSo);
		ValidatorFactory valfac = Validation.buildDefaultValidatorFactory();
		Validator vldtor = valfac.getValidator();
		Set<ConstraintViolation<Lop>> violation = vldtor.validate(lp);

		violation.forEach(x -> {
			System.out.println(x.getMessage());

		});
		if (violation.size() == 0) {
			return true;
		}
		return false;
	}

	@PostMapping(value = "/hocsinh/them", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> ThemHocSinh(@RequestBody String hocSinh) {
		Gson gs = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		HocSinh hsm = gs.fromJson(hocSinh, HocSinh.class);
		ValidatorFactory valfc = Validation.buildDefaultValidatorFactory();
		Validator vltor = valfc.getValidator();
		List<ConstraintViolation<HocSinh>> vio = vltor.validate(hsm).stream().collect(Collectors.toList());

		if (vio.size() != 0) {
			return new ResponseEntity<String>(vio.get(0).getMessage(), HttpStatus.BAD_REQUEST);
		}
		if (!KtraLopTonTai(hsm.getLop().getId())) {
			return new ResponseEntity<String>("Lop khong ton tai!", HttpStatus.BAD_REQUEST);
		}
		/*
		 * vio.forEach(x->{ System.out.println(x.getMessage());
		 * 
		 * }); try { if (vio.size() == 0){ if (KiemTra(hsm.getLop().getId())){
		 */
		hsRespository.save(hsm);
		CapNhatSiSo(hsm.getLop().getId());
		return new ResponseEntity<String>("Them thanh cong!", HttpStatus.OK);

	}

	@PutMapping(value = "/hocsinh/sua", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> SuaHocSinh(@RequestBody String hocSinh) {
		Gson gson = new Gson();
		HocSinh hsht = gson.fromJson(hocSinh, HocSinh.class);
		Integer lopCuID = hsRespository.findById(hsht.getId()).get().getLop().getId();
		if (!KtraHSTonTai(hsht.getId())) {
			return new ResponseEntity<>("Hoc sinh khong ton tai!", HttpStatus.BAD_REQUEST);
		}

		ValidatorFactory vlf = Validation.buildDefaultValidatorFactory();
		Validator vlt = vlf.getValidator();
		List<ConstraintViolation<HocSinh>> vio1 = vlt.validate(hsht).stream().collect(Collectors.toList());
		if (vio1.size() != 0) {
			return new ResponseEntity<String>(vio1.get(0).getMessage(), HttpStatus.BAD_REQUEST);
		}

		if (!KtraLopTonTai(hsht.getLop().getId())) {
			return new ResponseEntity<String>("Lop khong ton tai!", HttpStatus.BAD_REQUEST);
		}

		// HocSinh hsCurrent = hsRespository.findById(hsht.getId()).get();
		// int lopID1 = hsCurrent.getLop().getId();
		// hsCurrent.setHoTen(hsht.getHoTen());
		// hsCurrent.setNamSinh(hsht.getNamSinh());
		// hsCurrent.setQueQuan(hsht.getQueQuan());
		hsRespository.save(hsht);
		// CapNhatSiSo(hsht.getLop().getId());
		CapNhatSiSo(hsht.getLop().getId());
		CapNhatSiSo(lopCuID);
		// CapNhatSiSo(lopID1);
		return new ResponseEntity<String>("Cap nhat thanh cong!", HttpStatus.OK);
	}

	@DeleteMapping(value = "/hocsinh/xoa", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> XoaHocSinh(@RequestParam int id) {
		try {
			if (!hsRespository.findById(id).get().getHoTen().isEmpty()) {
				int lopID = hsRespository.findById(id).get().getLop().getId();
				hsRespository.deleteById(id);
				CapNhatSiSo(lopID);
				return new ResponseEntity<String>("Xoa thanh cong!", HttpStatus.OK);
			}
			return new ResponseEntity<String>("Xoa that bai!", HttpStatus.BAD_REQUEST);

		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<String>("Xoa that bai!", HttpStatus.BAD_REQUEST);
		}
	}
	

	@PutMapping(value = "/hocsinh/chuyenlop")
	public void ChuyenLop(@RequestParam int hocSinhID, int lopID) {
		HocSinh hsht = hsRespository.findById(hocSinhID).get();
		Lop lopc = hsht.getLop();
		hsht.setLop(lRespository.findById(lopID).get());
		CapNhatSiSo(lopc.getId());
		CapNhatSiSo(lopID);
	}

	@GetMapping(value = "/hocsinh/timkiem")
	public List<HocSinh> TimKiem(@RequestParam String hoTen, @RequestParam int lopID) {
		List<HocSinh> lstHocSinh = hsRespository.findAll();
		//for (HocSinh x : hsRespository.findAll()) {
		
		   if(hoTen != "")
		   {
			   lstHocSinh = lstHocSinh.stream().filter(c->c.getHoTen().toLowerCase().replace("\\s+", "").contains(hoTen.toLowerCase())).collect(Collectors.toList());
		   }
		   if (lopID > 0)
		   {
			   lstHocSinh = lstHocSinh.stream().filter(c->c.getLop().getId() == lopID).collect(Collectors.toList());
		   }
			
				//else if (lopID > 0) {
//				lstHocSinh = hsRespository.findAll().stream().filter(a -> a.getLop().getId() == lopID)
//						.collect(Collectors.toList());
//
//			}
			// if (x.getLop().getId() == lopID &&
			// x.getHoTen().toLowerCase().replaceAll("\\s+","").contains(hoTen.toLowerCase()))
			// {
	//		lstHocSinh.add(x);

		//}
		// System.out.println(lstHocSinh);
		return lstHocSinh;
	}

	@GetMapping(value = "/hocsinh/timkiemten")
	public List<HocSinh> TimKiemHocSinh(@RequestParam String hoTen) {
		List<HocSinh> lsths = new ArrayList<HocSinh>();
		for (HocSinh x : hsRespository.findAll()) {

			if (x.getHoTen().toLowerCase().replaceAll("\\s+", "").contains(hoTen.toLowerCase())) {
				lsths.add(x);
			} else {
				System.out.println("No find data!");
			}
		}
		return lsths;
	}

}
