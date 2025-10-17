package com.resumescreener.resume_screener.service;

import com.resumescreener.resume_screener.model.ScreeningResult;
import com.resumescreener.resume_screener.repository.ScreeningResultRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ResumeService {

    private final ScreeningResultRepository repository;

    private static final Set<String> STOP_WORDS = new HashSet<>(Arrays.asList(
            "we", "are", "a", "to", "our", "as", "an", "on", "of", "in", "and", "the", "is", "for", "with", "by"
    ));

    // Optional: define some high-priority keywords for extra weight
    private static final Map<String, Integer> KEYWORD_WEIGHTS = new HashMap<>();
    static {
        KEYWORD_WEIGHTS.put("java", 5);
        KEYWORD_WEIGHTS.put("spring", 4);
        KEYWORD_WEIGHTS.put("sql", 3);
        KEYWORD_WEIGHTS.put("docker", 3);
        KEYWORD_WEIGHTS.put("aws", 4);
        // add more as needed
    }

    public ResumeService(ScreeningResultRepository repository) {
        this.repository = repository;
    }

    public ScreeningResult analyze(MultipartFile resumeFile, String jobDescription) {
        try {
            // Convert resume file to string
            String resumeText = new BufferedReader(new InputStreamReader(resumeFile.getInputStream()))
                    .lines().collect(Collectors.joining("\n"));

            // Tokenize job description, ignore stop words
            String[] keywords = jobDescription.toLowerCase().split("\\s+");
            Map<String, Integer> matchedKeywords = new HashMap<>();
            int totalScore = 0;

            for (String keyword : keywords) {
                if (STOP_WORDS.contains(keyword)) continue;

                int count = countOccurrences(resumeText.toLowerCase(), keyword);
                if (count > 0) {
                    int weight = KEYWORD_WEIGHTS.getOrDefault(keyword, 1); // default weight 1
                    int keywordScore = count * weight;
                    totalScore += keywordScore;
                    matchedKeywords.put(keyword, keywordScore);
                }
            }

            String matchedStr = matchedKeywords.entrySet().stream()
                    .map(e -> e.getKey() + "(" + e.getValue() + ")")
                    .collect(Collectors.joining(", "));

            ScreeningResult result = new ScreeningResult(resumeText, jobDescription, totalScore, matchedStr, resumeFile.getOriginalFilename());
            repository.save(result);
            return result;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<ScreeningResult> getAllResults() {
        return repository.findAll();
    }

    // Utility method to count occurrences of a word in text
    private int countOccurrences(String text, String word) {
        int count = 0;
        int index = 0;
        while ((index = text.indexOf(word, index)) != -1) {
            count++;
            index += word.length();
        }
        return count;
    }
}
