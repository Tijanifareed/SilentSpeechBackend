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
    private String status;
    private LocalDateTime startedAt;
    private LocalDateTime endedAt;
    private String audioUrl;
    private String audioStatus;
    private Long transcriptionId;
}
