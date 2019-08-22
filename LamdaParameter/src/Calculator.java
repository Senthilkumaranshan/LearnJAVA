public class Calculator {

    public static void main(String[] args) {

      /*  Mathz maths = new Mathz() {
            @Override
            public int power(int i) {
                return i*i;
            }
        };
        System.out.println(maths.power(6));

       */

      Mathz maths =(i)-> i*i;
        System.out.println(maths.power(6));
    }
}
