/**
 * Represents the problem which involves students, teachers, and project allocation.
 */

class Problem {
    /** Array to store students and teachers */
    private Person[] people;

    /** The current number of people added */
    private int personCount;

    /**
     * Constructs a problem instance with a maximum number of people.
     *
     * @param maxPersons the maximum number of people (students and teachers)
     */
    public Problem(int maxPersons) {
        people = new Person[maxPersons];
        personCount = 0;
    }

    /**
     * Adds a person (student or teacher) to the problem.
     *
     * @param person the person to be added
     * @return {@code true} if the person was added successfully, {@code false} if already exists
     */
    public boolean addPerson(Person person) {
        for (int i = 0; i < personCount; i++) {
            if (people[i].equals(person)) return false;
        }
        if (personCount < people.length) {
            people[personCount++] = person;
            return true;
        }
        return false;
    }

    /**
     * Retrieves all persons involved in the problem.
     *
     * @return an array of all persons (students and teachers)
     */
    public Person[] getAllPersons() {
        Person[] allPersons = new Person[personCount];
        System.arraycopy(people, 0, allPersons, 0, personCount);
        return allPersons;
    }

    /**
     * Allocates projects to students using a simple greedy approach.
     * Each student receives the first available project from any teacher.
     */
    public void allocateProjects() {
        for (int i = 0; i < personCount; i++) {
            if (people[i] instanceof Teacher) {
                Teacher teacher = (Teacher) people[i];
                Project[] projects = teacher.getProposedProjects();
                for (Project project : projects) {
                    for (int j = 0; j < personCount; j++) {
                        if (people[j] instanceof Student) {
                            Student student = (Student) people[j];
                            if (student.getAllocatedProject() == null) {
                                student.setAllocatedProject(project);
                                System.out.println("Allocating project " + project.getName() + " to " + student.name);
                                break;
                            }
                        }
                    }
                }
            }
        }
    }}