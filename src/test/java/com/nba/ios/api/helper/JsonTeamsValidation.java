package com.nba.ios.api.helper;

import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.aventstack.extentreports.Status;
import com.cucumber.listener.Reporter;
import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.ReadContext;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.Response;
import com.nba.ios.api.helper.APIHelper;

public class JsonTeamsValidation {
	
	static String url = "http://data.nba.net/prod/v2/2018/teams.json";
	public static String COLOR_OPEN = "<font color=\"Blue\">";
    public static String COLOR_CLOSE = "</font>";
	
	/**
	 * returns the Teams Json Response as a string
	 * @param url
	 * @return
	 * @throws IOException
	 */
	public static String getTeamsJsonResponse() throws IOException {
		Response res = APIHelper.getRequest(url, ContentType.JSON);
		String responseString = null;
		responseString =res.body().asString();
		//System.out.println(responseString);
		return responseString;
				
	}
	
	public static String queryCityName(String jsonTeamResponse, String cityName) {
		String teamId = null;
		net.minidev.json.JSONArray jsonArr = null;
		ReadContext ctx = JsonPath.parse(jsonTeamResponse);
		
		String queryCity = "$.league.standard.[?(@.city== '"+cityName+"')]";
		
		jsonArr = ctx.read(queryCity);
		System.out.println(jsonArr);
		
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
			teamId = jPath.getString("teamId");
			System.out.println("City Name "+cityName + "and Team ID "+teamId);
		}
		
		return teamId;
	}
	
	public static String verifyTeamName(String jsonTeamResponse , String name, String type) throws  IOException {
		
		net.minidev.json.JSONArray jsonArr = null;
		ReadContext ctx = JsonPath.parse(jsonTeamResponse);
		
		String teamId = null;
		
		String queryTeamName = "$.league.standard.[?(@.fullName== '"+name+"')]";
		System.out.println("queryTeamName full name "+queryTeamName);
		
		jsonArr = ctx.read(queryTeamName);
		System.out.println(jsonArr);
	
		
		JSONParser jParser = new JSONParser();
		JSONArray jArray = null;
		try {
			jArray = (JSONArray) jParser.parse(jsonArr.toJSONString());
		} catch (ParseException e) {
			System.out.println("Parser Exception occured "+e.getMessage());
		}
		
		System.out.println(jArray);
		
		for(Object data : jArray)
		{
			//System.out.println(data);
			com.jayway.restassured.path.json.JsonPath jPath = new com.jayway.restassured.path.json.JsonPath(data.toString());
			System.out.println("V Team Name "+jPath.getString("teamId"));
			teamId = jPath.getString("teamId");
			System.out.println("team ID for "+name + " is "+teamId);
			String fullName = jPath.getString("fullName");
			//System.out.println("fullName "+fullName + " nickname "+nickName);
			System.out.println("Fullname.equalsname "+fullName.equals(name));
			if((fullName != null && !fullName.isEmpty()) && fullName.equals(name))
			{
				Reporter.addStepLog("Verified the " +COLOR_OPEN+ type+ COLOR_CLOSE+  " Team Name "+ COLOR_OPEN+ fullName +COLOR_CLOSE+ " with Teams JSON data. Both are same");
			} else {
				Reporter.addStepLog(type + " Team Name is not same as per Teams JSON data.");
			}

		}
	
		return teamId;
	}
	
	/**
	 * returns the v Team Names
	 * @param responseString
	 * @param vTeamId
	 * @return
	 * @throws ParseException
	 */
	public static String getVTeamNames(String responseString, String vTeamId) {
		String vTeamName = null;
		String a = "$.league.standard.[?(@.teamId=='";
		String teamId = vTeamId;
		String c = "')]";
		String d = a + teamId + c;
		//System.out.println(d);

		net.minidev.json.JSONArray jsonArr = null;
		ReadContext ctx = JsonPath.parse(responseString);
		jsonArr = ctx.read(d);
		//System.out.println(jsonArr);
		JSONParser jParser = new JSONParser();
		JSONArray jArray;
		try {
			jArray = (JSONArray) jParser.parse(jsonArr.toJSONString());
			for (Object data : jArray) {
				// System.out.println(data);
				com.jayway.restassured.path.json.JsonPath jPath = new com.jayway.restassured.path.json.JsonPath(
						data.toString());
				vTeamName = jPath.getString("nickname");
				
			}
		} catch (ParseException e) {
			System.out.println("Parser exception occured while getting v team names ");
		}
		
		return vTeamName;
	}
	
	/**
	 * returns the h Team Names
	 * @param responseString
	 * @param hTeamId
	 * @return
	 * @throws ParseException
	 */
	public static String getHTeamNames(String responseString, String hTeamId){

		String hTeamName = null;
		String a = "$.league.standard.[?(@.teamId=='";
		String teamId = hTeamId;
		String c = "')]";
		String d = a + teamId + c;
		//System.out.println(d);

		net.minidev.json.JSONArray jsonArr = null;
		ReadContext ctx = JsonPath.parse(responseString);
		jsonArr = ctx.read(d);
		//System.out.println(jsonArr);
		JSONParser jParser = new JSONParser();
		JSONArray jArray;
		try {
			jArray = (JSONArray) jParser.parse(jsonArr.toJSONString());
			for (Object data : jArray) {
				// System.out.println(data);
				com.jayway.restassured.path.json.JsonPath jPath = new com.jayway.restassured.path.json.JsonPath(
						data.toString());
				hTeamName = jPath.getString("fullName");
				System.out.println("hTeamName " + hTeamName);
			}
		} catch (ParseException e) {
			System.out.println("parser exception occured when getting h team names");
		}
		
		return hTeamName;
	}

}
