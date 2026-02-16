package com.restful.aiagent.utils;

import java.util.regex.Pattern;

public class AiReportAnalyzer {

    public static int calculateScore(String report) {
        if (report == null || report.isEmpty()) return 100; // default full score

        int violations = countOccurrences(report, "Violations:");
        int missingProcedures = countOccurrences(report, "Missing procedures:");
        int regulatoryRisks = countOccurrences(report, "Regulatory risks:");
        int deviations = countOccurrences(report, "Deviations:");

        int totalIssues = violations + missingProcedures + regulatoryRisks + deviations;

        // Simple scoring: 100 - 10 * totalIssues, minimum 0
        int score = 100 - 10 * totalIssues;
        return Math.max(score, 0);
    }

    public static String calculateRiskLevel(int score) {
        if (score >= 80) return "LOW";
        else if (score >= 50) return "MEDIUM";
        else return "HIGH";
    }

    private static int countOccurrences(String text, String keyword) {
        if (text == null || keyword == null) return 0;
        return text.split(Pattern.quote(keyword), -1).length - 1;
    }
}
