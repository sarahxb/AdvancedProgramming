/**
 * Represents a student who can be assigned a project.
 * Inherits from {@link Person}.
 */
public class Student extends Person {
    /** The unique registration number of the student */
    private int registrationNumber;

    /** The project allocated to the student, or null if no project is assigned */
    private Project allocatedProject;

    /**
     * Constructs a student with a name, birth date, and registration number.
     *
     * @param name             the student's name
     * @param dateOfBirth      the student's birth date
     * @param registrationNumber the student's unique registration number
     */
    public Student(String name, String dateOfBirth, int registrationNumber) {
        super(name, dateOfBirth);
        this.registrationNumber = registrationNumber;
        this.allocatedProject = null;
    }

    /**
     * Gets the registration number of the student.
     *
     * @return the registration number
     */
    public int getRegistrationNumber() {
        return registrationNumber;
    }

    /**
     * Sets the registration number of the student.
     *
     * @param registrationNumber the registration number
     */
    public void setRegistrationNumber(int registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    /**
     * Gets the project allocated to the student.
     *
     * @return the allocated project, or null if none assigned
     */
    public Project getAllocatedProject() {
        return allocatedProject;
    }

    /**
     * Sets the project allocated to the student.
     *
     * @param allocatedProject the project to allocate to the student
     */
    public void setAllocatedProject(Project allocatedProject) {
        this.allocatedProject = allocatedProject;
    }

    /**
     * Checks if this student is equal to another object.
     * Two students are considered equal if they have the same registration number.
     *
     * @param obj the object to compare with
     * @return {@code true} if the objects are equal, otherwise {@code false}
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Student student = (Student) obj;
        return registrationNumber == student.registrationNumber;
    }

    /**
     * Returns a string representation of the student.
     *
     * @return a formatted string representing the student and their allocated project
     */
    @Override
    public String toString() {
        return "Student{name='" + name + "', dateOfBirth='" + dob + "', registrationNumber="
                + registrationNumber + ", allocatedProject=" + allocatedProject + "}";
    }
}