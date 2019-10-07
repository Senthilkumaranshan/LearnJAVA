import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static java.util.Collections.reverseOrder;

public class Application {
    public static void main(String[] args) {
        System.out.println("Hello");

        List<String> data = new ArrayList<>(1000);
        data.add("Saman");
        data.add("Nimal");
        data.add("Amara");

        System.out.println(data);

        Collections.sort(data);

        System.out.println(data);

        Collections.sort(data,Collections.reverseOrder());

        System.out.println(data);

    }
}
