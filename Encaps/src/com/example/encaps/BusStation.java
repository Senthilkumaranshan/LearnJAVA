package com.example.encaps;

import java.util.ArrayList;

public class BusStation {

    private ArrayList<Bus> buses;

    public BusStation(){
        buses = new ArrayList<>();

    }

    public BusStation(String color){

        buses.add(new Bus(color));
    }


    public ArrayList<Bus> getBuses() {

        for (Bus bus:buses){

            validate(bus.getColor());
        }

        return buses;
    }

    public void setBuses(ArrayList<Bus> buses) {
        this.buses = buses;
    }

    public void addBus(String color){
        buses.add(new Bus(color));
    }

    public void addBus(Bus bus) {
        buses.add(bus);
    }


    //3rd level encapsulation
//    public void validate(String color){
//
//        class BusValidator{
//
//            public void ColorValidate(){
//
//                if("Red".equalsIgnoreCase(color)){
//                    System.out.println("valid");
//                }else{
//                    System.out.println("invalid");
//                }
//            }
//        }
//
//        new BusValidator().ColorValidate();
//    }


    //4th level encapsulation
    public void validate(String color){

        new Object(){

            public void ColorValidate(){

                if("Red".equalsIgnoreCase(color)){
                    System.out.println("valid");
                }else{
                    System.out.println("invalid");
                }
            }
        }.ColorValidate();

    }



//
//    static{
//
//        buses = new ArrayList<>();
//        buses.add(new Bus("Red"));
//        buses.add(new Bus("Green"));
//        buses.add(new Bus("White"));
//        buses.add(new Bus("Blue"));
//        buses.add(new Bus("Pink"));
//    }

    class Bus {

        public Bus(){}

        private String color;


        public String getColor() {
            return color;
        }

        public void setColor(String color) {
            this.color = color;
        }

        public Bus(String color) {
            this.color = color;
        }

        @Override
        public String toString() {
            return "Bus{" +
                    "color='" + color + '\'' +
                    '}';
        }
    }
}
