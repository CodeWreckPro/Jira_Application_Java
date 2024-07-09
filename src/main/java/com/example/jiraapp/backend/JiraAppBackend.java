package com.example.jiraapp.backend;

import com.atlassian.jira.rest.client.api.JiraRestClient;
import com.atlassian.jira.rest.client.api.domain.Issue;
import com.atlassian.jira.rest.client.api.domain.Project;
import com.atlassian.jira.rest.client.api.domain.IssueType;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JiraAppBackend {
    private JiraRestClient jiraRestClient;
    private Connection connection;

    public JiraAppBackend() {
        jiraRestClient = new JiraRestClient("https://your-jira-instance.atlassian.net");
        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/jira_app", "username", "password");
    }

    public List<Issue> getIssues() {
        List<Issue> issues = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM issues")) {
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Issue issue = new Issue();
                    issue.setId(resultSet.getInt("id"));
                    issue.setProjectKey(resultSet.getString("project_key"));
                    issue.setSummary(resultSet.getString("summary"));
                    issue.setDescription(resultSet.getString("description"));
                    issue.setIssueType(resultSet.getString("issue_type"));
                    issues.add(issue);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return issues;
    }

    public Issue createIssue(Issue issue) {
        try (PreparedStatement statement = connection.prepareStatement("INSERT INTO issues (project_key, summary, description, issue_type) VALUES (?, ?, ?, ?)")) {
            statement.setString(1, issue.getProjectKey());
            statement.setString(2, issue.getSummary());
            statement.setString(3, issue.getDescription());
            statement.setString(4, issue.getIssueType());
            statement.executeUpdate();
            return issue;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Project getProject(String projectKey) {
        try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM projects WHERE key = ?")) {
            statement.setString(1, projectKey);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    Project project = new Project();
                    project.setId(resultSet.getInt("id"));
                    project.setKey(resultSet.getString("key"));
                    project.setName(resultSet.getString("name"));
                    return project;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public IssueType getIssueType(String issueTypeName) {
        try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM issue_types WHERE name =?")) {
            statement.setString(1, issueTypeName);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    IssueType issueType = new IssueType();
                    issueType.setId(resultSet.getInt("id"));
                    issueType.setName(resultSet.getString("name"));
                    return issueType;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}