package com.jobportal.model;

import java.util.ArrayList;
import java.util.List;

public class Candidate {
    private String id;
    private String name;
    private int experience;
    private List<String> skills;
    private List<String> appliedJobs;

    public Candidate(String id, String name, int experience) {
        this.id = id;
        this.name = name;
        this.experience = experience;
        this.skills = new ArrayList<>();
        this.appliedJobs = new ArrayList<>();
    }

    // Getters and Setters
    public String getId() { return id; }
    public String getName() { return name; }
    public int getExperience() { return experience; }
    public List<String> getSkills() { return skills; }
    public List<String> getAppliedJobs() { return appliedJobs; }

    public void setName(String name) { this.name = name; }
    public void setExperience(int experience) { this.experience = experience; }
    
    public void addSkill(String skill) {
        skills.add(skill);
    }

    public void applyForJob(String jobId) {
        appliedJobs.add(jobId);
    }

    @Override
    public String toString() {
        return "Candidate{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", experience=" + experience +
                ", skills=" + skills +
                '}';
    }
}