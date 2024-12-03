package com.titans.SilentSpeech.entities;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
public class AudioSession {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long userId;
    private String audioUrl;
    private String audioFormat;
    private String status;
    private LocalDateTime startedAt;
    private Long transcriptionId;
}
