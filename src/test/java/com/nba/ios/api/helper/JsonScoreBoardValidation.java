package com.nba.ios.api.helper;

import java.io.IOException;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.ReadContext;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.Response;


public class JsonScoreBoardValidation {

	/**
	 * returns the Json Response as a string
	 * @param url
	 * @return
	 * @throws IOException
	 */
	public static String getJsonResponseString(String url) throws IOException {
		Response res = APIHelper.getRequest(url, ContentType.JSON);
		String responseString = null;
		responseString =res.body().asString();
		System.out.println(responseString);
		return responseString;
				
	}
	
	/**
	 * returns the no of games size
	 * @param responseString
	 * @return
	 */
	public static int getNoOfGames(String responseString) {
		
		net.minidev.json.JSONArray jsonArr = null;
		ReadContext ctx = JsonPath.parse(responseString);
		jsonArr = ctx.read("$.games");
		System.out.println(jsonArr.size());
		
		int noOfGames = jsonArr.size();
		return noOfGames;

		
	}

	public static ArrayList<String> getVTeamIds(String responseString) {
		
		ArrayList<String> vTeamList = new ArrayList<String>();
		
		net.minidev.json.JSONArray jsonArr = null;
		ReadContext ctx = JsonPath.parse(responseString);
		jsonArr = ctx.read("$.games");
		System.out.println(jsonArr.size());
		JSONParser jParser = new JSONParser();
		try {
			JSONArray jArray = (JSONArray) jParser.parse(jsonArr.toJSONString());
			for (Object data : jArray) {
				com.jayway.restassured.path.json.JsonPath jPath = new com.jayway.restassured.path.json.JsonPath(data.toString());
				//System.out.println("V Team Name "+jPath.getString("vTeam.teamId"));
				vTeamList.add(jPath.getString("vTeam.teamId"));
			}
		} catch (ParseException e) {
			System.out.println("Parser exception occured ");
		}
		return vTeamList;
	}
	
	public static ArrayList<String> getHTeamIds(String responseString) {
		
		ArrayList<String> hTeamList = new ArrayList<String>();
		
		net.minidev.json.JSONArray jsonArr = null;
		ReadContext ctx = JsonPath.parse(responseString);
		jsonArr = ctx.read("$.games");
		System.out.println(jsonArr.size());
		JSONParser jParser = new JSONParser();
		try {
			JSONArray jArray = (JSONArray) jParser.parse(jsonArr.toJSONString());
			for (Object data : jArray) {
				com.jayway.restassured.path.json.JsonPath jPath = new com.jayway.restassured.path.json.JsonPath(data.toString());
				//System.out.println(" H Team Name "+jPath.getString("hTeam.teamId"));
				hTeamList.add(jPath.getString("hTeam.teamId"));
			
			}
		} catch (ParseException e) {
			System.out.println("Parser exception occured ");
		}
		
		return hTeamList;

	}
	
	public static String validateScore(String scoreBaordJsonResponse, String getTeamID, String string) {
		net.minidev.json.JSONArray jsonArr = null;
		ReadContext ctx = JsonPath.parse(scoreBaordJsonResponse);
		System.out.println(ctx);
		
		String jsonScore = null;
		String gameId = getTeamID;
		jsonArr = ctx.read("$..[?(@.teamId=='"+gameId+"')]");
		//jsonArr = ctx.read("$..[?(@.teamId=='1610612761')]");
		System.out.println("For Scoreeeeeeeeee "+jsonArr);
		
		JSONParser jParser = new JSONParser();
		try {
			JSONArray jArray = (JSONArray) jParser.parse(jsonArr.toJSONString());
			for (Object data : jArray) {
				com.jayway.restassured.path.json.JsonPath jPath = new com.jayway.restassured.path.json.JsonPath(data.toString());
				System.out.println(jPath.getString("score"));
				jsonScore = jPath.getString("score");
			}
		} catch (ParseException e) {
			System.out.println("Parser exception occured ");
		}
		return jsonScore;
	}
}
