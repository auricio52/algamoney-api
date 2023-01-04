package com.algaworks.algamoneyapi.repositories.launch;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

public class LaunchFilter {
    private String description;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate startExpirationDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate endExpirationDate;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getStartExpirationDate() {
        return startExpirationDate;
    }

    public void setStartExpirationDate(LocalDate startExpirationDate) {
        this.startExpirationDate = startExpirationDate;
    }

    public LocalDate getEndExpirationDate() {
        return endExpirationDate;
    }

    public void setEndExpirationDate(LocalDate endExpirationDate) {
        this.endExpirationDate = endExpirationDate;
    }
}
