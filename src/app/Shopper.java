package app;

import java.util.ArrayList;

public class Shopper {
	private String name;
	private ArrayList<Availability> availabilities;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ArrayList<Availability> getAvailabilities() {
		return availabilities;
	}

	public void setAvailabilities(ArrayList<Availability> availabilities) {
		this.availabilities = availabilities;
	}
}
