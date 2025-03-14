import java.util.Objects;

/**
 * Represents a project that can be allocated to a student.
 */
public class Project {
    /** The title of the project */
    private String title;

    /** The type of the project (THEORETICAL or PRACTICAL) */
    private ProjectType projectType;

    /**
     * Constructs a project with a given title and type.
     *
     * @param name        the title of the project
     * @param projectType the type of the project
     */
    public Project(String name, ProjectType projectType) {
        this.title = name;
        this.projectType = projectType;
    }

    /**
     * Gets the title of the project.
     *
     * @return the project title
     */
    public String getName() {
        return this.title;
    }

    /**
     * Gets the type of the project.
     *
     * @return the project type
     */
    public ProjectType getProjectType() {
        return this.projectType;
    }

    /**
     * Sets the title of the project.
     *
     * @param name the new title of the project
     */
    public void setName(String name) {
        this.title = name;
    }

    /**
     * Sets the type of the project.
     *
     * @param projectType the new project type
     */
    public void setProjectType(ProjectType projectType) {
        this.projectType = projectType;
    }

    /**
     * Checks if this project is equal to another object.
     * Two projects are considered equal if they have the same title and type.
     *
     * @param o the object to compare with
     * @return {@code true} if the objects are equal, otherwise {@code false}
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Project project = (Project) o;
        return Objects.equals(title, project.title) &&
                projectType == project.projectType;
    }

    /**
     * Returns a string representation of the project.
     *
     * @return a formatted string representing the project
     */
    @Override
    public String toString() {
        return "Project{" + "title=" + title + ", projectType=" + projectType + '}';
    }
}
