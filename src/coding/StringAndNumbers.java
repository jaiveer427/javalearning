package coding;

public class StringAndNumbers {
	
	public static boolean isPalindrome(int n) {
		int temp=n;
		int reverseNo=0;
		int digit=0;
		while(temp!=0) {
			digit=temp%10;
			reverseNo=reverseNo*10+digit;
			temp=temp/10;
		}
		
		return n==reverseNo;
	}
	

	public static void main(String[] args) {
		
		System.out.println(isPalindrome(121));

	}

}
