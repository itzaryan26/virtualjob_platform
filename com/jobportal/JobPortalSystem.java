package com.jobportal;

import com.jobportal.model.*;
import java.util.*;

public class JobPortalSystem {
    private Map<String, Candidate> candidates;
    private Map<String, Company> companies;
    private Map<String, Job> jobs;
    private int nextCandidateId = 1;
    private int nextCompanyId = 1;
    private int nextJobId = 1;

    public JobPortalSystem() {
        candidates = new HashMap<>();
        companies = new HashMap<>();
        jobs = new HashMap<>();
    }

    // Candidate operations
    public String registerCandidate(String name, int experience) {
        String id = "C" + nextCandidateId++;
        Candidate candidate = new Candidate(id, name, experience);
        candidates.put(id, candidate);
        return id;
    }

    public void addCandidateSkill(String candidateId, String skill) {
        Candidate candidate = candidates.get(candidateId);
        if (candidate != null) {
            candidate.addSkill(skill);
        }
    }

    // Company operations
    public String registerCompany(String name) {
        String id = "COM" + nextCompanyId++;
        Company company = new Company(id, name);
        companies.put(id, company);
        return id;
    }

    public String postJob(String companyId, String title) {
        String id = "J" + nextJobId++;
        Job job = new Job(id, title, companyId);
        jobs.put(id, job);
        Company company = companies.get(companyId);
        if (company != null) {
            company.addJob(id);
        }
        return id;
    }

    public void addJobSkill(String jobId, String skill) {
        Job job = jobs.get(jobId);
        if (job != null) {
            job.addRequiredSkill(skill);
        }
    }

    // Application operations
    public void applyForJob(String candidateId, String jobId) {
        Candidate candidate = candidates.get(candidateId);
        Job job = jobs.get(jobId);
        
        if (candidate != null && job != null) {
            int score = calculateMatchScore(candidate, job);
            Application application = new Application(candidateId, jobId, score);
            job.addApplication(application);
            candidate.applyForJob(jobId);
        }
    }

    private int calculateMatchScore(Candidate candidate, Job job) {
        int matchingSkills = 0;
        for (String skill : candidate.getSkills()) {
            if (job.getRequiredSkills().contains(skill)) {
                matchingSkills++;
            }
        }
        return (matchingSkills * 10) + (candidate.getExperience() * 2);
    }

    public List<Application> getTopApplications(String jobId, int limit) {
        Job job = jobs.get(jobId);
        if (job == null) return new ArrayList<>();

        List<Application> topApplications = new ArrayList<>();
        PriorityQueue<Application> applications = new PriorityQueue<>(job.getApplications());
        
        for (int i = 0; i < limit && !applications.isEmpty(); i++) {
            topApplications.add(applications.poll());
        }
        
        return topApplications;
    }

    // Getter methods for entities
    public Candidate getCandidate(String id) { return candidates.get(id); }
    public Company getCompany(String id) { return companies.get(id); }
    public Job getJob(String id) { return jobs.get(id); }
    public List<Job> getAllJobs() { return new ArrayList<>(jobs.values()); }
}