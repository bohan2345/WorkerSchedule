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

	public Availability() {
	}

	public Availability(String availStr) {
		availStr = availStr.trim();
		this.day = availStr.substring(0, availStr.indexOf(" from"));
		String fromStr = availStr.substring(availStr.indexOf("from ") + 5, availStr.indexOf(" to"));
		this.from = Integer.valueOf(fromStr);
		this.to = Integer.valueOf(availStr.substring(availStr.indexOf("to ") + 3));
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

	public boolean isConfilctWith(Availability avail) {
		if (avail != null) {
			boolean sameDay = this.day.equals(avail.getDay());
			boolean timeConfilct = !((this.from - avail.getFrom() >= 2) || (this.from - avail.getFrom() <= -2));

			if (sameDay && timeConfilct)
				return true;
		}
		return false;
	}

	public String toString() {
		String str = this.day + " ";
		str += "from " + Integer.toString(this.from);
		str += " to " + Integer.toString(this.to);
		return str;
	}
}
