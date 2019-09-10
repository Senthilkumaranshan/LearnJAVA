public class Reverser {



    public static void main(String[] args) {

        int number =562873;

        int tens = number%10;
        int hundereds = ((number%100) - tens)/10;
        int thousands = ((number%1000) - hundereds)/100;
        int tenthousands = ((number%10000) - thousands)/1000;
        int lakhs = ((number%100000) - tenthousands)/10000;
        int million = ((number%1000000) - hundereds)/100000;

        System.out.println(tens);
        System.out.println(hundereds);
        System.out.println(thousands);
        System.out.println(tenthousands);
        System.out.println(lakhs);
        System.out.println(million);

        for(int i =10; i<=number;i=i*10){

            System.out.println();


        }


    }
}
