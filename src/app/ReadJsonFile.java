package app;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;

public class ReadJsonFile {

	private JsonObject personalShoppers;

	public HashMap<String, Shopper> getAllShoppers() {
		if (this.personalShoppers == null) {
			try {
				this.personalShoppers = readJson("shopperAvailability.json");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		HashMap<String, Shopper> shoppers = new HashMap<String, Shopper>();
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
				String day = availJson.getString("Day");

				// all the time
				JsonArray timeArray = availJson.getJsonArray("Time");
				for (int n = 0; n < timeArray.size(); n++) {
					JsonObject timeObj = timeArray.getJsonObject(n);

					int from = timeObj.getInt("From");
					int to = timeObj.getInt("To");

					addAllPeriod(day, from, to, avails);
				}
			}
			shopper.setAvailabilities(avails);
			shoppers.put(shopper.getName(), shopper);
		}
		return shoppers;
	}

	private void addAllPeriod(String day, int from, int to, ArrayList<Availability> avails) {
		for (int i = from; i <= to - 2; i++) {
			Availability avail = new Availability();
			avail.setDay(day);
			avail.setFrom(i);
			avail.setTo(i + 2);
			avails.add(avail);
		}
	}

	// TODO fix bug, availability is not comparable.
	public HashMap<Availability, ArrayList<Shopper>> getAllAvail(HashMap<String, Shopper> shoppers) {

		HashMap<Availability, ArrayList<Shopper>> allAvails = new HashMap<Availability, ArrayList<Shopper>>();
		ArrayList<Shopper> availShoppers = null;
		for (String name : shoppers.keySet()) {
			Shopper shopper = shoppers.get(name);
			ArrayList<Availability> avails = shopper.getAvailabilities();
			for (int i = 0; i < avails.size(); i++) {
				Availability avail = avails.get(i);
				if (!allAvails.containsKey(avail)) {
					availShoppers = new ArrayList<Shopper>();
					availShoppers.add(shopper);
					allAvails.put(avail, availShoppers);
				} else {
					availShoppers = allAvails.get(avail);
					availShoppers.add(shopper);
					allAvails.put(avail, availShoppers);
				}
			}
		}
		return allAvails;
	}

	// public ArrayList<Shopper> getShoppersByAvail(Availability avail) {
	// ArrayList<Shopper> shoppers = new ArrayList<Shopper>();
	// Shopper shopper = null;
	// return shoppers;
	// }

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
