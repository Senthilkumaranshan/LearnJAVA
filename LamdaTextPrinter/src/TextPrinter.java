public class TextPrinter {


    public static void main(String[] args) {

        //without lamda

        /*Printer p = new Printer() {
            @Override
            public void print() {
                System.out.println("Hi");
            }
        };

        p.print();
        */


        //with lamda

        Printer p=()-> System.out.println("Hi");
        p.print();

    }

}
