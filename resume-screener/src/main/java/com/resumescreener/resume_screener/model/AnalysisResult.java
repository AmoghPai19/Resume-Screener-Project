package com.resumescreener.resume_screener.model;

import java.util.List;

public class AnalysisResult {
    private double score;
    private List<String> matchedKeywords;

    public AnalysisResult(double score, List<String> matchedKeywords) {
        this.score = score;
        this.matchedKeywords = matchedKeywords;
    }

    public double getScore() { return score; }
    public List<String> getMatchedKeywords() { return matchedKeywords; }
}
