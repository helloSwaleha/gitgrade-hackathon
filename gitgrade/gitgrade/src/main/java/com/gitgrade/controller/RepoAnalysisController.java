package com.gitgrade.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.gitgrade.model.RepoRequest;
import com.gitgrade.model.RepoResponse;
import com.gitgrade.service.RepoAnalyzerService;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class RepoAnalysisController {

    @Autowired
    private RepoAnalyzerService service;

    @PostMapping("/analyze")
    public RepoResponse analyze(@RequestBody RepoRequest request) {
        return service.analyzeRepo(request.getRepoUrl());
    }
}
