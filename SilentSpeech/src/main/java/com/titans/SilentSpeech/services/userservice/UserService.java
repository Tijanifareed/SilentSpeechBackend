package com.titans.SilentSpeech.services.userservice;

import com.titans.SilentSpeech.Exceptions.EmailNotFoundException;
import com.titans.SilentSpeech.Exceptions.IncorrectPasswordException;
import com.titans.SilentSpeech.Exceptions.UserNotFoundException;
import com.titans.SilentSpeech.dtos.request.*;
import com.titans.SilentSpeech.dtos.response.*;

public interface UserService {
    SignUpResponse signUpUser(SignUpRequest signUpRequest);

    GetUserResponse getUserById(GetUserRequest getUserRequest) throws UserNotFoundException;

    UpdateProfileResponse updateUserAccount(UpdateProfileRequest updateProfileRequest, Long id) throws UserNotFoundException;

    DeleteAccountResponse deleteAccount(DeleteAccountRequest deleteAccountRequest) throws UserNotFoundException;

    LoginResponse loginUser(LoginRequest loginRequest) throws EmailNotFoundException, IncorrectPasswordException;
}


