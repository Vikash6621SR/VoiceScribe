package com.voicescribe.backend.controller;

public record TranscriptionSaveRequest(
        String title,
        String text,
        String language,
        Integer durationSeconds
) {
}
