package app;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;

public class ReadJsonFile {

	public ArrayList<Shopper> getAllShoppers() {
		JsonObject personalShoppers = null;
		try {
			personalShoppers = readJson("shopperAvailability.json");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ArrayList<Shopper> shoppers = new ArrayList<>();
		// all the shoppers
		JsonArray shoppersArray = personalShoppers.getJsonArray("PersonalShoppers");

		for (int i = 0; i < shoppersArray.size(); i++) {
			// the i Shopper
			JsonObject shopperJson = shoppersArray.getJsonObject(i);

			Shopper shopper = new Shopper();
			shopper.setName(shopperJson.getString("Name"));

			// the i Shopper's all availabilities
			JsonArray availArray = shopperJson.getJsonArray("Availability");

			ArrayList<Availability> avails = new ArrayList<>();
			for (int j = 0; j < availArray.size(); j++) {
				// the j availability of i shopper
				JsonObject availJson = availArray.getJsonObject(j);
				Availability avail = new Availability();
				avail.setDay(availJson.getString("Day"));

				// all the time
				JsonArray timeArray = availJson.getJsonArray("Time");
				ArrayList<Time> times = new ArrayList<>();
				for (int n = 0; n < timeArray.size(); n++) {
					JsonObject timeObj = timeArray.getJsonObject(n);

					int from = timeObj.getInt("From");
					int to = timeObj.getInt("To");

					addAllPeriod(from, to, times);
				}
				avail.setAvailabilityTimes(times);
				avails.add(avail);
			}
			shopper.setAvailabilities(avails);
			shoppers.add(shopper);
		}
		return shoppers;
	}

	private void addAllPeriod(int from, int to, ArrayList<Time> times) {
		for (int i = from; i <= to - 2; i++) {
			Time time = new Time();
			time.setFrom(i);
			time.setTo(i + 2);
			times.add(time);
		}
	}

	public JsonObject readJson(String path) throws IOException {
		InputStream stream = null;
		JsonObject jsonObj = null;
		stream = new FileInputStream(path);
		JsonReader jsonReader = Json.createReader(stream);
		jsonObj = jsonReader.readObject();

		stream.close();
		return jsonObj;
	}
}
