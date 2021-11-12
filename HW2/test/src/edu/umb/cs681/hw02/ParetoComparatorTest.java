package edu.umb.cs681.hw02;

import static org.junit.jupiter.api.Assertions.*;
import java.util.*;
import java.util.Collections;
import java.util.LinkedList;
import org.junit.jupiter.api.Test;
import java.util.stream.Collectors;


class ParetoComparatorTest {
	LinkedList<Car> carList = new LinkedList<Car>();
	
	
	@Test
	void test() {
		Car car1 = new Car("Ford", "F3", 2018, 33, 60000);
		Car car2 = new Car("Mustang", "M2", 2011, 30, 90000);
		carList.add(car1);
		carList.add(car2);
		for (int i = 0; i < carList.size(); i++) {
			carList.get(i).setDominationCount(i);
		}
		List<Car> SortedList = carList.stream()	
                .sorted(Comparator.comparing((Car car) -> car.getDominationCount(), Comparator.reverseOrder()))
                .collect(Collectors.toList());

		assertSame(1, SortedList .get(0).getDominationCount());
		assertSame(0, SortedList .get(1).getDominationCount());
	}
}

