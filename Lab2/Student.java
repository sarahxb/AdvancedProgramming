import java.util.ArrayList;
import java.util.List;

class Student {
    private String name;
    private List<Project> preferredProjects;


    public Student(String name) {
        this.name = name;
        this.preferredProjects = new ArrayList<>();
    }


    public String getName() { return name; }
    public List<Project> getPreferredProjects() { return preferredProjects; }

    public void setName(String name) { this.name = name; }
    public void addPreferredProject(Project project) { preferredProjects.add(project); }

    @Override
    public String toString() {
        return "Student{" + "name='" + name + '\'' + ", preferredProjects=" + preferredProjects + '}';
    }
}