package com.titans.SilentSpeech.entities;


import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class AppUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$",
            message = "Email is not valid")
    @NotEmpty(message = "Email is required")
    private String email;
    private String name;
    @NotEmpty(message = "password is required")
    @Size(min = 6, message = "Password must be at least 6 characters")
    private String password;
    private String phoneNumber;
    private String prefereredLanguage;
}
