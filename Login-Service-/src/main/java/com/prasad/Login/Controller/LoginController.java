package com.prasad.Login.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.prasad.Login.Dto.AdminDto;
import com.prasad.Login.Dto.AdminResponseDto;
import com.prasad.Login.Dto.LoginDto;
import com.prasad.Login.Dto.LoginResponseDto;
import com.prasad.Login.Entity.Login;
import com.prasad.Login.Service.LoginServiceImpl;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class LoginController {

	@Autowired
	LoginServiceImpl impl;

	@PostMapping("/loginC")
	public ResponseEntity<LoginResponseDto> login(@RequestBody LoginDto loginc) {

		LoginResponseDto save = impl.login(loginc);
		return new ResponseEntity<>(save, HttpStatus.OK);

	}

	@PostMapping("/loginA")
	public ResponseEntity<AdminResponseDto> AdminLogin(@RequestBody AdminDto loginA) {

		AdminResponseDto save = impl.AdminLogin(loginA);
		return new ResponseEntity<>(save,HttpStatus.OK);

	}

}