const apiUrl = 'http://localhost:8080/api';

document.addEventListener('DOMContentLoaded', () => {
    fetchIssues();
    fetchIssueTypes();
    document.getElementById('create-issue-form').addEventListener('submit', createIssue);
});

function fetchIssues() {
    fetch(`${apiUrl}/issues`)
        .then(response => response.json())
        .then(issues => {
            const issueListUl = document.getElementById('issue-list-ul');
            issueListUl.innerHTML = '';
            issues.forEach(issue => {
                const issueLi = document.createElement('li');
                issueLi.textContent = issue.summary;
                issueListUl.appendChild(issueLi);
            });
        })
        .catch(error => console.error(error));
}

function fetchIssueTypes() {
    fetch(`${apiUrl}/issue-types`)
        .then(response => response.json())
        .then(issueTypes => {
            const issueTypeSelect = document.getElementById('issueType');
            issueTypeSelect.innerHTML = '';
            issueTypes.forEach(issueType => {
                const option = document.createElement('option');
                option.value = issueType.name;
                option.textContent = issueType.name;
issueTypeSelect.appendChild(option);
            });
        })
       .catch(error => console.error(error));
}

function createIssue(event) {
    event.preventDefault();
    const form = event.target;
    const issue = {
        projectKey: form.projectKey.value,
        summary: form.summary.value,
        description: form.description.value,
        issueType: form.issueType.value,
    };
    fetch(`${apiUrl}/create-issue`, {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(issue),
    })
       .then(response => response.json())
       .then(issue => {
            fetchIssues();
            form.reset();
        })
       .catch(error => console.error(error));
}