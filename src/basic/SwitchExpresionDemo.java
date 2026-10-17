package basic;

public class SwitchExpresionDemo {

	enum Season {
		SPRING, SUMMER, FALL, WINTER
	}

	boolean shouldGetACoat(Season s) {
		return switch (s) {
		case SPRING -> false;
		case Season.SUMMER -> false;
		case FALL -> true;
		case Season.WINTER -> true;
		};
	}

	public static String weekDayExp(int day) {
		return switch (day) {
		case 1 -> "Monday";
		case 2 -> "Tuesday";
		case 3 -> "Wednesday";
		case 4 -> "Thrusday";
		case 5 -> "Friday";
		case 6, 7 -> "Weekend";
		default -> "Unknown";

		};
	}

	public static String weekDayStat(int day) {
		return switch (day) {
		case 1 -> "Monday";
		case 2 -> "Tuesday";
		case 3 -> "Wednesday";
		case 4 -> "Thrusday";
		case 5 -> "Friday";
		case 6, 7 -> "Weekend";
		default -> "Unknown";
		};

	}

	public static void main(String[] args) {
		SwitchExpresionDemo obj = new SwitchExpresionDemo();

		System.out.println(weekDayExp(1));

	}

}
