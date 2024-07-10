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

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public String getInstitution() {
        return institution;
    }

    public void setInstitution(String institution) {
        this.institution = institution;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
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

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String[] getTechnologies() {
        return technologies;
    }

    public void setTechnologies(String[] technologies) {
        this.technologies = technologies;
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

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String[] getResponsibilities() {
        return responsibilities;
    }

    public void setResponsibilities(String[] responsibilities) {
        this.responsibilities = responsibilities;
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

            System.out.println("\nEnter your projects details (press Enter after each project, type 'done' when finished):");
            Project[] projects = gatherProjects(scanner);

        
            System.out.println("\nEnter your work experience details (press Enter after each experience, type 'done' when finished):");
            Experience[] experience = gatherExperience(scanner);

          
            String htmlContent = generateHTML(name, education, projects, experience);
            String cssContent = generateCSS();

        
            String desktopPath = System.getProperty("user.home") + "/Desktop";
            String portfolioFolderPath = desktopPath + "/portfolio";

            File portfolioFolder = new File(portfolioFolderPath);
            if (!portfolioFolder.exists()) {
                if (portfolioFolder.mkdir()) {
                    System.out.println("Portfolio folder created at: " + portfolioFolderPath);
                } else {
                    System.out.println("Failed to create portfolio folder.");
                    return;
                }
            }

   
            writeToFile(portfolioFolderPath + "/portfolio.html", htmlContent);
            writeToFile(portfolioFolderPath + "/styles.css", cssContent);

            System.out.println("Portfolio generated successfully.");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            scanner.close(); 
        }
    }


    private static Project[] gatherProjects(Scanner scanner) {
        List<Project> projects = new ArrayList<>();
        while (true) {
            System.out.print("Project name (type 'done' to finish): ");
            String projectName = scanner.nextLine();
            if (projectName.equalsIgnoreCase("done")) {
                break;
            }
            System.out.print("Description: ");
            String description = scanner.nextLine();
            System.out.print("Technologies (comma-separated): ");
            String technologiesInput = scanner.nextLine();
            String[] technologies = technologiesInput.split(",");
            Project project = new Project(projectName, description, technologies);
            projects.add(project);
        }
        return projects.toArray(new Project[0]);
    }

  
    private static Experience[] gatherExperience(Scanner scanner) {
        List<Experience> experienceList = new ArrayList<>();
        while (true) {
            System.out.print("Job title (type 'done' to finish): ");
            String jobTitle = scanner.nextLine();
            if (jobTitle.equalsIgnoreCase("done")) {
                break;
            }
            System.out.print("Company: ");
            String company = scanner.nextLine();
            System.out.print("Start date: ");
            String startDate = scanner.nextLine();
            System.out.print("End date: ");
            String endDate = scanner.nextLine();
            System.out.print("Responsibilities (comma-separated): ");
            String responsibilitiesInput = scanner.nextLine();
            String[] responsibilities = responsibilitiesInput.split(",");
            Experience experience = new Experience(jobTitle, company, startDate, endDate, responsibilities);
            experienceList.add(experience);
        }
        return experienceList.toArray(new Experience[0]);
    }


    private static String generateHTML(String name, Education education, Project[] projects, Experience[] experience) {
        StringBuilder html = new StringBuilder();

  
        html.append("<html>");
        html.append("<head>");
        html.append("<link rel=\"stylesheet\" type=\"text/css\" href=\"styles.css\">");
        html.append("</head>");
        html.append("<body>");
        html.append("<div class=\"container\">");
        html.append("<h1>").append(name).append("'s Portfolio</h1>");

    
        html.append("<h2>Education</h2>");
        html.append("<ul>");
        html.append("<li>").append(education.getDegree()).append(" in ").append(education.getInstitution()).append(", ").append(education.getYear()).append("</li>");
        html.append("</ul>");

 
        html.append("<h2>Projects</h2>");
        html.append("<ul>");
        for (Project project : projects) {
            html.append("<li><strong>").append(project.getProjectName()).append("</strong>: ").append(project.getDescription()).append("</li>");
        }
        html.append("</ul>");


        html.append("<h2>Experience</h2>");
        html.append("<ul>");
        for (Experience exp : experience) {
            html.append("<li><strong>").append(exp.getJobTitle()).append("</strong> at ").append(exp.getCompany()).append(", ").append(exp.getStartDate()).append(" - ").append(exp.getEndDate()).append("</li>");
        }
        html.append("</ul>");

        html.append("</div>");
        html.append("</body>");
        html.append("</html>");

        return html.toString();
    }


    private static String generateCSS() {
        StringBuilder css = new StringBuilder();

        css.append("body { font-family: Arial, sans-serif; background-color: #f0f0f0; }");
        css.append(".container { width: 80%; margin: 0 auto; background-color: #fff; padding: 20px; box-shadow: 0 0 10px rgba(0,0,0,0.1); }");
        css.append("h1 { color: #333; text-align: center; }");
        css.append("h2 { color: #555; }");
        css.append("ul { list-style-type: none; padding-left: 0; }");
        css.append("li { margin-bottom: 10px; }");

        return css.toString();
    }


    private static void writeToFile(String filename, String content) {
        try (FileWriter writer = new FileWriter(filename)) {
            writer.write(content);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
