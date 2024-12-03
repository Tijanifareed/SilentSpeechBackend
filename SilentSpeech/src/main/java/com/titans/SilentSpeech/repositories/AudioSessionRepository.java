package com.titans.SilentSpeech.repositories;

import com.titans.SilentSpeech.entities.AudioSession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AudioSessionRepository extends JpaRepository<AudioSession, Long> {
}
