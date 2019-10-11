import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ApplicationStudent {

    public static void main(String[] args) {

        List<Student> list = new ArrayList<>();
        list.add(new Student(1,"saman","colombo"));
        list.add(new Student(2,"Arman","Galle"));
        list.add(new Student(3,"Malik","Matara"));
        list.add(new Student(4,"Kavushik","Jaffna"));
        list.add(new Student(5,"Niru","Badulla"));

        System.out.println(list.toString());

        System.out.println("---------------------------------------");


    }
}
