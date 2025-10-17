package com.resumescreener.resume_screener.model;


import org.springframework.web.multipart.MultipartFile;

public class ScreeningRequest {

    private String jobDescription;
    private MultipartFile resumeFile;

    // Getters and Setters
    public String getJobDescription() { return jobDescription; }
    public void setJobDescription(String jobDescription) { this.jobDescription = jobDescription; }

    public MultipartFile getResumeFile() { return resumeFile; }
    public void setResumeFile(MultipartFile resumeFile) { this.resumeFile = resumeFile; }
}

