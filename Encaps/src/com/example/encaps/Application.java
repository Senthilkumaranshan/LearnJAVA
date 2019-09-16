package com.example.encaps;

public class Application {

    public static void main(String[] args) {

    BusStation busStation = new BusStation();
    busStation.addBus("Green");
    busStation.addBus(busStation.new Bus("red"));
    busStation.addBus(new BusStation().new Bus("Blue"));

    busStation.getBuses();

        for(BusStation.Bus bus: busStation.getBuses()){
            System.out.println(bus);
        }


    }
}
