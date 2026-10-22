package com.voicescribe.backend.controller;

import com.voicescribe.backend.entity.Transcription;
import com.voicescribe.backend.repository.TranscriptionRepository;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/transcriptions")
public class TranscriptionController {

    private final TranscriptionRepository repository;

    public TranscriptionController(TranscriptionRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<Map<String, Object>> getAll() {
        return repository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    @GetMapping("/{id}")
    public Map<String, Object> getById(@PathVariable Long id) {
        return toResponse(findTranscription(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Map<String, Object> create(
            @RequestBody TranscriptionSaveRequest request) {

        if (request.text() == null || request.text().isBlank()) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Transcript text cannot be empty"
            );
        }

        Transcription transcription = new Transcription();
        transcription.setTitle(
                request.title() == null || request.title().isBlank()
                        ? "Untitled transcription"
                        : request.title().trim()
        );
        transcription.setTranscript(request.text().trim());
        transcription.setLanguage(
                request.language() == null || request.language().isBlank()
                        ? "English (India)"
                        : request.language().trim()
        );
        transcription.setDurationSeconds(
                request.durationSeconds() == null
                        ? 0
                        : Math.max(0, request.durationSeconds())
        );

        return toResponse(repository.save(transcription));
    }

    @PutMapping("/{id}")
    public Map<String, Object> update(
            @PathVariable Long id,
            @RequestBody TranscriptionSaveRequest request) {

        Transcription existing = findTranscription(id);

        if (request.text() == null || request.text().isBlank()) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Transcript text cannot be empty"
            );
        }

        if (request.title() != null && !request.title().isBlank()) {
            existing.setTitle(request.title().trim());
        }

        existing.setTranscript(request.text().trim());

        if (request.language() != null && !request.language().isBlank()) {
            existing.setLanguage(request.language().trim());
        }

        if (request.durationSeconds() != null) {
            existing.setDurationSeconds(
                    Math.max(0, request.durationSeconds())
            );
        }

        return toResponse(repository.save(existing));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        Transcription existing = findTranscription(id);
        repository.delete(existing);
    }

    private Transcription findTranscription(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Transcription not found"
                ));
    }

    private Map<String, Object> toResponse(Transcription item) {
        return Map.of(
                "id", item.getId(),
                "title", item.getTitle(),
                "text", item.getTranscript(),
                "transcript", item.getTranscript(),
                "language", item.getLanguage() == null
                        ? ""
                        : item.getLanguage(),
                "durationSeconds", item.getDurationSeconds() == null
                        ? 0
                        : item.getDurationSeconds(),
                "createdAt", item.getCreatedAt() == null
                        ? ""
                        : item.getCreatedAt().toString(),
                "updatedAt", item.getUpdatedAt() == null
                        ? ""
                        : item.getUpdatedAt().toString()
        );
    }
}
