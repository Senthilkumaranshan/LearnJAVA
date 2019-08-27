import java.util.List;
import java.util.stream.Collectors;

public class StreamJav {

    public static void main(String[] args) {


        List<Car> cars = Car.getCar();
        System.out.println(cars);

        cars = Car.getCar().stream().sorted((s1,s2)-> new Integer(s1.getId()).compareTo(s2.getId())).collect(Collectors.toList());
        System.out.println("Asc");
        System.out.println(cars);

        cars = Car.getCar().stream().sorted((s1,s2)-> new Integer(-s1.getId()).compareTo(s2.getId())).collect(Collectors.toList());
        System.out.println("Desc");
        System.out.println(cars);
    }
}
