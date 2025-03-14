import java.util.ArrayList;
import java.util.List;

/**
 * Represents a teacher who can propose projects for students.
 * Inherits from {@link Person}.
 */
class Teacher extends Person {
    /** An array of projects proposed by the teacher */
    private Project[] proposedProjects;

    /** The number of projects the teacher has proposed */
    private int projectCount;

    /**
     * Constructs a Teacher with a given name, date of birth, and maximum number of projects.
     *
     * @param name        the name of the teacher
     * @param dob         the date of birth of the teacher
     * @param projectCount the maximum number of projects the teacher can propose
     */
    public Teacher(String name, String dob, int projectCount) {
        super(name, dob);
        this.proposedProjects = new Project[projectCount];
        this.projectCount = 0;
    }

    /**
     * Adds a project to the teacher's list of proposed projects.
     *
     * @param project the project to be added
     * @return {@code true} if the project was added successfully, {@code false} if it already exists or the list is full
     */
    public boolean addProject(Project project) {
        for (int i = 0; i < projectCount; i++) {
            if (proposedProjects[i].equals(project)) {
                return false;
            }
        }
        if (projectCount < proposedProjects.length) {
            proposedProjects[projectCount++] = project;
            return true;
        }
        return false;
    }

    /**
     * Retrieves the list of projects proposed by the teacher.
     *
     * @return an array of proposed projects
     */
    public Project[] getProposedProjects() {
        Project[] projects = new Project[projectCount];
        for (int i = 0; i < projectCount; i++) {
            projects[i] = proposedProjects[i];
        }
        return projects;
    }

    /**
     * Checks if this teacher is equal to another object.
     * Two teachers are considered equal if they have the same name and date of birth.
     *
     * @param obj the object to compare with
     * @return {@code true} if the objects are equal, otherwise {@code false}
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Teacher teacher = (Teacher) obj;
        return name.equals(teacher.name) && dob.equals(teacher.dob);
    }

    /**
     * Returns a string representation of the teacher, including their name, date of birth, and proposed projects.
     *
     * @return a formatted string representing the teacher
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Teacher{name='").append(name)
                .append("', dateOfBirth='").append(dob)
                .append("', proposedProjects=[");
        for (int i = 0; i < projectCount; i++) {
            sb.append(proposedProjects[i]);
            if (i < projectCount - 1) {
                sb.append(", ");
            }
        }
        sb.append("]}");
        return sb.toString();
    }
}
