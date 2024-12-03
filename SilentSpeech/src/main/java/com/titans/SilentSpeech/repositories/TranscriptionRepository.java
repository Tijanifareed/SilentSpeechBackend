package com.titans.SilentSpeech.repositories;

import com.titans.SilentSpeech.entities.Transcription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TranscriptionRepository extends JpaRepository<Transcription, Long> {
}
