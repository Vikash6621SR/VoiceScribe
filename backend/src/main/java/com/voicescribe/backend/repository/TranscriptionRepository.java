package com.voicescribe.backend.repository;

import com.voicescribe.backend.entity.Transcription;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TranscriptionRepository
        extends JpaRepository<Transcription, Long> {
}