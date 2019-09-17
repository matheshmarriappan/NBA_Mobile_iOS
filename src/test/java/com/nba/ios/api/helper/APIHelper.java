package com.nba.ios.api.helper;


import static com.jayway.restassured.RestAssured.given;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import com.aventstack.extentreports.Status;
import com.jayway.restassured.RestAssured;
import com.jayway.restassured.config.MultiPartConfig;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.Response;



public class APIHelper {
	public static Response postRequest(String endPoint,ContentType contentType,String Auth,String content) throws IOException{
		Response response = null;
		try{
			response = given().
					contentType(contentType).
					header("Authorization", Auth).
				    body(content).
				when().
				    post(endPoint).
				then().
				extract().
			        response();	
			long time =  given().
					contentType(contentType).
					header("Authorization", Auth).
				    body(content).
				when().
				    post(endPoint).time();
			
			System.out.println("The response time for the post request "+endPoint+" is -- "+time +" milliseconds");
			}catch(Exception e){
				
				System.out.println("Fail Due to "+e.getMessage()+", the scenario got failed.");
		}
		return response;
	}
	
	
	
	public static Response getRequest(String endpoint,ContentType contentType) throws IOException{
		Response response = null;
		try{
			response = given().
					contentType(contentType).
						when().
								get(endpoint).
						then().								
						extract().
							response();
			}catch(Exception e){
				
				System.out.println("Due to "+e.getMessage()+", the scenario got failed.");
		}
		return response;
	}
	

	
	public static void validateResponseCode(String url,int exceptedCode) throws IOException{
        Response response = given().
                    when().
                        get(url).then().extract().response();
        int code = response.getStatusCode();
        if(code == exceptedCode){
          
           System.out.println("For the url, "+url+" and response code is "+code);
       }else{
           
           System.out.println("For the url, "+url+" and response code is "+code+" doesn't match with the excepted code "+exceptedCode);
       }
    }
	
}
