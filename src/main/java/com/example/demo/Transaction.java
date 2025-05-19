package com.example.demo;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Data
public class Transaction {
    @Id
    private String primaryId;
    private String secondaryId;
    private String eventType;
    private LocalDateTime eventDate;

    public Transaction() {
    }
    public Transaction(String primaryId, String secondaryId, String eventType, LocalDateTime eventDate) {
        this.primaryId = primaryId;
        this.secondaryId = secondaryId;
        this.eventType = eventType;
        this.eventDate = eventDate;
    }




    // Getters and Setters


    public String getPrimaryId() {
        return primaryId;
    }

    public void setPrimaryId(String primaryId) {
        this.primaryId = primaryId;
    }

    public String getSecondaryId() {
        return secondaryId;
    }

    public void setSecondaryId(String secondaryId) {
        this.secondaryId = secondaryId;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public LocalDateTime getEventDate() {
        return eventDate;
    }

    public void setEventDate(LocalDateTime eventDate) {
        this.eventDate = eventDate;
    }
}
