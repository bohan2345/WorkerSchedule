package test;

import java.util.ArrayList;

import app.ReadJsonFile;
import app.Shopper;

public class ReadJsonFileTest {

	public static void main(String[] args) {

		ReadJsonFile test = new ReadJsonFile();
		ArrayList<Shopper> shoppers = test.getAllShoppers();
		System.out.println(shoppers.size());
		// Persons shoppers = new Persons();
		// test.readJson("shopperAvailability.json", shoppers);

		// ArrayList<Person> shopperList = shoppers.getShoppers();

		// for(Person shopper : shopperList){
		// System.out.print(shopper.getName()+ " has");
		// ArrayList<String> availableTime = shopper.getAvailablePeriod();
		// String time = "";
		// for(String eachPeriod : availableTime){
		// time+=eachPeriod+"; ";
		// }
		//
		// System.out.println(" "+ time);
		//
		// System.out.println("The name is "+ shopper.getName());
		// System.out.println("The total remain hours: " +
		// shopper.getRawSlot());
		// HashMap<String, Vector<Range>> allPossibleSlotsForOnePerson =
		// shopper.getAllAvailableSlots();
		// for(Map.Entry<String, Vector<Range>> eachTimeSlot :
		// allPossibleSlotsForOnePerson.entrySet()){
		// String daySign = eachTimeSlot.getKey();
		// System.out.println("The day: "+ daySign);
		// for(Range r :eachTimeSlot.getValue()){
		// System.out.println("From " + r.getStart() +" To "+ r.getEnd());
		// }
		// System.out.println();
		// }
		//
		// }

		// ConvertDayToPerson con = new ConvertDayToPerson();
		// con.convert(shoppers);
		// con.displayDayRangeToPerson();

	}
}
