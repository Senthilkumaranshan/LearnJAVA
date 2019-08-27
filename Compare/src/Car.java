import javax.rmi.CORBA.StubDelegate;
import java.util.ArrayList;
import java.util.List;

public class Car {

    private int id;
    private String color;

    Car(int id, String color){
        this.id = id;
        this.color= color;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public static List<Car> getCar(){

        List<Car> cars = new ArrayList<>();

        cars.add(new Car(52,"Red"));
        cars.add(new Car(2,"Red"));
        cars.add(new Car(12,"Red"));
        cars.add(new Car(5,"Red"));
        cars.add(new Car(25,"Red"));

        return cars;

    }

    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", color='" + color + '\'' +
                '}';
    }
}
