import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Application {

    public static void main(String[] args) {

        List<String> list = new ArrayList<>();

        list.add("Saman");
        list.add("Anil");
        list.add("Banu");
        list.add("Elil");
        list.add("Fazil");
        list.add("Selva");
        list.add("Gayan");
        list.add("Mathura");
        list.add("Deshan");
        list.add("Zahura");

        System.out.println(list);
        Collections.sort(list);
        System.out.println("---------------");
        System.out.println(list);
    }
}
