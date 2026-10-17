package usecase_employee;

import java.time.LocalDate;
import java.util.List;

class Employee {
	private int id;
	private String name;
	private String department;
	private String designation;
	private int age;
	private double salary;
	private boolean active;
	private List<String> skills;
	private LocalDate joiningDate;

	public Employee(int id, String name, String department, String designation, int age, double salary, boolean active,
			List<String> skills, LocalDate joiningDate) {
		super();
		this.id = id;
		this.name = name;
		this.department = department;
		this.designation = designation;
		this.age = age;
		this.salary = salary;
		this.active = active;
		this.skills = skills;
		this.joiningDate = joiningDate;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public List<String> getSkills() {
		return skills;
	}

	public void setSkills(List<String> skills) {
		this.skills = skills;
	}

	public LocalDate getJoiningDate() {
		return joiningDate;
	}

	public void setJoiningDate(LocalDate joiningDate) {
		this.joiningDate = joiningDate;
	}

	@Override
	public String toString() {
		return " [name=" + name + ", department=" + department + ", designation=" + designation + ", salary="
				+ salary + ", active=" + active + "]\n";
	}
	
	

}
