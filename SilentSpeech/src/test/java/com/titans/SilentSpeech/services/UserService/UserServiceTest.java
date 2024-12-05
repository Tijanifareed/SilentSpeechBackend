package com.titans.SilentSpeech.services.UserService;

import com.titans.SilentSpeech.Exceptions.EmailNotFoundException;
import com.titans.SilentSpeech.Exceptions.IncorrectPasswordException;
import com.titans.SilentSpeech.Exceptions.UserNotFoundException;
import com.titans.SilentSpeech.dtos.request.*;
import com.titans.SilentSpeech.dtos.response.*;
import com.titans.SilentSpeech.services.userservice.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Test
    public void testSignUpUser() {
        SignUpRequest signUpRequest = new SignUpRequest();
        signUpRequest.setEmail("salako@gmail.com");
        signUpRequest.setPassword("123456");
        signUpRequest.setPhoneNumber("08012133345");
        signUpRequest.setName("salako");
        signUpRequest.setPrefereredLanguage("English");

        SignUpResponse signUpResponse = userService.signUpUser(signUpRequest);
        assertNotNull(signUpResponse);
        assertNotNull(signUpResponse.getId());
        assertEquals(signUpResponse.getMessage(), "Account created successfully");
    }

    @Test
    @Sql(scripts = {"/db/data.sql"})
    public void testGetUserById() throws UserNotFoundException {
        GetUserRequest getUserRequest = new GetUserRequest();
        getUserRequest.setUserId(2L);
        GetUserResponse getUserResponse = userService.getUserById(getUserRequest);
        assertNotNull(getUserResponse);
        assertEquals(getUserResponse.getName(), "Jane Smith");
    }

    @Test
    @Sql(scripts = {"/db/data.sql"})
    public void testUserCanUpdateProfile() throws UserNotFoundException {
        Long id = 2L;
        UpdateProfileRequest updateProfileRequest = new UpdateProfileRequest();
        updateProfileRequest.setPhoneNumber("09078564436");
        updateProfileRequest.setEmail("ashakun@gmail.com");

        UpdateProfileResponse updateprofileResponse = userService.updateUserAccount(updateProfileRequest, id);
        assertNotNull(updateprofileResponse);
        assertEquals(updateprofileResponse.getMessage(), "Account updated successfully");
    }

    @Test
    @Sql(scripts = {"/db/data.sql"})
    public void testUserCanDeleteProfile() throws UserNotFoundException {
        DeleteAccountRequest deleteAccountRequest = new DeleteAccountRequest();
        deleteAccountRequest.setUserId(3L);
        DeleteAccountResponse deleteAccountResponse = userService.deleteAccount(deleteAccountRequest);
        assertNotNull(deleteAccountResponse);
        assertEquals(deleteAccountResponse.getMessage(), "Account deleted successfully");
    }

    @Test
    @Sql(scripts = {"/db/data.sql"}) public void testUserLogin() throws UserNotFoundException, IncorrectPasswordException, EmailNotFoundException {
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setEmail("john.doe@example.com");
        loginRequest.setPassword("password123");
        LoginResponse loginResponse = userService.loginUser(loginRequest);
        assertNotNull(loginResponse);
        assertEquals("Login successful", loginResponse.getMessage());
    }
}
