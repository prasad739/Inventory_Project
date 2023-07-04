package com.prasad.Login.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.prasad.Login.Dto.AdminDto;
import com.prasad.Login.Dto.AdminResponseDto;
import com.prasad.Login.Dto.CustomerDto;
import com.prasad.Login.Dto.LoginDto;
import com.prasad.Login.Dto.LoginResponseDto;
import com.prasad.Login.Entity.Login;
import com.prasad.Login.Exception.EmailNotFoundException;
import com.prasad.Login.Exception.InvalidPasswordException;
import com.prasad.Login.Repository.LoginRepository;

@Service
public class LoginServiceImpl implements LoginService {

	@Autowired
	LoginRepository loginRepository;
	@Autowired
	RestTemplate restTemplate;

//	@Autowired
//	public LoginServiceImpl(LoginRepository loginRepository, RestTemplate restTemplate) {
//		this.loginRepository = loginRepository;
//		this.restTemplate = restTemplate;
//	}

//	@Override
//	public boolean login(Login login) {
//		boolean l = true;
//		Login newLogin = new Login();
//		String email = login.getEmail();
//		String password = login.getPassword();
//
//		try {
//			ResponseEntity<CustomerDto> responseEntity = restTemplate
//					.getForEntity("http://localhost:8082/getCustomerByEmail/" + email, CustomerDto.class);
//
//			CustomerDto customerDto = responseEntity.getBody();
//			newLogin.setEmail(customerDto.getEmail());
//
//			if (customerDto.getPassword().equals(password)) {
//
//				newLogin.setPassword(customerDto.getPassword());
//
//			} else {
//				l = false;
//
//				throw new InvalidPasswordException("Invalid password");
//			}
//
//		} catch (HttpClientErrorException ex) {
//			l = false;
//
//			throw new EmailNotFoundException("Invalid email");
//
//		}
//
//		return l;
//	}

	@Override
	public LoginResponseDto login(LoginDto loginDto) {
		LoginResponseDto newLogin = new LoginResponseDto();

		String email = loginDto.getEmail();
		String password = loginDto.getPassword();

		try {
			ResponseEntity<CustomerDto> responseEntity = restTemplate
					.getForEntity("http://localhost:8082/getCustomerByEmail/" + email, CustomerDto.class);

			CustomerDto customerDto = responseEntity.getBody();
			newLogin.setEmail(customerDto.getEmail());

			if (customerDto.getPassword().equals(password)) {
				newLogin.setLoggedIn(true);

			} else {

				throw new InvalidPasswordException("Invalid password");
			}

		} catch (HttpClientErrorException ex) {

			throw new EmailNotFoundException("Invalid email");

		}
		return newLogin;

	}

	@Override
	public AdminResponseDto AdminLogin(AdminDto adminDto) {

		AdminResponseDto ad = new AdminResponseDto();
		String email = adminDto.getEmail();
		String password = adminDto.getPassword();

		try {
			ResponseEntity<AdminDto> responseEntity = restTemplate
					.getForEntity("http://localhost:8084/getAdminByEmail/" + email, AdminDto.class);

			AdminDto adminDto2 = responseEntity.getBody();
			System.out.println(adminDto2.getEmail());

			ad.setEmail(adminDto2.getEmail());
			

			if (adminDto2.getPassword().equals(password)) {
				ad.setLoggedIn(true);
			} else {

				throw new InvalidPasswordException("Invalid password");
			}

		} catch (HttpClientErrorException ex) {

			throw new EmailNotFoundException("Invalid email");

		}
		return ad;

	}

}
