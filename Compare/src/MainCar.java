import java.util.Collections;
import java.util.List;

public class MainCar {

    public static void main(String[] args) {

        List<Car> cars = Car.getCar();
        System.out.println(cars);

        Collections.sort(cars, new Park());
        System.out.println(cars);
    }
}
