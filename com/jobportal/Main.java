package com.jobportal;

import com.jobportal.model.*;
import java.util.Scanner;
import java.util.List;

public class Main {
    private static JobPortalSystem system = new JobPortalSystem();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            printMainMenu();
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    candidateMenu();
                    break;
                case 2:
                    companyMenu();
                    break;
                case 3:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid choice!");
            }
        }
    }

    private static void printMainMenu() {
        System.out.println("\n=== Virtual Job Hiring Platform ===");
        System.out.println("1. Candidate Portal");
        System.out.println("2. Company Portal");
        System.out.println("3. Exit");
        System.out.print("Enter your choice: ");
    }

    private static void candidateMenu() {
        while (true) {
            System.out.println("\n=== Candidate Portal ===");
            System.out.println("1. Register as Candidate");
            System.out.println("2. Add Skills");
            System.out.println("3. Apply for Job");
            System.out.println("4. View Available Jobs");
            System.out.println("5. Back to Main Menu");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    registerCandidate();
                    break;
                case 2:
                    addCandidateSkills();
                    break;
                case 3:
                    applyForJob();
                    break;
                case 4:
                    viewAllJobs();
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Invalid choice!");
            }
        }
    }

    private static void companyMenu() {
        while (true) {
            System.out.println("\n=== Company Portal ===");
            System.out.println("1. Register Company");
            System.out.println("2. Post Job");
            System.out.println("3. View Top Applicants");
            System.out.println("4. Back to Main Menu");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    registerCompany();
                    break;
                case 2:
                    postJob();
                    break;
                case 3:
                    viewTopApplicants();
                    break;
                case 4:
                    return;
                default:
                    System.out.println("Invalid choice!");
            }
        }
    }

    private static void registerCandidate() {
        System.out.print("Enter name: ");
        String name = scanner.nextLine();
        System.out.print("Enter years of experience: ");
        int experience = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        String id = system.registerCandidate(name, experience);
        System.out.println("Registered successfully! Your ID is: " + id);
    }

    private static void addCandidateSkills() {
        System.out.print("Enter candidate ID: ");
        String id = scanner.nextLine();
        
        Candidate candidate = system.getCandidate(id);
        if (candidate == null) {
            System.out.println("Candidate not found!");
            return;
        }

        System.out.print("Enter number of skills to add: ");
        int numSkills = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        for (int i = 0; i < numSkills; i++) {
            System.out.print("Enter skill " + (i+1) + ": ");
            String skill = scanner.nextLine();
            system.addCandidateSkill(id, skill);
        }
        System.out.println("Skills added successfully!");
    }

    private static void registerCompany() {
        System.out.print("Enter company name: ");
        String name = scanner.nextLine();
        String id = system.registerCompany(name);
        System.out.println("Registered successfully! Company ID is: " + id);
    }

    private static void postJob() {
        System.out.print("Enter company ID: ");
        String companyId = scanner.nextLine();
        
        Company company = system.getCompany(companyId);
        if (company == null) {
            System.out.println("Company not found!");
            return;
        }

        System.out.print("Enter job title: ");
        String title = scanner.nextLine();
        String jobId = system.postJob(companyId, title);

        System.out.print("Enter number of required skills: ");
        int numSkills = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        for (int i = 0; i < numSkills; i++) {
            System.out.print("Enter required skill " + (i+1) + ": ");
            String skill = scanner.nextLine();
            system.addJobSkill(jobId, skill);
        }
        System.out.println("Job posted successfully! Job ID is: " + jobId);
    }

    private static void viewAllJobs() {
        List<Job> jobs = system.getAllJobs();
        if (jobs.isEmpty()) {
            System.out.println("No jobs available!");
            return;
        }

        System.out.println("\nAvailable Jobs:");
        for (Job job : jobs) {
            System.out.println("ID: " + job.getId());
            System.out.println("Title: " + job.getTitle());
            System.out.println("Required Skills: " + job.getRequiredSkills());
            System.out.println("-------------------");
        }
    }

    private static void applyForJob() {
        System.out.print("Enter your candidate ID: ");
        String candidateId = scanner.nextLine();
        System.out.print("Enter job ID: ");
        String jobId = scanner.nextLine();

        system.applyForJob(candidateId, jobId);
        System.out.println("Application submitted successfully!");
    }

    private static void viewTopApplicants() {
        System.out.print("Enter job ID: ");
        String jobId = scanner.nextLine();
        System.out.print("Enter number of top applicants to view: ");
        int limit = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        List<Application> topApplications = system.getTopApplications(jobId, limit);
        if (topApplications.isEmpty()) {
            System.out.println("No applications found for this job!");
            return;
        }

        System.out.println("\nTop Applicants:");
        for (Application app : topApplications) {
            Candidate candidate = system.getCandidate(app.getCandidateId());
            System.out.println("Candidate ID: " + app.getCandidateId());
            System.out.println("Name: " + candidate.getName());
            System.out.println("Match Score: " + app.getMatchScore());
            System.out.println("-------------------");
        }
    }
}