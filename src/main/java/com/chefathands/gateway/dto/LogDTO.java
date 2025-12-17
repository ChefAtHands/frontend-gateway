package com.chefathands.gateway.dto;

import java.time.LocalDateTime;

public class LogDTO {
    private Long id;
    private String level;        // INFO, WARN, ERROR
    private String message;
    private LocalDateTime timestamp;

    // Constructors
    public LogDTO() {}

    public LogDTO(String level, String message) {
        this.level = level;
        this.message = message;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}