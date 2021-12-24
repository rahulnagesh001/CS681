package edu.umb.cs681.hw18;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class Car {
    private String make, model;
    private int mileage, year, price;

    private int dominationCount = 0;

    public Car(String make, String model, int mileage, int year, int price){
        this.make = make;
        this.model = model;
        this.mileage = mileage;
        this.year = year;
        this.price = price;
    }

    public String getMake(){
        return this.make;
    }

    public String getModel(){
        return this.model;
    }


    public int getMileage(){
        return this.mileage;
    }

    public int getYear(){
        return this.year;
    }

    public int getPrice() {
        return this.price;
    }

    public int getDominationCount(){ return this.dominationCount;}

    public void setDominationCount(int newCount){
        this.dominationCount = newCount;
    }


    public boolean dominates(Car otherCar){
        boolean checkprice = this.price <= otherCar.getPrice();
        boolean checkyear = this.year >= otherCar.getYear();
        boolean checkmileage = this.mileage <= otherCar.getMileage();
        boolean any = this.price < otherCar.getPrice() ||
                this.year > otherCar.getYear() ||
                this.mileage < otherCar.getMileage();

        return checkprice && checkyear && checkmileage && any;
    }

    public static void DominationCount(LinkedList<Car> cars){
        for (Car c : cars){
            c.setDominationCount(0);
        }
        for (Car c1 : cars){
            for (Car c2 : cars){
                if (c1.dominates(c2)){
                    c2.setDominationCount(c2.getDominationCount() + 1);
                }
            }
        }
    }


    public static void main(String[] args){

        LinkedList<Car> cars = new LinkedList<>();
        Car car1 = new Car("Ford", "F3", 2018, 33, 60000);
		Car car2 = new Car("Mustang", "M2", 2011, 30, 90000);
		Car car3 = new Car("Cadillac", "C", 2007, 20, 99000);
	
		cars.add(car1);
		cars.add(car2);
		cars.add(car3);
		
        long count = cars.stream()
                .parallel()
                .map((Car car)-> car.getModel())
                .reduce(0, (result, m)-> result + 1, (suma, sumb)-> suma + sumb);
        System.out.println(count);

    }
}