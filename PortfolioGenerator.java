import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Education {
    private String degree;
    private String institution;
    private int year;

    public Education(String degree, String institution, int year) {
        this.degree = degree;
        this.institution = institution;
        this.year = year;
    }

    public String getDegree() {
        return degree;
    }

    public String getInstitution() {
        return institution;
    }

    public int getYear() {
        return year;
    }
}

class Project {
    private String projectName;
    private String description;
    private String[] technologies;

    public Project(String projectName, String description, String[] technologies) {
        this.projectName = projectName;
        this.description = description;
        this.technologies = technologies;
    }

    public String getProjectName() {
        return projectName;
    }

    public String getDescription() {
        return description;
    }

    public String[] getTechnologies() {
        return technologies;
    }
}

class Experience {
    private String jobTitle;
    private String company;
    private String startDate;
    private String endDate;
    private String[] responsibilities;

    public Experience(String jobTitle, String company, String startDate, String endDate, String[] responsibilities) {
        this.jobTitle = jobTitle;
        this.company = company;
        this.startDate = startDate;
        this.endDate = endDate;
        this.responsibilities = responsibilities;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public String getCompany() {
        return company;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public String[] getResponsibilities() {
        return responsibilities;
    }
}

public class PortfolioGenerator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.print("Enter your name: ");
            String name = scanner.nextLine();

            System.out.println("\nEnter your education details:");
            System.out.print("Degree: ");
            String degree = scanner.nextLine();
            System.out.print("Institution: ");
            String institution = scanner.nextLine();
            System.out.print("Year of graduation: ");
            int year = scanner.nextInt();
            scanner.nextLine();

            Education education = new Education(degree, institution, year);

            System.out.println("\nEnter your projects (type 'done' to finish):");
            Project[] projects = gatherProjects(scanner);

            System.out.println("\nEnter your work experience (type 'done' to finish):");
            Experience[] experiences = gatherExperience(scanner);

            String htmlContent = generateHTML(name, education, projects, experiences);
            String cssContent = generateCSS();

            String desktopPath = System.getProperty("user.home") + "/Desktop";
            String portfolioFolderPath = desktopPath + "/portfolio";

            File portfolioFolder = new File(portfolioFolderPath);
            if (!portfolioFolder.exists() && !portfolioFolder.mkdir()) {
                System.out.println("Failed to create portfolio folder.");
                return;
            }

            writeToFile(portfolioFolderPath + "/portfolio.html", htmlContent);
            writeToFile(portfolioFolderPath + "/styles.css", cssContent);

            System.out.println("Portfolio generated successfully at: " + portfolioFolderPath);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            scanner.close();
        }
    }

    private static Project[] gatherProjects(Scanner scanner) {
        List<Project> projects = new ArrayList<>();
        while (true) {
            System.out.print("Project name: ");
            String projectName = scanner.nextLine();
            if (projectName.equalsIgnoreCase("done")) break;

            System.out.print("Description: ");
            String description = scanner.nextLine();

            System.out.print("Technologies (comma-separated): ");
            String[] technologies = scanner.nextLine().split(",");

            projects.add(new Project(projectName, description, technologies));
        }
        return projects.toArray(new Project[0]);
    }

    private static Experience[] gatherExperience(Scanner scanner) {
        List<Experience> experiences = new ArrayList<>();
        while (true) {
            System.out.print("Job title: ");
            String jobTitle = scanner.nextLine();
            if (jobTitle.equalsIgnoreCase("done")) break;

            System.out.print("Company: ");
            String company = scanner.nextLine();

            System.out.print("Start date: ");
            String startDate = scanner.nextLine();

            System.out.print("End date: ");
            String endDate = scanner.nextLine();

            System.out.print("Responsibilities (comma-separated): ");
            String[] responsibilities = scanner.nextLine().split(",");

            experiences.add(new Experience(jobTitle, company, startDate, endDate, responsibilities));
        }
        return experiences.toArray(new Experience[0]);
    }

    private static String generateHTML(String name, Education education, Project[] projects, Experience[] experiences) {
        StringBuilder html = new StringBuilder();

        html.append("<!DOCTYPE html>");
        html.append("<html lang=\"en\">");
        html.append("<head>");
        html.append("<meta charset=\"UTF-8\">");
        html.append("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">");
        html.append("<link href=\"styles.css\" rel=\"stylesheet\">");
        html.append("<link href=\"https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css\" rel=\"stylesheet\">");
        html.append("<title>").append(name).append("'s Portfolio</title>");
        html.append("</head>");
        html.append("<body>");
        html.append("<div class=\"container\">");
        html.append("<h1>").append(name).append("'s Portfolio</h1>");

        html.append("<section><h2>Education</h2><p>")
            .append(education.getDegree()).append(" from ").append(education.getInstitution())
            .append(", ").append(education.getYear()).append("</p></section>");

        html.append("<section><h2>Projects</h2><ul>");
        for (Project project : projects) {
            html.append("<li><strong>").append(project.getProjectName()).append("</strong>: ")
                .append(project.getDescription()).append("</li>");
        }
        html.append("</ul></section>");

        html.append("<section><h2>Experience</h2><ul>");
        for (Experience experience : experiences) {
            html.append("<li><strong>").append(experience.getJobTitle()).append("</strong> at ")
                .append(experience.getCompany()).append(" (").append(experience.getStartDate())
                .append(" - ").append(experience.getEndDate()).append(")</li>");
        }
        html.append("</ul></section>");

        html.append("</div><footer><p>&copy; 2024 ").append(name).append(". All Rights Reserved.</p></footer>");
        html.append("</body></html>");

        return html.toString();
    }

    private static String generateCSS() {
        return """
            body {
                font-family: 'Roboto', sans-serif;
                background: linear-gradient(120deg, #f6d365, #fda085);
                margin: 0;
                padding: 0;
                color: #333;
            }
            .container {
                max-width: 800px;
                background: white;
                margin: 50px auto;
                border-radius: 10px;
                padding: 20px;
                box-shadow: 0 5px 15px rgba(0, 0, 0, 0.2);
            }
            h1 {
                text-align: center;
                color: #333;
            }
            h2 {
                color: #555;
                border-bottom: 2px solid #fda085;
                padding-bottom: 5px;
            }
            ul {
                list-style: none;
                padding: 0;
            }
            li {
                background: #fff;
                margin-bottom: 10px;
                padding: 10px;
                border-left: 4px solid #fda085;
                border-radius: 5px;
            }
            footer {
                text-align: center;
                font-size: 0.9em;
                color: #777;
                margin-top: 20px;
            }
        """;
    }

    private static void writeToFile(String filename, String content) {
        try (FileWriter writer = new FileWriter(filename)) {
            writer.write(content);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
