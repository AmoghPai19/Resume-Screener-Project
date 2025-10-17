package com.resumescreener.resume_screener.controller;

import com.resumescreener.resume_screener.model.ScreeningResult;
import com.resumescreener.resume_screener.service.ResumeService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ResumeController {

    private final ResumeService service;

    public ResumeController(ResumeService service) {
        this.service = service;
    }

    // Updated analyze method for file upload
    @PostMapping("/analyze")
    public ScreeningResult analyze(
            @RequestParam("jobDescription") String jobDescription,
            @RequestParam("resumeFile") MultipartFile resumeFile) {

        ScreeningResult result = service.analyze(resumeFile, jobDescription);
        if (result == null) {
            throw new RuntimeException("Error analyzing resume");
        }
        return result;
    }

    @GetMapping("/results")
    public List<ScreeningResult> getAllResults() {
        return service.getAllResults();
    }

    @GetMapping("/results/{id}")
    public ScreeningResult getResultById(@PathVariable Long id) {
        return service.getAllResults().stream()
                .filter(r -> r.getId().equals(id))
                .findFirst()
                .orElse(null);
    }
}
