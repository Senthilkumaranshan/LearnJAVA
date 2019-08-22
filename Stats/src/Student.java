public class Student {

    Student(){
        System.out.println("This is constructor");
    }

    static {

        System.out.println("This is a static block");
    }
    {
        System.out.println("This is a anonymous block");
    }

    void print(String str){

        System.out.println(str);
    }
}
