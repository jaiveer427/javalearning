package stack;

import java.util.ArrayDeque;
import java.util.Deque;


public class StackDemo {

	public static String removeStars(String s) {
		
		Deque<Character> stack = new ArrayDeque<Character>();
		
		for (int i = 0; i < s.length(); i++) {
            char c=s.charAt(i);
            if(c!='*') {
            	stack.offerLast(c);
            }
            else {
            	stack.pollLast();
            	
            }
		}
		
		 StringBuilder result = new StringBuilder();

		    for (char c : stack) {
		        result.append(c);
		    }

		    return result.toString();
	}
	
	public  static String removeStarsA(String s) {
        char[] a = new char[s.length()];
        int j = 0;

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '*') {
                j--;
            } else {
                a[j] = s.charAt(i);
                j++;
            }
        }

        return new String(a, 0, j);
    }
	//Input: s = "3[a2[c]]"
	//Output: "accaccacc"
	
   public static String decodeString(String s) {
	   
        
    }

	public static void main(String[] args) {
		
		String s = "erase*****";
		System.out.println(removeStarsA(s));

	}

}
