package streams;

import java.util.*;
import java.util.stream.Collectors;

public class StreamPractice {

	public static class Employee {
		private int id;
		private String name;
		private String department;
		private double salary;
		private int age;
		private List<String> skills;
		private boolean isActive;

		public Employee(int id, String name, String department, double salary, int age, List<String> skills,
				boolean isActive) {
			this.id = id;
			this.name = name;
			this.department = department;
			this.salary = salary;
			this.age = age;
			this.skills = skills;
			this.isActive = isActive;
		}

		public int getId() {
			return id;
		}

		public String getName() {
			return name;
		}

		public String getDepartment() {
			return department;
		}

		public double getSalary() {
			return salary;
		}

		public int getAge() {
			return age;
		}

		public List<String> getSkills() {
			return skills;
		}

		public boolean isActive() {
			return isActive;
		}

		@Override
		public String toString() {
			return "Employee{name='" + name + "', dept='" + department + "', salary=" + salary + "}" + "\n";
		}
	}

	public static List<Employee> getEmployees() {
		return Arrays.asList(
				new Employee(1, "Alice Johnson", "Engineering", 115000, 28, Arrays.asList("Java", "Spring Boot", "SQL"),
						true),
				new Employee(2, "Bob Smith", "Engineering", 95000, 34, Arrays.asList("Java", "React", "Docker"), true),
				new Employee(3, "Charlie Brown", "HR", 65000, 41, Arrays.asList("Recruiting", "Excel"), false),
				new Employee(4, "Diana Prince", "Marketing", 88000, 29, Arrays.asList("SEO", "Copywriting", "GA4"),
						true),
				new Employee(5, "Evan Wright", "Engineering", 140000, 45,
						Arrays.asList("Java", "AWS", "Kubernetes", "Architecture"), true),
				new Employee(6, "Fiona Gallagher", "Finance", 105000, 31,
						Arrays.asList("Accounting", "Excel", "Python"), true),
				new Employee(7, "George Clark", "HR", 72000, 26, Arrays.asList("Onboarding", "Communication"), true),
				new Employee(8, "Hannah Abbott", "Marketing", 91000, 32,
						Arrays.asList("Design", "Photoshop", "Branding"), false),
				new Employee(9, "Ian Malcolm", "Finance", 130000, 50, Arrays.asList("Risk Analysis", "Stata", "R"),
						true),
				new Employee(10, "Julia Roberts", "Engineering", 102000, 27,
						Arrays.asList("Python", "Django", "PostgreSQL"), true));
	}

	public static void main(String[] args) {
		List<Employee> employees = getEmployees();
		List<Employee> activeEmployees = employees.stream().filter(Employee::isActive).toList();
		List<String> employeeByName = employees.stream().map(Employee::getName).toList();
		List<Employee> higherEarner = employees.stream().filter(e -> e.getSalary() > 100000).toList();
		// Sort by Salary: Sort employees by salary in descending order.
		// List<Employee>
		// sortBySalary=employees.stream().sorted((e1,e2)->(int)e2.getSalary()-(int)e1.getSalary()).toList();
		List<Employee> sortBySalary = employees.stream()
				.sorted(Comparator.comparingDouble(Employee::getSalary).reversed()).toList();

		// Highest Earner
		Employee highestEarner = employees.stream().max(Comparator.comparingDouble(Employee::getSalary)).orElse(null);

		// Skill List
		List<String> skilllList = employees.stream().map(Employee::getSkills).flatMap(List::stream).distinct().toList();

		// System.out.println(highestEarner);
		boolean legalEmployee = employees.stream().anyMatch(e -> e.getDepartment().equals("Legal"));
		System.out.println(legalEmployee);
		Map<String, List<Employee>> groupByDepartment = employees.stream()
				.collect(Collectors.groupingBy(Employee::getDepartment));
		Map<String, Long> countByDepartment = employees.stream()
				.collect(Collectors.groupingBy(Employee::getDepartment, Collectors.counting()));

		Map<Boolean, List<Employee>> partitionBySalary = employees.stream()
				.collect(Collectors.partitioningBy(e -> e.getSalary() > 90000));

		// System.out.println(groupByDepartment);
		// System.out.println(partitionBySalary);
		// Max Salary Per Department: Find the top-earning employee within each
		// individual department.
		Map<Object, Employee> maxSalByDept = employees.stream()
				.collect(Collectors.groupingBy(e -> e.getDepartment(), Collectors.collectingAndThen(
						Collectors.maxBy(Comparator.comparingDouble(Employee::getSalary)), op -> op.orElse(null))));

		// The Ultimate Inverse Map: Create a mapping where the key is a skill (e.g.,
		// "Java") and the value is a list of employees who possess it.

		Map<String, List<String>> employeeBySkill = employees.stream()
				.flatMap(e -> e.getSkills().stream().map(skill -> new AbstractMap.SimpleEntry<>(skill, e.name)))
				.collect(Collectors.groupingBy(Map.Entry::getKey,
						Collectors.mapping(Map.Entry::getValue, Collectors.toList())));

		System.out.println(employeeBySkill);
	}
}
