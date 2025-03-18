To implement a Java program that sorts a list of Employee objects (based on name, age, and salary) using lambda expressions and stream operations to demonstrate efficient data processing.

Step 1: Create the Employee Class
-Define an Employee class with the following attributes:
  name (String)
  age (int)
  salary (double)
-Create a constructor to initialize these values.
-Implement a display() method to print employee details.
  
Step 2: Create the Main Class
-Initialize an ArrayList<Employee> and add sample employee data.
-Use lambda expressions to sort the list:
  Sort by Name (Alphabetical order)
  Sort by Age (Ascending order)
  Sort by Salary (Descending order)
  
Step 3: Display the Sorted List
Use forEach() with a method reference to print the sorted employees.


import java.util.*;
import java.util.stream.Collectors;

class Employee {
    String name;
    int age;
    double salary;
    
    public Employee(String name, int age, double salary) {
        this.name = name;
        this.age = age;
        this.salary = salary;
    }
    
    public void display() {
        System.out.println(name + " (" + age + ", " + salary + ")");
    }
}

public class EmployeeSorting {
    public static void main(String[] args) {
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee("Alice", 30, 50000));
        employees.add(new Employee("Bob", 25, 60000));
        employees.add(new Employee("Charlie", 35, 55000));
        employees.add(new Employee("Alex", 28, 45000));
        employees.add(new Employee("Alex", 32, 47000));
        employees.add(new Employee("Alex", 25, 46000));
        employees.add(new Employee("David", 29, 50000));
        employees.add(new Employee("Eve", 31, 50000));
        employees.add(new Employee("Frank", 27, 50000));
        
        // Sorting by Name (Alphabetical Order)
        List<Employee> sortedByName = employees.stream()
                .sorted(Comparator.comparing(e -> e.name))
                .collect(Collectors.toList());
        
        System.out.println("Sorted by Name:");
        sortedByName.forEach(Employee::display);
        
        // Sorting by Age (Ascending Order)
        List<Employee> sortedByAge = employees.stream()
                .sorted(Comparator.comparingInt(e -> e.age))
                .collect(Collectors.toList());
        
        System.out.println("\nSorted by Age:");
        sortedByAge.forEach(Employee::display);
        
        // Sorting by Salary (Descending Order)
        List<Employee> sortedBySalary = employees.stream()
                .sorted(Comparator.comparingDouble((Employee e) -> e.salary).reversed())
                .collect(Collectors.toList());
        
        System.out.println("\nSorted by Salary:");
        sortedBySalary.forEach(Employee::display);
    }
}

Test Cases
Test Case         	Input Data                                                      	                    Expected Output
Case 1:       Sorting by Name	Alice (30, 50000), Bob (25, 60000), Charlie (35, 55000)	                    Alice, Bob, Charlie (sorted alphabetically)
Case 2:       Sorting by Age	Alice (30, 50000), Bob (25, 60000), Charlie (35, 55000)	                    Bob, Alice, Charlie (sorted by age in ascending order)
Case 3:       Sorting by Salary	Alice (30, 50000), Bob (25, 60000), Charlie (35, 55000)              	    Bob, Charlie, Alice (sorted by salary in descending order)
Case 4:       Edge Case - Same Name, Different Age	Alex (28, 45000), Alex (32, 47000), Alex (25, 46000)	Sorted by age (25 → 28 → 32)
Case 5:       Edge Case - Same Salary	David (29, 50000), Eve (31, 50000), Frank (27, 50000)	              Sorted by name if salary is the same
