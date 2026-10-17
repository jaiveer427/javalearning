package usecase_employee;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import streams.Dish;

public class Main {

	public static void main(String[] args) {
		List<Employee> employees = List.of(

				new Employee(1, "Amit", "IT", "Backend Engineer", 28, 85000, true, List.of("Java", "Spring", "Kafka"),
						LocalDate.of(2022, 3, 10)),

				new Employee(2, "Rohit", "IT", "Frontend Engineer", 26, 78000, true,
						List.of("JavaScript", "React", "CSS"), LocalDate.of(2023, 1, 15)),

				new Employee(3, "Neha", "HR", "HR Manager", 35, 65000, true, List.of("Recruitment", "Communication"),
						LocalDate.of(2021, 7, 1)),

				new Employee(4, "Priya", "Finance", "Accountant", 32, 72000, false, List.of("Accounting", "Excel"),
						LocalDate.of(2020, 5, 20)),

				new Employee(5, "Karan", "IT", "DevOps Engineer", 30, 95000, true, List.of("AWS", "Docker", "Kafka"),
						LocalDate.of(2022, 11, 5)),

				new Employee(6, "Sneha", "Finance", "Financial Analyst", 29, 88000, true,
						List.of("Finance", "Excel", "PowerBI"), LocalDate.of(2023, 6, 10)),

				new Employee(7, "Rahul", "HR", "Recruiter", 27, 55000, false, List.of("Recruitment", "Interviewing"),
						LocalDate.of(2022, 8, 18)),

				new Employee(8, "Ankit", "IT", "Backend Engineer", 34, 105000, true,
						List.of("Java", "Spring", "Microservices"), LocalDate.of(2019, 9, 12)),

				new Employee(9, "Amit", "IT", "Tech Lead", 38, 130000, true, List.of("Java", "Architecture", "Kafka"),
						LocalDate.of(2018, 2, 25)),

				new Employee(10, "Meena", "Finance", "Finance Manager", 41, 120000, true,
						List.of("Finance", "Leadership"), LocalDate.of(2017, 4, 30)));

		System.out.println(".............................Employee Dashboard................................\n");

		// System.out.println(employees);

		// Get names of all active employees
		List<String> activeEmployee = employees.stream().filter(emp -> emp.isActive() == true).map(emp -> emp.getName())
				.collect(Collectors.toList());

		// Get employees whose salary is greater than 80,000
		List<Employee> aboveSalaryEmployee = employees.stream().filter(emp -> emp.getSalary() > 80000).toList();

		Double avgSalary = employees.stream().collect(Collectors.averagingDouble(Employee::getSalary));

		// Convert employee list into a map
		Map<Object, List<Employee>> employeesById = employees.stream()
				.collect(Collectors.groupingBy(emp -> emp.getId()));

		// group by department
		Map<String, List<Employee>> groupByDepartment = employees.stream()
				.collect(Collectors.groupingBy(e -> e.getDepartment()));

		// Count employees per department
		Map<String, Long> employeeCountByDepartment = employees.stream()
				.collect(Collectors.groupingBy(e -> e.getDepartment(), Collectors.counting()));

		// Average salary per department
		Map<String, Double> averageSalaryPerDepartment = employees.stream().collect(
				Collectors.groupingBy(e -> e.getDepartment(), Collectors.averagingDouble(Employee::getSalary)));

		// Highest paid employee in each department

		/*
		 * Map<String, Optional<Employee>> highestPaidEmployeePerDepartment =
		 * employees.stream() .collect(Collectors.groupingBy(emp -> emp.getDepartment(),
		 * Collectors.reducing((e1, e2) -> e1.getSalary() > e2.getSalary() ? e1 : e2)));
		 */

		Map<String, Optional<Employee>> highestPaidEmployeePerDepartment = employees.stream()
				.collect(Collectors.groupingBy(Employee::getDepartment,
						Collectors.maxBy(Comparator.comparingDouble(Employee::getSalary))));

		// List employee names per department

		Map<String, List<String>> employeeNamePerDepartment = employees.stream().collect(Collectors
				.groupingBy(Employee::getDepartment, Collectors.mapping(Employee::getName, Collectors.toList())));

		// Partition employees by active/inactive

		Map<Boolean, List<Employee>> partitionByActive = employees.stream()
				.collect(Collectors.partitioningBy(e -> e.isActive()));

		// Partition employees by salary > 1,00,000

		Map<Boolean, List<Employee>> partitionEmployeeBySalary = employees.stream()
				.collect(Collectors.partitioningBy(e -> e.getSalary() > 100000));

		// Count active vs inactive employees

		Map<Boolean, Long> countActiveInActive = employees.stream()
				.collect(Collectors.partitioningBy(e -> e.isActive(), Collectors.counting()));

		// Get all unique skills across the company
		Set<String> uniqueSkills = employees.stream().map(e -> e.getSkills()).flatMap(List::stream).distinct()
				.collect(Collectors.toSet());

		// 1️⃣5️⃣ Count employees per skill

		Map<String, Long> noOfEmployeesBySkills = employees.stream().map(e -> e.getSkills()).flatMap(List::stream)
				.collect(Collectors.groupingBy(e -> e, Collectors.counting()));

		// 1️⃣6️⃣ Find skill-wise highest paid employee
		Map<String, Employee> highestpaidBySkill = employees.stream()
				.flatMap(
						emp -> emp.getSkills().stream().map(skill -> Map.entry(skill, emp)))
				.collect(
						Collectors
								.groupingBy(Map.Entry::getKey,
										Collectors
												.collectingAndThen(
														Collectors.mapping(Map.Entry::getValue,
																Collectors.maxBy(Comparator
																		.comparingDouble(Employee::getSalary))),
														Optional::get)));

		// List departments that use "Kafka" skill

		Set<String> departmentWithKafkaSkills = employees.stream().filter(emp -> emp.getSkills().contains("Kafka"))
				.map(Employee::getDepartment).collect(Collectors.toSet());

		// Employees who joined in last 6 months
		/*
		 * List<Employee> joinedInLast_6_Months = employees.stream() .filter(emp ->
		 * ChronoUnit.MONTHS.between(emp.getJoiningDate(), LocalDate.now())
		 * <=6).toList();
		 */

		List<Employee> joinedInLast_6_Months = employees.stream()
				.filter(emp -> emp.getJoiningDate().isAfter(LocalDate.now().minusMonths(8))).toList();

		// Group employees by joining year
		Map<Integer, List<Employee>> groupByJoiningYear = employees.stream()
				.collect(Collectors.groupingBy(emp -> emp.getJoiningDate().getYear()));

		// 2️⃣0️⃣ Find most recently joined employee per department
		Map<String, Employee> recentlyJoinedEmployeesPerDepartment = employees.stream()
				.collect(Collectors.groupingBy(Employee::getDepartment, Collectors.collectingAndThen(
						Collectors.maxBy(Comparator.comparing(Employee::getJoiningDate)), Optional::get)));
		// 2️⃣1️⃣ Top 3 highest paid employees

		List<Employee> top_3_HighestPaidEmployee = employees.stream()
				.sorted((e1, e2) -> (int) e2.getSalary() - (int) e1.getSalary()).limit(3).collect(Collectors.toList());
		// 2️⃣2️⃣ Top 2 highest paid employees per department

		Map<String, List<Employee>> highestpaidEmployeePerDepartment = employees.stream()
				.collect(Collectors.groupingBy(Employee::getDepartment,
						Collectors.collectingAndThen(Collectors.toList(),
								list -> list.stream().sorted(Comparator.comparingDouble(Employee::getSalary).reversed())
										.limit(2).toList())));

		// Sort employees by: department then salary descending,then age ascending
		List<Employee> sortedList = employees.stream()
				.sorted(Comparator.comparing(Employee::getDepartment)
						.thenComparing(Comparator.comparingDouble(Employee::getSalary).reversed()
								.thenComparing(Comparator.comparingInt(Employee::getAge))))
				.toList();

		/*
		 * System.out.println(
		 * "............................Solutions....................................");
		 * System.out.println("Active Employee : " + activeEmployee);
		 * System.out.println("Emloyees salaray greater than 80000: " +
		 * aboveSalaryEmployee); System.out.println("Average salary : " + avgSalary);
		 * System.out.println("Employee Map: " + employeesById);
		 * System.out.println("Employee by department :" + groupByDepartment);
		 * System.out.println("Employee count per department :" +
		 * employeeCountByDepartment);
		 * System.out.println("Average Salary Per Department : " +
		 * averageSalaryPerDepartment);
		 * System.out.println(" highestPaidEmployeePerDepartment:" +
		 * highestPaidEmployeePerDepartment);
		 * 
		 * System.out.println("Employyes name per department :" +
		 * employeeNamePerDepartment);
		 * 
		 * System.out.println(" Partition employees by active/inactive" +
		 * partitionByActive);
		 * 
		 * System.out.println("Partition employees by salary > 1,00,000: " +
		 * partitionEmployeeBySalary);
		 * 
		 * System.out.println(" countActiveInActive :" + countActiveInActive);
		 * System.out.println("Unique Skills : " + uniqueSkills);
		 */

		// System.out.println("noOfEmployeesBySkills: " + noOfEmployeesBySkills);
		// System.out.println("highestpaidBySkill " + highestpaidBySkill);

		// System.out.println("departmentWithKafkaSkills: " +
		// departmentWithKafkaSkills);
		// System.out.println(" joinedInLast_6_Months: " + joinedInLast_6_Months);
		// System.out.println(" groupByJoiningYear: " + groupByJoiningYear);

		// System.out.println("recentlyJoinedEmployeesPerDepartment: " +
		// recentlyJoinedEmployeesPerDepartment);
		// System.out.println("top_3_HighestPaidEmployee :" +
		// top_3_HighestPaidEmployee);
		System.out.println(" highestpaidEmployeePerDepartment: " + highestpaidEmployeePerDepartment);
		System.out.println(" sortedList: " + sortedList);

	}

}
