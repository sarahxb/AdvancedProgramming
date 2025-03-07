import java.util.*;


enum ProjectType {
    THEORETICAL, PRACTICAL
}




public class Main {
    public static void main(String[] args) {

        Project p1 = new Project("Project 1", ProjectType.THEORETICAL);
        Project p2 = new Project("Project 2", ProjectType.PRACTICAL);
        Project p3 = new Project("Project 3", ProjectType.PRACTICAL);
        Project p4 = new Project("Project 4", ProjectType.THEORETICAL);


        Student s1 = new Student("Student 1");
        s1.addPreferredProject(p1);
        s1.addPreferredProject(p2);

        Student s2 = new Student("Student 2");
        s2.addPreferredProject(p1);
        s2.addPreferredProject(p3);

        Student s3 = new Student("Student 3");
        s3.addPreferredProject(p3);
        s3.addPreferredProject(p4);

        Student s4 = new Student("Student 4");
        s4.addPreferredProject(p1);
        s4.addPreferredProject(p4);

        System.out.println(s1);
        System.out.println(s2);
        System.out.println(s3);
        System.out.println(s4);
    }
}
