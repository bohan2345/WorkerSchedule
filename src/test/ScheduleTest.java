package test;

import java.util.ArrayList;
import java.util.HashMap;

import app.Availability;
import app.ReadJsonFile;
import app.Schedule;
import app.Shopper;

public class ScheduleTest {
	public void initScheduleTest() {

		ReadJsonFile reader = new ReadJsonFile();
		HashMap<String, Shopper> shoppers = reader.getAllShoppers();
		HashMap<String, ArrayList<Shopper>> allAvails = reader.getAllAvail(shoppers);
		Schedule sche = new Schedule();
		HashMap<String, String> schedule = sche.initSchedule(shoppers, allAvails);

		for (String avail : schedule.keySet()) {
			System.out.print(avail + " : ");
			System.out.println(schedule.get(avail));
		}
	}

	public void generateScheduleTest() {
		Schedule s = new Schedule();
		HashMap<String, ArrayList<Availability>> lalala = s.generateSchedule();
		System.out.println(lalala.size());
		for (String shopper : lalala.keySet()) {
			ArrayList<Availability> as = lalala.get(shopper);
			System.out.println(shopper + " : " + as.size());
			for (int i = 0; i < as.size(); i++) {
				System.out.println("    " + as.get(i).toString());
			}
		}
	}

	public static void main(String[] args) {
		ScheduleTest test = new ScheduleTest();
		// test.availbilityConstructorTest();
		// test.initScheduleTest();
		test.generateScheduleTest();
	}

	public void availbilityConstructorTest() {
		String str = "Tuesday from 10 to 12";
		Availability a = new Availability(str);

		System.out.println(a.getDay() + " : " + a.getFrom() + " -> " + a.getTo());
	}
}
