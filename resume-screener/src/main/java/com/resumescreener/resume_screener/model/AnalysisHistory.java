package com.resumescreener.resume_screener.model;


import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class AnalysisHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String resumeName;

    @Column(length = 5000)
    private String jobDescription;

    @Column(length = 5000)
    private String result;

    private LocalDateTime analyzedAt;

    // Constructors
    public AnalysisHistory() {}

    public AnalysisHistory(String resumeName, String jobDescription, String result, LocalDateTime analyzedAt) {
        this.resumeName = resumeName;
        this.jobDescription = jobDescription;
        this.result = result;
        this.analyzedAt = analyzedAt;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public String getResumeName() { return resumeName; }
    public void setResumeName(String resumeName) { this.resumeName = resumeName; }
    public String getJobDescription() { return jobDescription; }
    public void setJobDescription(String jobDescription) { this.jobDescription = jobDescription; }
    public String getResult() { return result; }
    public void setResult(String result) { this.result = result; }
    public LocalDateTime getAnalyzedAt() { return analyzedAt; }
    public void setAnalyzedAt(LocalDateTime analyzedAt) { this.analyzedAt = analyzedAt; }
}

