package com.prasad.Login.Service;

import com.prasad.Login.Dto.AdminDto;
import com.prasad.Login.Dto.AdminResponseDto;
import com.prasad.Login.Dto.LoginResponseDto;

public interface LoginService {
	
	
	public AdminResponseDto AdminLogin(AdminDto adminDto);
	public LoginResponseDto login(com.prasad.Login.Dto.LoginDto loginDto);
	
	
	
	
	

}
