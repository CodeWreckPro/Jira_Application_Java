# Jira_Application_Java
JiraApp is a comprehensive project management tool designed to streamline project development processes and enhance team collaboration. With its intuitive web interface and robust backend, JiraApp makes task management simple and efficient.

## Features

- **Task Creation and Management**: Create tasks within projects, assign them to team members, and track their progress from inception to completion.
  
- **Issue Tracking**: Keep a detailed record of all tasks, including summaries, descriptions, issue types, and status updates.

- **User-Friendly Interface**: Responsive web application interface ensures ease of use across devices, enabling seamless project management on the go.

- **Scalable Database Design**: Utilizes MySQL for storing project, issue, user, and assignment data, ensuring scalability and performance.

- **Integration with Jira**: Integrates with Jira REST API to fetch and synchronize project and issue data, providing real-time updates.

## Technologies Used

- **Java**: Backend logic and integration with external APIs.
  
- **Spring Boot**: Framework for building robust and scalable Java applications.
  
- **Thymeleaf**: Server-side Java template engine for rendering HTML templates.
  
- **MySQL**: Database management system for storing project and task-related data.

- **JavaScript**: Client-side scripting for dynamic web content and interaction.
  
- **HTML/CSS**: Frontend structure and styling for a responsive and user-friendly interface.

## Getting Started

To run JiraApp locally:
1. Clone this repository.
2. Configure `application.properties` with your MySQL database credentials.
3. Build the project using Maven:
   ```
   mvn clean install
   ```
4. Run the application:
   ```
   java -jar target/JiraApp-1.0-SNAPSHOT.jar
   ```
5. Access the application at `http://localhost:8080`.

## Contributing

We welcome contributions to JiraApp! To contribute:
- Fork the repository.
- Create your feature branch (`git checkout -b feature/AmazingFeature`).
- Commit your changes (`git commit -m 'Add some AmazingFeature'`).
- Push to the branch (`git push origin feature/AmazingFeature`).
- Open a pull request.

## License

Distributed under the MIT License[LICENSE].
