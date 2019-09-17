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
import com.nba.ios.seleniumgluecode.CommonMethods;

public class JsonPlayersResponse {
	
	public static String COLOR_OPEN = "<font color=\"blue\">";
	public static String GREEN_OPEN = "<font color=\"green\">";
	public static String RED_OPEN = "<font color=\"red\">";
	public static String COLOR_CLOSE = "</font>";
	
	public static String produrl = "https://data.nba.net/data/prod/v2/2018/players.json";
	public static String getJsonResponseString() throws IOException {
		Response res = APIHelper.getRequest(produrl, ContentType.JSON);
		String responseString = null;
		responseString =res.body().asString();
		System.out.println(responseString);
		return responseString;
				
	}
	
	public static String getPlayerID(String playersJsonResponse, String temporaryDisplayName) {
		net.minidev.json.JSONArray jsonArr = null;
		ReadContext ctx = JsonPath.parse(playersJsonResponse);
		System.out.println("temporary Name "+temporaryDisplayName);

        System.out.println("playersresonse"+ playersJsonResponse);
		System.out.println("ctx"+ ctx);
		
		String personID = null;
		
		jsonArr = ctx.read("$..[?(@.temporaryDisplayName=='"+temporaryDisplayName+"')]");
		
		//jsonArr = ctx.read("$..[?(@.teamId=='1610612761')]");
		System.out.println("For Plaayers json array "+jsonArr);
		
		JSONParser jParser = new JSONParser();
		try {
			JSONArray jArray = (JSONArray) jParser.parse(jsonArr.toJSONString());
			for (Object data : jArray) {
				com.jayway.restassured.path.json.JsonPath jPath = new com.jayway.restassured.path.json.JsonPath(data.toString());
				System.out.println(jPath.getString("personId"));
				personID = jPath.getString("personId");
			}
		} catch (ParseException e) {
			System.out.println("Parser exception occured ");
		}
		return personID;
	}
	
	/*public static void main(String [] args) throws IOException {
		
		String getPlayersJsonResponse = JsonPlayersResponse.getJsonResponseString();
		
		
		String firstName = "Jaylen";
		String lastName = "Adams";
		String temporaryName = lastName+","+" "+firstName;
		
		String playerID = JsonPlayersResponse.getPlayerID(getPlayersJsonResponse, temporaryName);
		System.out.println("Player Id of the player in json "+ playerID );
		
		String getPlayersProfilesResponse = JsonPlayersProfileResponse.getJsonResponseString(playerID);
	}
	*/

}
