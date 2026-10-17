package lambdaExpression;

public record Animal(String species, boolean canHop, boolean canSwim) {

	@Override
	public String toString() {
		return "Animal { species= " + species + " ,canHop=  " + canHop + " , canSwim= " + canSwim + " } " + "\n";
	}
}
