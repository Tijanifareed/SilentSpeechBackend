package com.titans.SilentSpeech.services.userservice;

import com.titans.SilentSpeech.dtos.request.CreateAccountRequest;
import com.titans.SilentSpeech.dtos.response.CreateAccountResponse;
import com.titans.SilentSpeech.entities.AppUser;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserServiceImplTest {

    @Autowired
    private UserService userService;

    @Test
    public void testThatAUserCanCreateAccount(){
        CreateAccountRequest request = new CreateAccountRequest();
        request.setUserName("Christianah");
        request.setPassword("christian");
        request.setEmail("freddie2810@gmail.com");
        request.setPhoneNumber("09099887464");
        request.setPrefereredLanguage("English");
        CreateAccountResponse response = userService.createUserAccount(request);
        assertThat(response).isNotNull();
    }
}