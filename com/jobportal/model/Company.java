package com.jobportal.model;

import java.util.ArrayList;
import java.util.List;

public class Company {
    private String id;
    private String name;
    private List<String> postedJobs;

    public Company(String id, String name) {
        this.id = id;
        this.name = name;
        this.postedJobs = new ArrayList<>();
    }

    // Getters and Setters
    public String getId() { return id; }
    public String getName() { return name; }
    public List<String> getPostedJobs() { return postedJobs; }

    public void addJob(String jobId) {
        postedJobs.add(jobId);
    }

    @Override
    public String toString() {
        return "Company{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", postedJobs=" + postedJobs +
                '}';
    }
}