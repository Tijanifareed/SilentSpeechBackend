package com.titans.SilentSpeech.Controllers;

import com.titans.SilentSpeech.Exceptions.EmailNotFoundException;
import com.titans.SilentSpeech.Exceptions.IncorrectPasswordException;
import com.titans.SilentSpeech.Exceptions.UserNotFoundException;
import com.titans.SilentSpeech.dtos.request.*;
import com.titans.SilentSpeech.dtos.response.*;
import com.titans.SilentSpeech.services.userservice.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<SignUpResponse> signUpUser(@RequestBody SignUpRequest signUpRequest) {
        SignUpResponse response = userService.signUpUser(signUpRequest);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/user")
    public ResponseEntity<GetUserResponse> getUserById(GetUserRequest getUserRequest) throws UserNotFoundException{
        GetUserResponse response = userService.getUserById(getUserRequest);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/updateProfile")
    public ResponseEntity<UpdateProfileResponse> updateUserAccount(@RequestBody UpdateProfileRequest updateProfileRequest, @PathVariable Long id) throws UserNotFoundException {
        UpdateProfileResponse response = userService.updateUserAccount(updateProfileRequest, id);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/deleteAccount")
    public ResponseEntity<DeleteAccountResponse> deleteAccount(@RequestBody DeleteAccountRequest deleteAccountRequest) throws UserNotFoundException {
        DeleteAccountResponse response = userService.deleteAccount(deleteAccountRequest);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> loginUser(@RequestBody LoginRequest loginRequest) throws EmailNotFoundException, IncorrectPasswordException, UserNotFoundException {
        LoginResponse response = null;
        try {
            response = userService.loginUser(loginRequest);
        } catch (com.titans.SilentSpeech.Exceptions.IncorrectPasswordException e) {
            throw new RuntimeException(e);
        }
        return ResponseEntity.ok(response);
    }
}
