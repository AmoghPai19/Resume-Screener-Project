package com.resumescreener.resume_screener.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
@Entity
public class ScreeningResult {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "MEDIUMTEXT")
    private String resumeText;

    @Column(columnDefinition = "MEDIUMTEXT")
    private String jobDescription;

    private int score;

    @Column(columnDefinition = "TEXT")
    private String matchedKeywords;

    private String resumeName;
    private LocalDateTime analyzedAt;

    public ScreeningResult() {}

    public ScreeningResult(String resumeText, String jobDescription, int score, String matchedKeywords, String resumeName) {
        this.resumeText = resumeText;
        this.jobDescription = jobDescription;
        this.score = score;
        this.matchedKeywords = matchedKeywords;
        this.resumeName = resumeName;
        this.analyzedAt = LocalDateTime.now();
    }

    // Getters and Setters

    public Long getId() { return id; }
    public String getResumeText() { return resumeText; }
    public String getJobDescription() { return jobDescription; }
    public int getScore() { return score; }
    public String getMatchedKeywords() { return matchedKeywords; }
    public String getResumeName() { return resumeName; }
    public LocalDateTime getAnalyzedAt() { return analyzedAt; }

    public void setResumeText(String resumeText) { this.resumeText = resumeText; }
    public void setJobDescription(String jobDescription) { this.jobDescription = jobDescription; }
    public void setScore(int score) { this.score = score; }
    public void setMatchedKeywords(String matchedKeywords) { this.matchedKeywords = matchedKeywords; }
    public void setResumeName(String resumeName) { this.resumeName = resumeName; }
    public void setAnalyzedAt(LocalDateTime analyzedAt) { this.analyzedAt = analyzedAt; }
}
