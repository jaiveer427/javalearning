package basic;

class Demo {
	private static final int val = 10;

	public Demo() {
		System.out.println("Creating object: " + val);
	}

	static {
		System.out.println("Inside static block: " + val);
	}

	public static void print() {
		System.out.println("value: " + val);
	}
}

public class StaticDemo {

	public static void main(String[] args) {
		Demo d=new Demo();
		Demo.print();
		StaticDemo.main("java");

	}
	public static final  void main(String s) {
		System.out.println("Overloaded main: "+ s);
	}

}
