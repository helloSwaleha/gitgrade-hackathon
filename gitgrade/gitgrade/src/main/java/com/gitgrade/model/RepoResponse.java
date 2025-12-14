package com.gitgrade.model;

import java.util.List;

public class RepoResponse {
    private int score;
    private String level;
    private String summary;
    private List<String> roadmap;

    public int getScore() { return score; }
    public void setScore(int score) { this.score = score; }
    public String getLevel() { return level; }
    public void setLevel(String level) { this.level = level; }
    public String getSummary() { return summary; }
    public void setSummary(String summary) { this.summary = summary; }
    public List<String> getRoadmap() { return roadmap; }
    public void setRoadmap(List<String> roadmap) { this.roadmap = roadmap; }
}
