package test;

import java.util.ArrayList;

import app.ReadJsonFile;
import app.Shopper;

public class ReadJsonFileTest {

	public static void main(String[] args) {

		ReadJsonFile test = new ReadJsonFile();
		ArrayList<Shopper> shoppers = test.getAllShoppers();
		System.out.println(shoppers.size());

	}
}
