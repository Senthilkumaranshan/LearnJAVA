public class Application {

    public static void main(String[] args) {

        Car car = new Car();
        Bus bus = new Bus();

        Vehicle<Car> vehicleCar= new Vehicle<Car>(car);
        vehicleCar.drive();

        Vehicle<Bus> busVehicle = new Vehicle<Bus>(bus);
        busVehicle.drive();

        ArrayPrinter arrayPrinter = new ArrayPrinter();
        Integer integer[] = {1,2,3,4,5,6};
        String  words[] = {"a","b","c","d"};

        arrayPrinter.printArray(integer);
        arrayPrinter.printArray(words);
    }
}
