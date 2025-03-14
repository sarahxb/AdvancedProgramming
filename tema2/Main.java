

/**
 * Enum representing the types of projects that can be proposed.
 * A project can be either:
 * <ul>
 *     <li>{@code THEORETICAL} - A research project</li>
 *     <li>{@code PRACTICAL} - An implementation-based project</li>
 * </ul>
 */
enum ProjectType {
    /** A theoretical research project */
    THEORETICAL,

    /** A practical, implementation-based project */
    PRACTICAL
}

/**
 * The main class to demonstrate the project allocation system.
 */
public class Main {
    /**
     * The main method where execution starts.
     * It creates students, a teacher, and projects, then allocates projects to students.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {

        // Create a Problem instance with a max of 5 persons
        Problem problem = new Problem(5);

        // Create students
        Student s1 = new Student("Sarah", "2004-11-24", 100);
        Student s2 = new Student("Daniel", "2002-09-21", 101);

        // Create a teacher and add proposed projects
        Teacher t1 = new Teacher("Buliga", "1980-05-10", 2);
        t1.addProject(new Project("Java", ProjectType.PRACTICAL));
        t1.addProject(new Project("AI", ProjectType.THEORETICAL));

        // Add students and teacher to the problem
        problem.addPerson(s1);
        problem.addPerson(s2);
        problem.addPerson(t1);

        // Allocate projects to students
        problem.allocateProjects();

        // Print the details of all persons (students and teachers)
        for (Person person : problem.getAllPersons()) {
            System.out.println(person);
        }
    }
}