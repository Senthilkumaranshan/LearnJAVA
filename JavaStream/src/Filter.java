import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

public class Filter {

    public static void main(String[] args) {
//        printStudentsWithShortNames();
//        filterWithStream();
//        processWithoutStream();
//        processWithStream();
//        minWithStream();
//        maxWithStream();
        countWithStream();


    }

    static void processWithoutStream(){

        Student.getStudent().stream().map(s->new Student("Dr "+s.getName(),s.getId()))
                .collect(Collectors.toList()).forEach(s-> System.out.println(s.getName()));
    }

    static void processWithStream(){
        List<Student> students = Student.getStudent().stream().map(s->new Student("Dr "+s.getName(),s.getId())).collect(Collectors.toList());
        System.out.println(students);
    }

    static  void filterWithStream(){
        List<Student> students = Student.getStudent().stream().filter(s->s.getName().length()<=6)
                .collect(Collectors.toList());
        System.out.println(students.toString());
    }

    static void printStudentsWithShortNames(){

        List<Student> students = Student.getStudent();

        for(Student student:students){
            if(student.getName().length()<=5){
                System.out.println(student.getName());
            }
        }
    }

    //Min
    static void minWithStream() {
        List<Student> students = Student.getStudent();
        System.out.println(
                students.stream()
                .min(Comparator.comparing(Student::getId))
                .orElseThrow(NoSuchElementException::new)
        );

    }

    //Max
    static void maxWithStream() {
        List<Student> students = Student.getStudent();
        System.out.println(
                students.stream()
                        .max(Comparator.comparing(Student::getId))
                        .orElseThrow(NoSuchElementException::new)
        );

    }

    //count
    static void countWithStream() {
        List<Student> students = Student.getStudent();
        System.out.println(
                students.stream().filter(s->s.getId() <25).count()
        );

    }
}
