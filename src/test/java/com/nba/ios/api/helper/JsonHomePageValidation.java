package com.nba.ios.api.helper;

import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.ReadContext;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.Response;

public class JsonHomePageValidation {

	public static String url = "https://service-apis-us-east-1.nonprod.nbad.io/data-agg-qa/v0/aggregator/apphomescreen/2018";
	
	public static void main(String[] args) throws IOException {
		String response = getHomesJsonResponse();
		getSpotLightNames(response, "T2VideoorArticleText");
	}
	
	/**
	 * returns the Teams Json Response as a string
	 * @param url
	 * @return
	 * @throws IOException
	 */
	public static String getHomesJsonResponse() throws IOException {
		Response res = APIHelper.getRequest(url, ContentType.JSON);
		String responseString = null;
		responseString =res.body().asString();
		//System.out.println(responseString);
		return responseString;			
	}
	
	
	public static String getHeadlineNames(String responseString, String headlineName) {
		
		int num = 0;
		if(headlineName.equalsIgnoreCase("HeadlineSection1VideoOrArcticle"))
		{
			num = 0;
			System.out.println("Number 0 is added for headline section 1 video or Arcticle");
		} else if(headlineName.equalsIgnoreCase("HeadlineSection2VideoOrArcticle"))
		{
			num = 1;
		}
		
		String spotLightName = null;
		net.minidev.json.JSONArray jsonArr = null;
		ReadContext ctx = JsonPath.parse(responseString);
		
		String spotLightQuery = "$.response.result[*].content[4].content["+num+"]";
			//	"$.league.standard.[?(@.city== '"+cityName+"')]";
		
		jsonArr = ctx.read(spotLightQuery);
	//	System.out.println(jsonArr);
		
		JSONParser jParser = new JSONParser();
		JSONArray jArray = null;
		try {
			jArray = (JSONArray) jParser.parse(jsonArr.toJSONString());
		} catch (ParseException e) {
			System.out.println("Parser Exception occured "+e.getMessage());
		}
		
		for(Object data : jArray)
		{
			//System.out.println(data);
			com.jayway.restassured.path.json.JsonPath jPath = new com.jayway.restassured.path.json.JsonPath(data.toString());
			spotLightName = jPath.getString("headline");
			System.out.println(headlineName + " JSON data is "+spotLightName);
		}
		
		return spotLightName;
	}
	
	public static String getSpotLightNames(String responseString, String spotlightName)
	{
		int num = 0;
		if(spotlightName.equalsIgnoreCase("T1VideoorArticleText"))
		{
			num = 1;
			
		} else if(spotlightName.equalsIgnoreCase("T2VideoorArticleText"))
		{
			num = 2;
		} else if(spotlightName.equalsIgnoreCase("T3VideoorArticleText"))
		{
			num = 3;
		}
		
		String spotLightName = null;
		net.minidev.json.JSONArray jsonArr = null;
		ReadContext ctx = JsonPath.parse(responseString);
		
		String spotLightQuery = "$.response.result[*].content[0].content["+num+"]";
			//	"$.league.standard.[?(@.city== '"+cityName+"')]";
		
		jsonArr = ctx.read(spotLightQuery);
	//	System.out.println(jsonArr);
		
		JSONParser jParser = new JSONParser();
		JSONArray jArray = null;
		try {
			jArray = (JSONArray) jParser.parse(jsonArr.toJSONString());
		} catch (ParseException e) {
			System.out.println("Parser Exception occured "+e.getMessage());
		}
		
		for(Object data : jArray)
		{
			//System.out.println(data);
			com.jayway.restassured.path.json.JsonPath jPath = new com.jayway.restassured.path.json.JsonPath(data.toString());
			spotLightName = jPath.getString("headline");
			System.out.println(spotlightName + " JSON data is "+spotLightName);
		}
		
		return spotLightName;
	}
}