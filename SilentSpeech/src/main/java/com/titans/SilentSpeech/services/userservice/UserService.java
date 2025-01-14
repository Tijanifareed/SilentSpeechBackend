package com.titans.SilentSpeech.services.userservice;

import com.titans.SilentSpeech.dtos.request.CreateAccountRequest;
import com.titans.SilentSpeech.dtos.response.CreateAccountResponse;

public interface UserService {
   CreateAccountResponse createUserAccount(CreateAccountRequest request);
}
