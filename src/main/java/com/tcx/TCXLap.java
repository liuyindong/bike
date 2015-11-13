package com.tcx;

public class TCXLap {
	private String startTime;
	private String totalTimeSeconds;
	private String totalTimeMinutes;
	private String distanceMeters;
	private String intensity;
	private String triggerMethod;
	
	private String removeDecimals(String input){
		if (input.indexOf(".") == -1){
			return input;
		}
		int nexwtIdx = input.indexOf(".") + 3;
		if (nexwtIdx > input.length()){
			return input;
		}
		return input.substring(0, nexwtIdx);
	}
	
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getTotalTimeSeconds() {
		return totalTimeSeconds;
	}
	public void setTotalTimeSeconds(String totalTimeSeconds) {
		this.totalTimeSeconds = removeDecimals(totalTimeSeconds);
		this.totalTimeMinutes = removeDecimals(""+(Double.parseDouble(this.totalTimeSeconds) / 60));
	}
	public String getDistanceMeters() {
		return distanceMeters;
	}
	public void setDistanceMeters(String distanceMeters) {
		this.distanceMeters = removeDecimals(distanceMeters);
	}
	public String getIntensity() {
		return intensity;
	}
	public void setIntensity(String intensity) {
		this.intensity = intensity;
	}
	public String getTriggerMethod() {
		return triggerMethod;
	}
	public void setTriggerMethod(String triggerMethod) {
		this.triggerMethod = triggerMethod;
	}
	public String getTotalTimeMinutes() {
		return totalTimeMinutes;
	}
	@Override
	public String toString() {
		return "startTime=" + startTime + ", totalTimeSeconds="
				+ totalTimeSeconds + ", totalTimeMinutes=" + totalTimeMinutes
				+ ", distanceMeters=" + distanceMeters + ", intensity="
				+ intensity + ", triggerMethod=" + triggerMethod;
	}

}
