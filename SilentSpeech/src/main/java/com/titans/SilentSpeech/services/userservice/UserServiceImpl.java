package com.titans.SilentSpeech.services.userservice;


import com.titans.SilentSpeech.Exceptions.EmailNotFoundException;
import com.titans.SilentSpeech.Exceptions.IncorrectPasswordException;
import com.titans.SilentSpeech.Exceptions.UserNotFoundException;
import com.titans.SilentSpeech.dtos.request.*;
import com.titans.SilentSpeech.dtos.response.*;
import com.titans.SilentSpeech.entities.AppUser;
import com.titans.SilentSpeech.repositories.AppUserRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final AppUserRepository appUserRepository;
    private final ModelMapper modelMapper;

    @Override
    public SignUpResponse signUpUser(SignUpRequest signUpRequest) {
        AppUser appUser = modelMapper.map(signUpRequest, AppUser.class);
        appUserRepository.save(appUser);
        SignUpResponse signUpResponse = new SignUpResponse();
        signUpResponse.setMessage("Account created successfully");
        signUpResponse.setId(appUser.getId());
        return signUpResponse;
    }

    @Override
    public GetUserResponse getUserById(GetUserRequest getUserRequest) throws UserNotFoundException {
        AppUser user = getUser(getUserRequest.getUserId());
        return modelMapper.map(user, GetUserResponse.class);
    }

    @Override
    public UpdateProfileResponse updateUserAccount(UpdateProfileRequest updateProfileRequest, Long id) throws UserNotFoundException {
        AppUser user = getUser(id);
        user.setName(updateProfileRequest.getName());
        user.setEmail(updateProfileRequest.getEmail());
        user.setPassword(updateProfileRequest.getPassword());
        user.setPhoneNumber(updateProfileRequest.getPhoneNumber());
        UpdateProfileResponse updateProfileResponse = new UpdateProfileResponse();
        updateProfileResponse.setMessage("Account updated successfully");
        return updateProfileResponse;
    }

    @Override
    public DeleteAccountResponse deleteAccount(DeleteAccountRequest deleteAccountRequest) throws UserNotFoundException {
        AppUser user = getUser(deleteAccountRequest.getUserId());
        appUserRepository.delete(user);
        DeleteAccountResponse deleteAccountResponse = new DeleteAccountResponse();
        deleteAccountResponse.setMessage("Account deleted successfully");
        return deleteAccountResponse;
    }

    @Override
    public LoginResponse loginUser(LoginRequest loginRequest) throws EmailNotFoundException, IncorrectPasswordException {
        AppUser user = appUserRepository.findByEmail(loginRequest.getEmail().toLowerCase())
                .orElseThrow(() -> new EmailNotFoundException(
                        String.format("Email %s not found", loginRequest.getEmail())
                ));

        if (!loginRequest.getPassword().equals(user.getPassword())) {
            throw new IncorrectPasswordException("Incorrect password");
        }

        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setMessage("Login successful");
        return loginResponse;
    }

    private AppUser getUser(Long id) throws UserNotFoundException {
        AppUser user = appUserRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(
                        String.format("User with the id %d not found", id)));
        return user;
    }


}
