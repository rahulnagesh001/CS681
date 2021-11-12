package edu.umb.cs681.hw02;

import static org.junit.jupiter.api.Assertions.*;
import java.util.*;
import java.util.Collections;
import java.util.LinkedList;
import org.junit.jupiter.api.Test;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

class YearComparatorTest {
	
	LinkedList<Car> carList = new LinkedList<Car>();
	@Test
	void test() {
		Car car1 = new Car("Ford", "F3", 2018, 33, 60000);
		Car car2 = new Car("Mustang", "M2", 2011, 30, 90000);
		carList.add(car1);
		carList.add(car2);
		List<Car> SortedList = carList.stream()	
                .sorted((Car c1, Car c2) -> c1.getYear()- c2.getYear()).collect(Collectors.toList());

		
		assertEquals(car1, SortedList.get(1));
		assertEquals(car2, SortedList.get(0));
		
	}

}
