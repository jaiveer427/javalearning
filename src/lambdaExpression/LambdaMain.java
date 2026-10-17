package lambdaExpression;

import java.util.ArrayList;
import java.util.List;

public class LambdaMain {

	public static void main(String[] args) {

		var animals = new ArrayList<Animal>();
		animals.add(new Animal("fish", false, true));
		animals.add(new Animal("kangaroo", true, false));
		animals.add(new Animal("rabbit", true, false));
		animals.add(new Animal("turtle", false, true));
		   
		CheckTrait ct=(a)->a.canHop();
		
     for(Animal a:animals) {
    	 if(ct.test(a)) {
    		 System.out.println(a);
    	 }
     }

	}

}
