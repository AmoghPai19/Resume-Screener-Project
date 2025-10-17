package com.resumescreener.resume_screener.logic;

import java.util.*;

public class ScreenerEngine {

    // Extract keywords by splitting and cleaning text
    public static Set<String> extractKeywords(String text) {
        Set<String> keywords = new HashSet<>();
        String[] words = text.toLowerCase().split("[^a-z0-9+]+"); // split on non-word chars

        for (String word : words) {
            if (word.length() > 2 && !isStopWord(word)) {
                keywords.add(word);
            }
        }
        return keywords;
    }

    // Simple list of stopwords to ignore
    private static boolean isStopWord(String word) {
        String[] stopWords = {"and", "the", "with", "for", "you", "are", "from", "this", "that", "have"};
        return Arrays.asList(stopWords).contains(word);
    }

    // Calculate score between resume & job description
    public static double calculateMatchScore(String resumeText, String jobDesc) {
        Set<String> resumeKeywords = extractKeywords(resumeText);
        Set<String> jobKeywords = extractKeywords(jobDesc);

        int matched = 0;
        for (String kw : jobKeywords) {
            if (resumeKeywords.contains(kw)) {
                matched++;
            }
        }

        if (jobKeywords.isEmpty()) return 0.0;
        return (matched * 100.0) / jobKeywords.size();
    }

    // Show matched keywords
    public static List<String> getMatchedKeywords(String resumeText, String jobDesc) {
        Set<String> resumeKeywords = extractKeywords(resumeText);
        Set<String> jobKeywords = extractKeywords(jobDesc);
        List<String> matched = new ArrayList<>();

        for (String kw : jobKeywords) {
            if (resumeKeywords.contains(kw)) {
                matched.add(kw);
            }
        }
        return matched;
    }
}
