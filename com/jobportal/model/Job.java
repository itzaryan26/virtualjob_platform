package com.jobportal.model;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Comparator;

public class Job {
    private String id;
    private String title;
    private String companyId;
    private List<String> requiredSkills;
    private PriorityQueue<Application> applications;

    public Job(String id, String title, String companyId) {
        this.id = id;
        this.title = title;
        this.companyId = companyId;
        this.requiredSkills = new ArrayList<>();
        this.applications = new PriorityQueue<>(
            Comparator.comparingInt(Application::getMatchScore).reversed()
        );
    }

    // Getters and Setters
    public String getId() { return id; }
    public String getTitle() { return title; }
    public String getCompanyId() { return companyId; }
    public List<String> getRequiredSkills() { return requiredSkills; }
    public PriorityQueue<Application> getApplications() { return applications; }

    public void addRequiredSkill(String skill) {
        requiredSkills.add(skill);
    }

    public void addApplication(Application application) {
        applications.offer(application);
    }

    @Override
    public String toString() {
        return "Job{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", companyId='" + companyId + '\'' +
                ", requiredSkills=" + requiredSkills +
                '}';
    }
}