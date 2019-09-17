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

public class JsonStandingsResponse {
	
	public static String COLOR_OPEN = "<font color=\"blue\">";
	public static String GREEN_OPEN = "<font color=\"green\">";
	public static String RED_OPEN = "<font color=\"red\">";
	public static String COLOR_CLOSE = "</font>";
	
	public static String url = "http://data.nba.net/prod/v1/current/standings_conference.json";
	
	public static String getJsonResponseString() throws IOException {
		Response res = APIHelper.getRequest("https://data.nba.net/prod/v1/2019/players/1628389_profile.json", ContentType.JSON);
		String responseString = null;
		responseString =res.body().asString();
		System.out.println(responseString);
		return responseString;
				
	}
	
	public static void getStandingsData(String standingsResponse, String teamID, String winUI, String lossUI, String winPctm, String gb, String conf, String div, String home, String road, String LTen, String streak) throws IOException {
		
		net.minidev.json.JSONArray jsonArr = null;
		ReadContext ctx = JsonPath.parse(standingsResponse);
		System.out.println(ctx);
		
		String win = null;
		String loss = null;
		String winPct = null;
		String gamesBehind = null;
		
		String confWinLoss = null;
		String divWinLoss = null;
		String homeWinLoss = null;
		
		jsonArr = ctx.read("$..[?(@.teamId=='"+teamID+"')]");
		System.out.println("Standings jsonArr "+jsonArr);
		
		JSONParser jParser = new JSONParser();
		try {
			JSONArray jArray = (JSONArray) jParser.parse(jsonArr.toJSONString());
			for (Object data : jArray) {
				com.jayway.restassured.path.json.JsonPath jPath = new com.jayway.restassured.path.json.JsonPath(data.toString());
				
				/*String confRank = jPath.getString("confRank");
				System.out.println("confRank" +confRank);
				compareTwoData(teamRanking.trim(), confRank.trim(), "Rank");*/
				
				win = jPath.getString("win");
				loss = jPath.getString("loss");
				winPct = jPath.getString("winPct");
				gamesBehind = jPath.getString("gamesBehind");
				System.out.println("JSON Standings data win loss and winPct "+win+ " "+ loss + " "+ winPct);
				
				
				compareTwoData(winUI.trim(), win.trim(), "Win");
				compareTwoData(lossUI.trim(), loss.trim(), "Loss");
				compareTwoData(winPctm.trim(), winPct.trim(), "Win Percentage ");
				compareTwoData(gb.trim(), gamesBehind.trim(), "Games Behind ");
				
				
				String confWin = jPath.getString("confWin");
				String confLoss = jPath.getString("confLoss");
				confWinLoss = confWin+" - "+confLoss;
				compareTwoData(conf.trim(), confWinLoss.trim(), "Conf Win Loss");
				System.out.println("gamesBehind "+gamesBehind + " confWinLoss "+confWinLoss);
				
				String divWin = jPath.getString("divWin");
				String divLoss = jPath.getString("divLoss");
				divWinLoss = divWin + " - "+divLoss;
				compareTwoData(div.trim(), divWinLoss.trim(), "Div Win Loss");
				System.out.println("divWinLoss "+divWinLoss);
				homeWinLoss = jPath.getString("homeWin") + " - "+jPath.getString("homeLoss");
				System.out.println("homeWinLoss "+homeWinLoss);
				compareTwoData(home.trim(), homeWinLoss.trim(), "Home Win Loss");
				
				String roadWinLoss = jPath.getString("awayWin") + " - "+jPath.getString("awayLoss");
				System.out.println("roadWinLoss"+roadWinLoss);
				compareTwoData(road.trim(), roadWinLoss.trim(), "Road Win Loss");
				
				String lTenWinLoss = jPath.getString("lastTenWin") + " - "+jPath.getString("lastTenLoss");
				System.out.println("lTenWinLoss.."+lTenWinLoss);
				compareTwoData(LTen.trim(), lTenWinLoss.trim(), "LTen Win Loss");
				
				String streakJson = jPath.getString("streak");
				System.out.println("streakJson"+streakJson);
				compareTwoData(streak.trim(), streakJson.trim(), "Streak");
				
				
				
			}
		} catch (ParseException e) {
			System.out.println("Parser exception occured ");
		}
		
	}
	
	public static void compareTwoData(String uiData, String jsonData, String type) throws IOException
	{
		if((uiData!= null && jsonData != null) && uiData.equalsIgnoreCase(jsonData))
		{
			Reporter.addStepLog("Compared "+COLOR_OPEN+ type + COLOR_CLOSE+ " UI data "+ COLOR_OPEN+ uiData +COLOR_CLOSE + " with Standings Json Data "+COLOR_OPEN+jsonData +COLOR_CLOSE+ " Both are same.");
		} else {
			Reporter.addStepLog("Compared "+RED_OPEN+ type + COLOR_CLOSE+ " UI data "+ COLOR_OPEN+ uiData +COLOR_CLOSE + " with Standings Json Data "+COLOR_OPEN+jsonData +COLOR_CLOSE+ " Both are not same.");
		}
		
	}

}
