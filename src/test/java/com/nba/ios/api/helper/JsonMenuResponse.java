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

public class JsonMenuResponse {

	public static String url = "https://secure.nba.com/mobile/apps/configs/dev/menu/menu_nbatv_ios.json";
	
	public static void main(String[] args) throws IOException {
		String response = getMenusJsonResponse();
		ArrayList<String> menus = getMenuNames(response);
		System.out.println("menus "+menus);
	}
	
	public static ArrayList<String> getMenuNames(String responseString) {
		ArrayList<String> menuList = new ArrayList<String>();
		net.minidev.json.JSONArray jsonArr = null;
		ReadContext ctx = JsonPath.parse(responseString);
		
		String menuNames = "$.navigationBar[*]";
		
		jsonArr = ctx.read(menuNames);
		//System.out.println("jsonArr "+jsonArr);
		
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
			String menuName = jPath.getString("title");
			menuList.add(menuName);
			//System.out.println(menuName + " JSON data is "+menuName);
		}
		
		return menuList;
	}
	
	/**
	 * returns the Menu Json Response as a string
	 * @param url
	 * @return
	 * @throws IOException
	 */
	public static String getMenusJsonResponse() throws IOException {
		Response res = APIHelper.getRequest(url, ContentType.JSON);
		String responseString = null;
		responseString =res.body().asString();
		//System.out.println(responseString);
		return responseString;			
	}
	
	
}
