package com.logistica.jsons;

public class ElementJSON {

	 DurationJSON duration;
	 DistanceJSON distance;
	 String status;
	public DurationJSON getDuration() {
		return duration;
	}
	public void setDuration(DurationJSON duration) {
		this.duration = duration;
	}
	public DistanceJSON getDistance() {
		return distance;
	}
	public void setDistance(DistanceJSON distance) {
		this.distance = distance;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public ElementJSON() {
	}
	 
}
