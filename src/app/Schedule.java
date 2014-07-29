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

		if (schedule.containsKey(avail.toString()) || schedule.containsKey(avail1.toString()) || schedule.containsKey(avail2.toString())) {
			return true;
		} else
			return false;
	}

	public HashMap<String, ArrayList<Availability>> generateSchedule() {
		int time = 0;
		ReadJsonFile reader = new ReadJsonFile();
		HashMap<String, Shopper> shoppers = reader.getAllShoppers();
		HashMap<String, ArrayList<Shopper>> allAvails = reader.getAllAvail(shoppers);
		while (true) {
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

			// TODO check if every shopper satisfied the time constraint.(everyone has 8 hour)
			ArrayList<String> satisfiedShoppers = new ArrayList<String>(); // store shopper satisfied
			ArrayList<String> notSatisfiedShoppers = new ArrayList<String>(); // store shopper not satisfied
			for (String shopper : shoppers.keySet()) {
				if (!shopperSchedule.containsKey(shopper)) {
					notSatisfiedShoppers.add(shopper);
				} else {
					ArrayList<Availability> avails = shopperSchedule.get(shopper);
					if (avails.size() >= 4) {
						satisfiedShoppers.add(shopper);
					} else {
						notSatisfiedShoppers.add(shopper);
					}
				}
			}
			int n = 0;
			while (notSatisfiedShoppers.size() > 0 && time < 1000) {
				time++;
				String notSatisfiedShopper = notSatisfiedShoppers.get(n);
				if (shopperSchedule.get(notSatisfiedShopper).size() >= 4) {
					notSatisfiedShoppers.remove(n);
					n++;
					continue;
				}
				// get this notSatisfiedShopper's available time
				ArrayList<Availability> hisAvails = shoppers.get(notSatisfiedShopper).getAvailabilities();
				for (int i = 0; i < hisAvails.size(); i++) {
					String hisAvailStr = hisAvails.get(i).toString();
					for (int j = 0; j < shopperSchedule.get(notSatisfiedShopper).size(); j++) {
						// find his available time which has not been scheduled
						if (!hisAvailStr.equals(shopperSchedule.get(notSatisfiedShopper).get(j).toString())) {
							// random find a shopper who worked more than 8 hour
							Random r = new Random();
							while (true) {
								int m = r.nextInt(satisfiedShoppers.size());
								String satisfiedShopper = satisfiedShoppers.get(m);

								if (shopperSchedule.get(satisfiedShopper).size() > 4) {
									// try to remove one time slot for this satisfied shopper and schedule this available time for notSatisfiedShopper.
									for (int z = 0; z < shopperSchedule.get(satisfiedShopper).size(); z++) {
										Availability waitToRemove = shopperSchedule.get(satisfiedShopper).get(z);
										if (allAvails.get(waitToRemove.toString()).contains(notSatisfiedShopper)) {
											shopperSchedule.get(notSatisfiedShopper).add(waitToRemove);
											shopperSchedule.get(satisfiedShopper).remove(waitToRemove);
											break;
										}
									}
									break;
								}
							}
						}
					}
				}
			}
			if (notSatisfiedShoppers.size() == 0)
				return shopperSchedule;
		}
	}
}
