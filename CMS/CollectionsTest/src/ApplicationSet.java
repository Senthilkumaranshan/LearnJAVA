import java.util.HashSet;
import java.util.Set;

public class ApplicationSet {

    public static void main(String[] args) {

        Set<Student> students = new HashSet<>();

        Student student1 =new Student(1,"saman","cmb");
        Student student2 =new Student(2,"Anirudh","chn");
        Student student3 =new Student(3,"Banu","Jaffna");
        Student student4 =new Student(4,"Carbon","kuru");

        students.add(student1);
        students.add(student2);
        students.add(student3);

        students.add(student1);
        students.add(student4);


        System.out.println(students);

    }
}
