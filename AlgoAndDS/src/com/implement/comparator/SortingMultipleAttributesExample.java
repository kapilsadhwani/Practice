package com.implement.comparator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * This program demonstrates how to sort a list collection by multiple
 * attributes using a chained comparator.
 *
 * @author www.codejava.net
 *
 */
public class SortingMultipleAttributesExample {

	public static void main(String[] args) {

		System.out.println("===== SORTING BY MULTIPLE ATTRIBUTES =====");

		List<Employee> listEmployees = new ArrayList<Employee>();

		listEmployees.add(new Employee("Tom", "Developer", 45, 80000));
		listEmployees.add(new Employee("Sam", "Designer", 30, 75000));
		listEmployees.add(new Employee("Bob", "Designer", 45, 134000));
		listEmployees.add(new Employee("Peter", "Programmer", 25, 60000));
		listEmployees.add(new Employee("Tim", "Designer", 45, 130000));
		listEmployees.add(new Employee("Craig", "Programmer", 30, 52000));
		listEmployees.add(new Employee("Anne", "Programmer", 25, 51000));
		listEmployees.add(new Employee("Alex", "Designer", 30, 120000));
		listEmployees.add(new Employee("Bill", "Programmer", 22, 30000));
		listEmployees.add(new Employee("Samuel", "Developer", 28, 80000));
		listEmployees.add(new Employee("John", "Developer", 35, 67000));
		listEmployees.add(new Employee("Patrick", "Developer", 35, 140000));
		listEmployees.add(new Employee("Alice", "Programmer", 35, 80000));
		listEmployees.add(new Employee("David", "Developer", 35, 99000));
		listEmployees.add(new Employee("Jane", "Designer", 30, 82000));

		System.out.println("*** Before sorting:");

		for (Employee emp : listEmployees) {
			System.out.println(emp);
		}

		Collections.sort(listEmployees, new EmployeeChainedComparator(
				new EmployeeJobTitleComparator(),
				new EmployeeAgeComparator(),
				new EmployeeSalaryComparator())
		);

		System.out.println("\n*** After sorting:");

		for (Employee emp : listEmployees) {
			System.out.println(emp);
		}

	}
}