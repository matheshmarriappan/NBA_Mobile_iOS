package com.nba.ios.api.helper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class test {

	public static void main(String[] args) {
		/*
		Scanner scan = new Scanner(System.in);
		System.out.println("Enter the Year");
		int year = scan.nextInt();
		boolean isYear = false;
		
		if(year%4 == 0) {
			if(year%100 == 0) {
				if(year%400 == 0) {
					isYear = true;
				} else {
					isYear = false;
				}
			} else {
				isYear = true;
			}
		} 
		if(isYear) {
			System.out.println("Year is Leap");
		} else {
			System.out.println("Year is not leap16");
		}*/
		
		checkPalindrome(121);
		//checkPrime(54);
		
		
 	}
	
	
	private static void checkPalindrome(int i) {
		int rev = 0;
        int rem = 0;
        int ori = i;
		while(i>0) {
			rem = i%10;
			rev = rev*10+rem;
			i=i/10;
		}
		if(ori == rev) {
			System.out.println("palindrome");
		} else {
			System.out.println("not palin");
		}
	}


	private static void checkPrime(int num) {
		int count =0;
		int sum=1;
		
		for(int n=2; n<=num; n++) {
			
			for(int i=1;i<=n;i++) {
			if(n%i == 0) {
				count++;
			}
		}
			if(count == 2) {
				sum=sum+n;
				//System.out.println("Prime");
			}
			
			if(n==num&&count==2)
				System.out.println("Given number is prime number");
			else if(n==num)
				System.out.println("Given nuymberf ius not");
			count=0;
		}
		System.out.println(sum);
		
	}
	
}
	
/*for(int i =0; i < 5 ; i++) {
	for (int j = 0; j < 5-i; j++) {
		System.out.println(" ");
	}
	for (int k = 0; k<=i; k++) {
		System.out.println("* ");
	}
	System.out.println();
}*/
		
		
		/*for(int i =2; i<=15; i++) {
            if (i%2==0) {
            	int j=i;
                System.out.print(i+", "=j);
            }
            
            else if (i%2!=0) {
                System.out.print(i +"odd value"+", ");
            }
            
            else {
            	 
            }
            if((i%2==0)&&(i%2!=0)) {
            	 System.out.println("TRying....");
            }
			for(int j=i+1;j<=15;j++) {
			if (j%2==0) {
	                System.out.print(j+", ");
	            }

            
          if (j%2!=0) {
                System.out.print(j +"odd value"+", ");
                List<String> lst = new ArrayList <String> ();
                
                lst.add("string");
            }
            
            else {
            	 
            }
				
			}*/
     


		
/*int a[] = {5,3,9,2,0,6};
  int temp = 0;
for (int i =0 ; i < a.length; i ++) {
	 for (int j = i+1; j < a.length; j ++) {
		 if(a[i] > a[j] ) {
			 temp = a[i];
			 a[i]=a[j];
			 a[j]=temp;
			 
		 }
	 }
	
	 System.out.println(a[i]);
   }*/

	
