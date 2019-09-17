package nba_cucumber;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.Scanner;

public class Test {

	public static void main(String[] args) throws IOException {
		
	// reverse a number	
		
		int n = 321;
		int revNum = 0;
		
		while(n!=0) {
			revNum = revNum * 10;
			revNum = revNum + n % 10;
			n = n/10;
		}
		
		System.out.println("reverse number..."+ revNum);
		
		
		// reverse a string 
		
		/*String s ="MADAM";
		int size = s.length();
		char [] c= s.toCharArray();
		
		String rev = "";
		
		for(int i=size-1; i >=0  ; i-- ) {
			rev = rev + s.charAt(i);

		}
		 System.out.println("reversed string " + rev);*/
		////////////////////
	/*	int counter=5,  temp;
		Integer num[] = new Integer[] {7,4,2,3,4};*/
		
		//Collections.reverse(Arrays.asList(num));
		//Arrays.sort(num,Collections.reverseOrder());
		/*for(int i=0; i<counter; i++)
		{
		   System.out.print(num[i]+ "  ");
		} */
		
		//System.out.println(Arrays.toString(num));
	//reversing an array	
		
		/*j = counter - 1;     
		i = 0;         
		while(i<j)
		{
	  	   temp = number[i];
		   number[i] = number[j];
		   number[j] = temp;
		   i++;
		   j--;
		}

		System.out.print("Reversed array: ");
		for(i=0; i<counter; i++)
		{
		   System.out.print(number[i]+ "  ");
		}       */
		
		
		// sorting an arrray
/*		
		for(int i=0;i<counter;i++) {
			for(int j=i+1;j<counter;j++) {
				if(num[i]>num[j]) {
					temp = num[i];
					num[i]=num[j];
					num[j]=temp;
				}
			}
		}
		for(int i=0;i<=counter-1;i++) {
			System.out.println("reverse"+num[i]+ ",");
		*/}


}
