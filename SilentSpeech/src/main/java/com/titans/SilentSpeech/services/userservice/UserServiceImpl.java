package com.titans.SilentSpeech.services.userservice;


import com.titans.SilentSpeech.dtos.request.CreateAccountRequest;
import com.titans.SilentSpeech.dtos.response.CreateAccountResponse;
import com.titans.SilentSpeech.entities.AppUser;
import com.titans.SilentSpeech.repositories.AppUserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private final AppUserRepository appUserRepository;

    public UserServiceImpl(AppUserRepository appUserRepository) {
        this.appUserRepository = appUserRepository;
    }

    @Override
    public CreateAccountResponse createUserAccount(CreateAccountRequest request) {
        AppUser  user = new AppUser();
        user.setName(request.getUserName());
        user.setEmail(request.getEmail());
        user.setPhoneNumber(request.getPhoneNumber());
        user.setPassword(request.getPassword());
        user.setPrefereredLanguage(request.getPrefereredLanguage());
        appUserRepository.save(user);
        CreateAccountResponse response = new CreateAccountResponse();
        response.setMessage("Account created Sucessfully");
        return response;
    }
}
