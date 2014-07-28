package test;

import java.util.ArrayList;
import java.util.HashMap;

import app.Availability;
import app.ReadJsonFile;
import app.Shopper;

public class ReadJsonFileTest {

	public static void main(String[] args) {
		ReadJsonFileTest test = new ReadJsonFileTest();
		test.getAllAvailTest();
		// ArrayList<Availability> avails =
		// shoppers.get("Alex").getAvailabilities();
		//
		// for (int i = 0; i < avails.size(); i++) {
		// Availability avail = avails.get(i);
		// ArrayList<Shopper> availShoppers = allAvails.get(avail);
		// for (int j = 0; j < availShoppers.size(); j++) {
		// System.out.println(availShoppers.get(j).getName());
		// }
		// }

		// System.out.println(allAvails.size());

	}

	public void getAllAvailTest() {
		ReadJsonFile test = new ReadJsonFile();
		HashMap<String, Shopper> shoppers = test.getAllShoppers();
		HashMap<String, ArrayList<Shopper>> allAvails = test.getAllAvail(shoppers);

		for (String availStr : allAvails.keySet()) {
			System.out.print(availStr+" : ");
			ArrayList<Shopper> availShoppers = allAvails.get(availStr);
			for (int i = 0; i < availShoppers.size(); i++) {
				System.out.print(availShoppers.get(i).getName() + ", ");
			}
			System.out.println("");
		}

	}
}
