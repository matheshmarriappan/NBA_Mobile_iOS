package com.mail.helper;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.ios.IOSDriver;

public class Test 
{

	
	public AppiumDriver driver;

	public static void main(String[] args) throws Exception {	
		//new Test().launch();
		
		
		//Fresh question 1
		/*String input = "CamelCaseValue";
		String value = "";
		HashMap<Character, Integer> map = new HashMap<Character,Integer>();
		
		for(int i = 0; i<input.length();i++) {
           if(Character.isUpperCase(input.charAt(i))) {
        	   char c = input.charAt(i);
               if(map.containsKey(c)) {
            	   map.put(c, map.get(c)+1);
               } else {
            	   map.put(c, 1);
               }
           }
		}
   		System.out.println("Map will be: "+ map);

           int out = 0;
           for( Integer val: map.values()) {
        	     out = out+val;
        	     if(val>1) {
        	    	  System.out.println("consective length: "+ val);
        	     }
           }
           System.out.println("length "+ out);
		*/
		
		//Fresh Question 2
		
		int [] a1 = {10, 20, 30, 40};
		int [] a2 = {20, 40, 60,20};
		HashMap<Integer,Integer> map = new HashMap<Integer, Integer>();
		ArrayList<Integer> lst = new ArrayList<Integer>();
	  for(int i = 0; i< a1.length; i++) {
		  if(map.containsKey(a1[i])) {
			  map.put(a1[i], map.get(a1[i])+1);

		  } else {
			  map.put(a1[i], 1);
		  }
	  }
	  
	  for(int i = 0; i< a2.length; i++) {
		  if(map.containsKey(a2[i])) {
			  map.put(a2[i], map.get(a2[i])+1);
		  } else {
			  map.put(a2[i], 1);
		  }	  
		 }

	  System.out.println("map print: "+ map);
	    for(Map.Entry val : map.entrySet()) {
	    	System.out.println("value"+ val.getValue());
	    	  if(val.getValue().equals("1") || val.getValue().equals("3")) {
	    		  System.out.println("test"+ val.getKey());
	    		  //lst.add(val.getKey());
	    	  }
	    }
	    
	    System.out.println("Output..."+ lst);
		/*ArrayList lst = new ArrayList();
		boolean isPres = false;
		'
		for(int i=0; i<a1.length; i++) {
			for(int j=0; j<a2.length; j++) {
				if(a1[i]==a2[j]) {
					lst.add(a1[i]);
				}		
			}
			
		}*/
		
		
		
		/*String out = value.replaceAll("([A-Z])\\1{1,}", "$1");

		System.out.println("String length: " + out);*/
		/*StringBuilder sb = new StringBuilder();
		for(int i =0; i< value.length(); i++) {
			if(i == 0) {
				sb.append(value.charAt(i));
			} else if(value.charAt(i-1)!=value.charAt(i)) {
				sb.append(value.charAt(i));
			}
		}*/
		
		//System.out.println("sb value: "+ sb.length());
		//Duplicates of an String 
		/*String s= "ccv";
		s = s.replaceAll("([a-z])\\1{1,}", "$1");
        System.out.println(s);*/
		/* 
		String output = "";
		for (int i = 0; i < s.length(); i++) {
			if(i==0) {
				output=""+s.charAt(i);
			} else if(s.charAt(i-1)!=s.charAt(i)) {
				output=output+s.charAt(i);
			}
		}
		
		System.out.println("OutPut: "+ output);
		*/
		//Reverse with the same place and dots 
		/*String originalS = "welcome....to..the..world";
        StringBuilder sb2 = new StringBuilder();

		//StringBuilder sb = new StringBuilder();
		String s = originalS.replaceAll("\\.", "a");
		System.out.println("replacedString " + s);
		
		s = s.replaceAll("([a-z])\\1{1,}", "$1");
		System.out.println("s..."+ s);
		
		String[] splitArr = s.split("a");
		
		System.out.println("length"+ splitArr.length);
		for(int i=0; i < splitArr.length; i++) {
        StringBuilder sb = new StringBuilder(splitArr[i]) ;
        sb = sb.reverse();
		System.out.println("Reversed" + sb);
        sb2.append(sb);
		}
		
		System.out.println("Final Reversed:" + sb2);
		
		for(int j = 0; j < originalS.length(); j++) {
			if(originalS.charAt(j)=='.') {
				sb2.insert(j, '.');
			}
		}
		
		System.out.println("Final :"+ sb2);*/
		
		//Remove complete duplicates 
		/*for(int i =0; i <= s.length(); i++) {
			char a = s.charAt(i);
			int occurence = s.indexOf(a, i+1);
			if(occurence == -1) {
				sb.append(a);

			}
			
		}*/
		
		// Reverse using stack
		/*Stack<Character> stack = new Stack<Character>();

	for(int i =0; i<=s.length()-1; i++) {
		stack.push(s.charAt(i));
	}
	
	StringBuilder sb = new StringBuilder();
	while(!stack.isEmpty()) {
		sb.append(stack.pop());
	}
	System.out.println("reversed pop: "+ sb);*/
	
	//Reverse at the same position
		
		/* String str = "Hello Iam Java Program";
		 String strAfterSpace = str.replaceAll("\\s", "");
		 System.out.println("String after removing space" + strAfterSpace);
	      StringBuffer sb= new StringBuffer(strAfterSpace);
	      sb.reverse();
	      System.out.println("Reversed String : "+ sb);
	      for(int i=0 ; i<str.length(); i++){
	      if(str.charAt(i)== ' '){
	         sb.insert(i, " ");
	      }
	   }
	   sb.append("");
	   System.out.println(sb);*/
		
	}

	

	
	private static int function(int sum) {
      
		int result = 0;
		int rem;
			if(sum!=0) {
				rem = sum%10;
				result += rem;
				sum = sum/10;
				function(sum);
			}
			
		//	System.out.println("result: "+ result);
			return result;
		}
	







	public void launch() throws Exception
	{
		DesiredCapabilities caps = new DesiredCapabilities();
		caps.setCapability("platformName", "ios");
		caps.setCapability("platformVersion", "12.0");
		caps.setCapability("deviceName", "iPhone Xs");
		caps.setCapability("udid", "1e93dd47cb72c4eb539fa61bf6f22761de16a371");
		caps.setCapability("automationName", "XCUITest");
		caps.setCapability("app", "com.apple.MobileSMS");
		
	

		driver = new IOSDriver(new URL("http://0.0.0.0:4723/wd/hub"), caps);
		
		
		Thread.sleep(3000);
		driver.findElementByAccessibilityId("Compose").click();
		Thread.sleep(3000);
		driver.findElementByXPath("//XCUIElementTypeTextField[@name=\"To:\"]").sendKeys("9677028084");
		Thread.sleep(3000);
		driver.findElementByAccessibilityId("messageBodyField").click();
		Thread.sleep(3000);
		driver.findElementByAccessibilityId("messageBodyField").sendKeys("gametime://team/1610612737");
		
		driver.findElementByAccessibilityId("sendButton").click();
		Thread.sleep(5000);

		System.out.println("Waiting before click going to click");
		driver.findElementByXPath("//XCUIElementTypeCollectionView[@name='TranscriptCollectionView']//XCUIElementTypeCell[contains(@name,'gametime://team/1610612737')]").click();
		
		


		Thread.sleep(3000);
		

		System.out.println("Done Waiting");
		
		Thread.sleep(3000);
		
		

	}
	
	public void reverseString() {
		String srcStr= "Reverse.. by split..";
				 
        //Splitting the string

        String[] arrStr = srcStr.split("");
        System.out.println(arrStr[0]);
        String strRev = "";

        for(int i=arrStr.length-1;i>=0;i--){
        	
        strRev = strRev + arrStr[i];

        }
        System.out.print("Source string: " + srcStr +"\n");

        System.out.print("The reversed string after split: " + strRev );
	}
}
