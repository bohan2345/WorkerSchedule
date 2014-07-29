package app;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class Schedule {
	public HashMap<String, String> initSchedule(HashMap<String, Shopper> shoppers, HashMap<String, ArrayList<Shopper>> allAvails) {
		HashMap<String, String> schedule = new HashMap<String, String>();

		for (String avail : allAvails.keySet()) {
			if (!hasConfilt(avail, schedule)) {
				ArrayList<Shopper> availShoppers = allAvails.get(avail);
				Random rand = new Random();
				// random pick a shopper available at this time slot.
				int n = rand.nextInt(availShoppers.size());
				schedule.put(avail, availShoppers.get(n).getName());
			}
		}
		return schedule;
	}

	private boolean hasConfilt(String availStr, HashMap<String, String> schedule) {
		Availability avail = new Availability(availStr);
		Availability avail1 = new Availability();
		Availability avail2 = new Availability();

		avail1.setDay(avail.getDay());
		avail1.setFrom(avail.getFrom() + 1);
		avail1.setTo(avail.getTo() + 1);

		avail2.setDay(avail.getDay());
		avail2.setFrom(avail.getFrom() - 1);
		avail2.setTo(avail.getTo() - 1);

		if (schedule.containsKey(avail.toString()) && schedule.containsKey(avail1.toString()) && schedule.containsKey(avail2.toString())) {
			return true;
		} else
			return false;
	}

	public HashMap<String, ArrayList<Availability>> generateSchedule() {
		ReadJsonFile reader = new ReadJsonFile();
		HashMap<String, Shopper> shoppers = reader.getAllShoppers();
		HashMap<String, ArrayList<Shopper>> allAvails = reader.getAllAvail(shoppers);

		// HashMap initSchedule < time slot, shopper name >
		HashMap<String, String> initSchedule = initSchedule(shoppers, allAvails);
		// HashMap shopperSchedule < shopper name, all time slots >
		HashMap<String, ArrayList<Availability>> shopperSchedule = new HashMap<String, ArrayList<Availability>>();

		for (String availStr : initSchedule.keySet()) {
			String shopper = initSchedule.get(availStr);
			if (shopperSchedule.containsKey(shopper)) {
				ArrayList<Availability> avails = shopperSchedule.get(shopper);
				avails.add(new Availability(availStr));
			} else {
				ArrayList<Availability> avails = new ArrayList<Availability>();
				Availability avail = new Availability(availStr);
				avails.add(avail);
				shopperSchedule.put(shopper, avails);
			}
		}
		return shopperSchedule;
	}
}
