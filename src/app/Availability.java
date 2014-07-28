package app;


public class Availability {
	private String day;
	private int from;
	private int to;

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}

	public int getFrom() {
		return from;
	}

	public void setFrom(int from) {
		this.from = from;
	}

	public int getTo() {
		return to;
	}

	public void setTo(int to) {
		this.to = to;
	}

	public boolean isSameTimeWith(Availability avail) {
		if (avail == null)
			return false;

		if (this.day.equals(avail.getDay())) {
			if (this.from == avail.from && this.to == avail.to)
				return true;
		}
		return false;
	}
}
