package utilityPackage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class UtilityClass {

	// Read json data and config files
	public static JSONObject readDataFile(String dataType) throws IOException {

		String file = null;
		String localDirectory = System.getProperty("user.dir");
		JSONObject jsonobject = null;

		switch (dataType) {

		case "testdata":

			file = localDirectory + "\\src\\test\\resources\\data\\testData.json";
			break;

		case "configdata":
			file = localDirectory + "\\src\\test\\resources\\data\\configData.json";
		}

		try {
			JSONParser jsonParser = new JSONParser();
			jsonobject = (JSONObject) jsonParser.parse(new FileReader(file));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return jsonobject;
	}
}
