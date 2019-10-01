public class Application {

    public void test(){
        System.out.println("No parameters");
    }

    public void test(int i){
        System.out.println(i+"int i");
    }

    public void test(double i){
        System.out.println("double i");
    }

    public void test(float i){
        System.out.println("float i");
    }

    public static void main(String[] args) {

        //invoke no argument method
        Application application = new Application();
        application.test();

        //invoke int argument method\
        application.test(10);

        //invoke float method
        application.test(10.5f);

        //testing automatic promotion
        application.test('a');
    }
}
