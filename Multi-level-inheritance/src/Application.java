public class Application {

    public static void main(String[] args) {
        SchoolImpl school = new SchoolImpl();
        school.enroll();

        Student students= new SchoolImpl();
        students.enroll();

        Staff staffs = new SchoolImpl();
        staffs.enroll();
    }
}
