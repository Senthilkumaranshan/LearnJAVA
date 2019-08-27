import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class LamdaComp

{

    public static void main(String[] args) {

        List<Car> cars = Car.getCar();
        System.out.println("Before Sorting");
        System.out.println(cars);

        Comparator<Car> carComp = Comparator.comparing(Car::getId);
        Collections.sort(cars,carComp);
        System.out.println("Ascending Order");
        System.out.println(cars);

        carComp = Comparator.comparing(Car::getId).reversed();
        Collections.sort(cars,carComp);
        System.out.println("Decending Order");
        System.out.println(cars);

    }
}
