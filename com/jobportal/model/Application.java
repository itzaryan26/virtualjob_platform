package com.jobportal.model;

public class Application {
    private String candidateId;
    private String jobId;
    private int matchScore;

    public Application(String candidateId, String jobId, int matchScore) {
        this.candidateId = candidateId;
        this.jobId = jobId;
        this.matchScore = matchScore;
    }

    // Getters
    public String getCandidateId() { return candidateId; }
    public String getJobId() { return jobId; }
    public int getMatchScore() { return matchScore; }

    @Override
    public String toString() {
        return "Application{" +
                "candidateId='" + candidateId + '\'' +
                ", jobId='" + jobId + '\'' +
                ", matchScore=" + matchScore +
                '}';
    }
}