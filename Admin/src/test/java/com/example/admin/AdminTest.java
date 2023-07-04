package com.example.admin;

import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.example.admin.Entity.Admin;
import com.example.admin.repo.AdminRepo;
import com.example.admin.service.AdminServiceImpl;

@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
@SpringBootTest
public class AdminTest {

	@Mock
	private AdminRepo adminRepo;

	@InjectMocks
	private AdminServiceImpl adminServiceImpl;

	private Admin admin;

	@BeforeEach
	public void setUp() {
		// Initialize test data
		admin = new Admin();
		admin.setId(12);
		admin.setEmail("ravi@gmail.com");
		admin.setAddress("kakinada");
		admin.setUserName("Ravi varma");
		admin.setMobileNumber("9876543210");
		admin.setPassword("ravi33");

	}

	@Test
    public void testCreateAdmin() {
        // Mock repository method
        when(adminRepo.save(any(Admin.class))).thenReturn(admin);
        
        // Call the service method
        //User addedUser = userService.addUser(testUser);
        Admin ad = adminServiceImpl.createAdmin(admin);

        // Verify the result
        assertNotNull(ad);
        assertEquals(admin, ad);

        
        // Verify that the repository method was called
        verify(adminRepo, times(1)).save(admin);
    }

}
