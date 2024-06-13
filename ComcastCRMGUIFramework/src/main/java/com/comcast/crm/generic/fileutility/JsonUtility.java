package com.comcast.crm.generic.fileutility;

import java.io.FileReader;


import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JsonUtility {
	public String getDataFromJsonFile(String key) throws Throwable, ParseException {
		FileReader fileR = new FileReader("./configAppData/appCommonData.json");
		JSONParser parser = new JSONParser();
		
		Object Obj = parser.parse(fileR);

		// step2:Convert java Object to JSon Object using downcasting

		JSONObject map = (JSONObject) Obj;
	String data = (String)map.get(key);
    return data;

	}
}
