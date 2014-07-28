package app;

import java.util.ArrayList;

public class Availability {
	private String day;
	private ArrayList<Time> availabilityTimes;

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}

	public ArrayList<Time> getAvailabilityTimes() {
		return availabilityTimes;
	}

	public void setAvailabilityTimes(ArrayList<Time> availabilityTimes) {
		this.availabilityTimes = availabilityTimes;
	}
}
