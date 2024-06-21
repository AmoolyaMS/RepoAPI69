package com.vtigercrm.generic.fileutility;

import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JsonUtility {



	public String getDatafromJSONFile(String key) throws IOException, ParseException {
		JSONParser parser=new JSONParser();
		Object obj = parser.parse(new FileReader("./congifdata/commondatajson.json"));
		JSONObject jsonobj=(JSONObject) obj;
		String data=(String) jsonobj.get(key);
		return data;

	}

}
