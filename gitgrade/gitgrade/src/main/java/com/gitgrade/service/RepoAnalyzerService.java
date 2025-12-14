package com.gitgrade.service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.jgit.api.Git;
import org.springframework.stereotype.Service;

import com.gitgrade.model.RepoResponse;

@Service
public class RepoAnalyzerService {

    private static final String TEMP_DIR = "tempRepo";

    public RepoResponse analyzeRepo(String repoUrl) {
        RepoResponse response = new RepoResponse();
        File repoDir = new File(TEMP_DIR);

        try {
            // Delete old temp folder if exists
            if (repoDir.exists()) deleteDirectory(repoDir);

            // Clone the repository
            Git.cloneRepository()
                .setURI(repoUrl)
                .setDirectory(repoDir)
                .call();

            // Analyze repo content
            int score = calculateScore(repoDir);
            List<String> roadmap = generateRoadmap(repoDir);

            // Set response
            response.setScore(score);
            response.setLevel(score > 80 ? "Advanced" : score > 50 ? "Intermediate" : "Beginner");
            response.setSummary(score > 50 ? "Good project" : "Needs improvement");
            response.setRoadmap(roadmap);

        } catch (Exception e) {
            e.printStackTrace();
            response.setScore(0);
            response.setLevel("Unknown");
            response.setSummary("Failed to analyze repository");
            response.setRoadmap(List.of("Check repository URL or access rights"));
        } finally {
            // Cleanup temporary repo folder
            if (repoDir.exists()) deleteDirectory(repoDir);
        }

        return response;
    }

    private int calculateScore(File repoDir) {
        int score = 0;

        // Check README
        if (new File(repoDir, "README.md").exists()) score += 20;

        // Check for tests
        File testDir = new File(repoDir, "src/test/java");
        if (testDir.exists() && testDir.listFiles().length > 0) score += 30;

        // Base score
        score += 25;

        return Math.min(score, 100);
    }

    private List<String> generateRoadmap(File repoDir) {
        List<String> roadmap = new ArrayList<>();

        if (!new File(repoDir, "README.md").exists()) roadmap.add("Add a README file");
        File testDir = new File(repoDir, "src/test/java");
        if (!testDir.exists() || testDir.listFiles().length == 0) roadmap.add("Add unit tests");

        roadmap.add("Follow Git best practices");

        return roadmap;
    }

    private void deleteDirectory(File dir) {
        File[] files = dir.listFiles();
        if (files != null) {
            for (File f : files) {
                if (f.isDirectory()) deleteDirectory(f);
                else f.delete();
            }
        }
        dir.delete();
    }
}
