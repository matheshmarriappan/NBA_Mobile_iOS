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

public class JsonPlayersProfileResponse {
	
	public static String COLOR_OPEN = "<font color=\"blue\">";
	public static String GREEN_OPEN = "<font color=\"green\">";
	public static String RED_OPEN = "<font color=\"red\">";
	public static String COLOR_CLOSE = "</font>";
	
	//public static String url = "https://data.nba.net/prod/v1/2019/players/1629121_profile.json";
	
	public static String getJsonResponseString(String playerID) throws IOException {
		String url = getPlayersJsonUrl(playerID);
		Response res = APIHelper.getRequest(url, ContentType.JSON);
		String responseString = null;
		responseString =res.body().asString();
		System.out.println("Response string of players profile json "+ responseString);
		return responseString;
				
	}
	
	/**
	 * create json URL
	 */
	/**
	 * this method will create the json url with the provided date and month data
	 * 
	 * @param dateDisplayed
	 * @return
	 * @throws IOException
	 */
	private static String getPlayersJsonUrl(String playerID) throws IOException {
		String getAPKPath = System.getProperty("InstallAPK");
		System.out.println("getAPKPath " + getAPKPath);
		
		String finalURL = "";
		String u1Prod = "https://data.nba.net/prod/v1/";
		String u1Dev = "https://data.sim.nba.net/sweetpotato/v1/";
		//https://data.sim.nba.net/sweetpotato/v2/20180116/scoreboard.json
		String u2 = "/players/";
		String u3 = playerID+"_profile.json";
		if (getAPKPath.equalsIgnoreCase("QA_APP")) {
			finalURL = u1Dev + "2018" + u2 + u3;
		} else if(getAPKPath.equalsIgnoreCase("PROD_APP")) {				
			finalURL = u1Prod + "2019" + u2 + u3;
		}
		System.out.println("Final JSON URL for players profile " + finalURL);
		Reporter.addStepLog("JSON URL for Players Profile Page is " + COLOR_OPEN + finalURL + COLOR_CLOSE);

		return finalURL;
	}
	

	public static void getLatestStatsData(String playersProfileResponse, String year, String whichData, String ppg, String rpg, String apg, String spg, String bpg, String fgPercent, String threePercent, String ftPercent) throws IOException {
		

		System.out.println("Entering " + whichData);
		net.minidev.json.JSONArray jsonArr = null;
		ReadContext ctx = JsonPath.parse(playersProfileResponse);
		System.out.println(ctx);
		
		String latestSeasonYear = null;
		
		
		if(whichData.equalsIgnoreCase("SEASON")) {
			jsonArr = ctx.read("$.league.standard[*].latest");
			System.out.println("players stats for season jsonArr "+jsonArr);
		} else if (whichData.equalsIgnoreCase("CAREER STATS")) {
			jsonArr = ctx.read("$..standard[*].careerSummary");
			System.out.println("players stats for career summary jsonArr "+jsonArr);
		}
		JSONParser jParser = new JSONParser();
		try {
			JSONArray jArray = (JSONArray) jParser.parse(jsonArr.toJSONString());
			for (Object datas : jArray) {
				com.jayway.restassured.path.json.JsonPath jPath = new com.jayway.restassured.path.json.JsonPath(datas.toString());
				
				if(whichData.equalsIgnoreCase("SEASON")) {
					latestSeasonYear = jPath.getString("seasonYear");
					System.out.println("latest season year"+ latestSeasonYear);
					System.out.println("year"+ year);

					if(year.contains(latestSeasonYear)) {
						whichData = whichData +" " + latestSeasonYear;
								
					} else {
						Reporter.addStepLog("Year Present in UI data does not match with the Json data for the season ");
					}

				} else if(whichData.equalsIgnoreCase("CAREER STATS")) {
					
				}
				
				//latestSeasonYear = jPath.getString("seasonYear");
				
				String jsonPPG = jPath.getString("ppg");		
				System.out.println("jsondata:" + jsonPPG.trim() + "uidata:" + ppg.trim() +" whichData:"+ whichData);
				compareTwoData(jsonPPG.trim(), ppg.trim(), "PPG", whichData);
				
				String jsonRPG = jPath.getString("rpg");		
				System.out.println("jsondata:" + jsonRPG.trim() + "uidata:" + rpg.trim() +" whichData:"+ whichData);
				compareTwoData(jsonRPG.trim(), rpg.trim(), "RPG", whichData);
				
				String jsonAPG = jPath.getString("apg");		
				System.out.println("jsondata:" + jsonAPG.trim() + "uidata:" + apg.trim() +" whichData:"+ whichData);
				compareTwoData(jsonAPG.trim(), apg.trim(), "APG", whichData);
				
				String jsonSPG = jPath.getString("spg");		
				System.out.println("jsondata:" + jsonSPG.trim() + "uidata:" + spg.trim() +" whichData:"+ whichData);
				compareTwoData(jsonSPG.trim(), spg.trim(), "SPG", whichData);
				
				String jsonBPG = jPath.getString("bpg");		
				System.out.println("jsondata:" + jsonBPG.trim() + "uidata:" + bpg.trim() +" whichData:"+ whichData);
				compareTwoData(jsonBPG.trim(), bpg.trim(), "BPG", whichData);
				
				String jsonFGPercent = jPath.getString("fgp");		
				System.out.println("jsondata:" + jsonFGPercent.trim() + "uidata:" + fgPercent.trim() +" whichData:"+ whichData);
				compareTwoData(jsonFGPercent.trim(), fgPercent.trim(), "FG%", whichData);
				
				String jsonthreePercent = jPath.getString("tpp");		
				System.out.println("jsondata:" + jsonthreePercent.trim() + "uidata:" + threePercent.trim() +" whichData:"+ whichData);
				compareTwoData(jsonthreePercent.trim(), threePercent.trim(), "3P%", whichData);
				
				String jsonftPercent = jPath.getString("ftp");		
				System.out.println("jsondata:" + jsonftPercent.trim() + "uidata:" + ftPercent.trim() +" whichData:"+ whichData);
				compareTwoData(jsonftPercent.trim(), ftPercent.trim(), "FT%", whichData);
			}
		} catch (ParseException e) {
			System.out.println("Parser exception occured ");
		}
		
	}

	public static void main(String [] args) throws IOException, ParseException {
	
		
	
	
	//String getPlayerProfileResponse = "https://data.nba.net/prod/v1/2019/players/1628389_profile.json";
	String getPlayerProfileResponse = JsonStandingsResponse.getJsonResponseString();
	net.minidev.json.JSONArray jsonArr = null;
	ReadContext ctx = JsonPath.parse(getPlayerProfileResponse);
	
    System.out.println("ctxplayerprofileresponse...."+ ctx);
	
		jsonArr = ctx.read("$..standard.stats.latest");
		System.out.println("players stats for season jsonArr "+jsonArr);
	
		jsonArr = ctx.read("$..standard[*].careerSummary");
		System.out.println("players stats for career summary jsonArr "+jsonArr);
	
	JSONParser jParser = new JSONParser();
	
		JSONArray jArray = (JSONArray) jParser.parse(jsonArr.toJSONString());
		for (Object datas : jArray) {
			com.jayway.restassured.path.json.JsonPath jPath = new com.jayway.restassured.path.json.JsonPath(datas.toString());
			
			
		String	latestSeasonYear = jPath.getString("seasonYear");
			System.out.println("year json"+ latestSeasonYear);
			String	jsonPPG = jPath.getString("ppg");
			System.out.println("json PPG"+ jsonPPG);
		}
}

	public static void compareTwoData(String jsonData, String uiData, String headline, String dataType ) throws IOException
	{
		if(uiData.equalsIgnoreCase("-") && jsonData.equalsIgnoreCase("-1")) {
			
			Reporter.addStepLog("Compared "+COLOR_OPEN+ headline + COLOR_CLOSE+ " ui data "+ COLOR_OPEN+ uiData +COLOR_CLOSE + " with Json Data "+COLOR_OPEN+jsonData +COLOR_CLOSE+ " for the " +COLOR_OPEN+ dataType+ COLOR_CLOSE  + " Both are same.");
		
		} else if((uiData!= null && jsonData != null) && uiData.equalsIgnoreCase(jsonData))
		{
			Reporter.addStepLog("Compared "+COLOR_OPEN+ headline + COLOR_CLOSE+ " ui data "+ COLOR_OPEN+ uiData +COLOR_CLOSE + " with Json Data "+COLOR_OPEN+jsonData +COLOR_CLOSE+ " for the " +COLOR_OPEN+ dataType+ COLOR_CLOSE  + " Both are same.");
		} else {
			Reporter.addStepLog("Compared "+COLOR_OPEN+ headline + COLOR_CLOSE+ " ui data "+ COLOR_OPEN+ uiData +COLOR_CLOSE + " with Json Data "+COLOR_OPEN+jsonData +COLOR_CLOSE+ " for the " +COLOR_OPEN+ dataType+ COLOR_CLOSE  + " Both are not same.");
		}
		
	}

}
