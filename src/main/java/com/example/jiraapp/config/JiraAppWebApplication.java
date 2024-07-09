package com.example.jiraapp.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

@SpringBootApplication
@RestController
public class JiraAppWebApplication {
    private JiraAppBackend backend;
    private TemplateEngine templateEngine;

    public JiraAppWebApplication(JiraAppBackend backend, TemplateEngine templateEngine) {
        this.backend = backend;
        this.templateEngine = templateEngine;
    }

    @GetMapping("/")
    public String index(WebContext context) {
        context.setVariable("issues", backend.getIssues());
        return "index";
    }

    @PostMapping("/create-issue")
    public String createIssue(@RequestBody Issue issue, WebContext context) {
        backend.createIssue(issue);
        context.setVariable("issues", backend.getIssues());
        return "index";
    }

    public static void main(String[] args) {
        SpringApplication.run(JiraAppWebApplication.class, args);
    }
}