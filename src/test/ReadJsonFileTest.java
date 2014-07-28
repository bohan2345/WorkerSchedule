package test;

import java.util.ArrayList;
import java.util.HashMap;

import app.Availability;
import app.ReadJsonFile;
import app.Shopper;

public class ReadJsonFileTest {

	public static void main(String[] args) {

		ReadJsonFile test = new ReadJsonFile();
		HashMap<String, Shopper> shoppers = test.getAllShoppers();
		HashMap<Availability, ArrayList<Shopper>> allAvails = test.getAllAvail(shoppers);
		ArrayList<Availability> avails = shoppers.get("Alex").getAvailabilities();

		for (int i = 0; i < avails.size(); i++) {
			Availability avail = avails.get(i);
			ArrayList<Shopper> availShoppers = allAvails.get(avail);
			for (int j = 0; j < availShoppers.size(); j++) {
				System.out.println(availShoppers.get(j).getName());
			}
		}

		// System.out.println(allAvails.size());

	}
}
