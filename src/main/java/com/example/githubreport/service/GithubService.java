package com.example.githubreport.service;

import com.example.githubreport.client.GithubClient;
import com.example.githubreport.model.Repo;
import com.example.githubreport.model.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class GithubService {

    @Value("${github.org}")
    private String org;

    private final GithubClient githubClient;

    public GithubService(GithubClient githubClient) {
        this.githubClient = githubClient;
    }

    public Map<String, List<String>> generateReport() {

        Map<String, List<String>> report = new HashMap<>();

        try {

            int page = 1;

            while (true) {

                ResponseEntity<Repo[]> repoResponse =
                        githubClient.getRepos(org, page);

                Repo[] repos = repoResponse.getBody();

                if (repos == null || repos.length == 0) {
                    break;
                }

                for (Repo repo : repos) {

                    String repoName = repo.getName();

                    ResponseEntity<User[]> userResponse =
                            githubClient.getCollaborators(org, repoName);

                    User[] users = userResponse.getBody();

                    if (users == null) continue;

                    for (User user : users) {

                        String username = user.getLogin();

                        // Skip bots
                        if (username.contains("[bot]")) {
                            continue;
                        }

                        report
                                .computeIfAbsent(username, k -> new ArrayList<>())
                                .add(repoName);
                    }
                }

                page++;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return report;
    }
}