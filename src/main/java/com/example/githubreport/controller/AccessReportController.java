package com.example.githubreport.controller;

import com.example.githubreport.service.GithubService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

@RestController
@RequestMapping("/api")
public class AccessReportController {

    private final GithubService service;

    public AccessReportController(GithubService service) {
        this.service = service;
    }

    @GetMapping("/access-report")
    public ResponseEntity<Map<String, Object>> getAccessReport(
            @RequestParam(required = false) String org) {

        Map<String, Object> response = new HashMap<>();

        try {

            Map<String, List<String>> report = service.generateReport();

            response.put("organization", org);
            response.put("generatedAt", LocalDateTime.now());
            response.put("accessReport", report);

            return ResponseEntity.ok(response);

        } catch (Exception e) {

            response.put("error", "Failed to generate report");
            response.put("message", e.getMessage());

            return ResponseEntity.internalServerError().body(response);
        }
    }
}