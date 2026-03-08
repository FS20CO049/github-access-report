package com.example.githubreport.client;

import com.example.githubreport.model.Repo;
import com.example.githubreport.model.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class GithubClient {

    @Value("${github.token}")
    private String token;

    private final RestTemplate restTemplate = new RestTemplate();

    private HttpEntity<String> getEntity() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + token);
        return new HttpEntity<>(headers);
    }

    public ResponseEntity<Repo[]> getRepos(String org,int page) {
        String url = "https://api.github.com/orgs/" + org +
                "/repos?page=" + page + "&per_page=100";
        return restTemplate.exchange(url, HttpMethod.GET, getEntity(), Repo[].class);
    }

    public ResponseEntity<User[]> getCollaborators(String org, String repo) {
        String url = "https://api.github.com/repos/" + org + "/" + repo + "/contributors";
        return restTemplate.exchange(url, HttpMethod.GET, getEntity(), User[].class);
    }
}