package com.bnu.jlh.application.model;

public class ResAir {
	private String id;
	private double windSpeed;//风速
	private String windDirection;//风向
	private double pm25;
	private double pm10;
	private double temperature;//温度
	private double humidity;//湿度
	private double o3;
	private double co;
	private double so2;
	private double no2;
	private String longDate;//(yyyy-mm-dd:ss:mm:ss)
	private String shortDate; //短时间（yyyy-mm-dd）
	private String equipmentNO;//设备编号
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public double getWindSpeed() {
		return windSpeed;
	}
	public void setWindSpeed(double windSpeed) {
		this.windSpeed = windSpeed;
	}
	public String getWindDirection() {
		return windDirection;
	}
	public void setWindDirection(String windDirection) {
		this.windDirection = windDirection;
	}
	public double getPm25() {
		return pm25;
	}
	public void setPm25(double pm25) {
		this.pm25 = pm25;
	}
	public double getPm10() {
		return pm10;
	}
	public void setPm10(double pm10) {
		this.pm10 = pm10;
	}
	public double getTemperature() {
		return temperature;
	}
	public void setTemperature(double temperature) {
		this.temperature = temperature;
	}
	public double getHumidity() {
		return humidity;
	}
	public void setHumidity(double humidity) {
		this.humidity = humidity;
	}
	public double getO3() {
		return o3;
	}
	public void setO3(double o3) {
		this.o3 = o3;
	}
	public double getCo() {
		return co;
	}
	public void setCo(double co) {
		this.co = co;
	}
	public double getSo2() {
		return so2;
	}
	public void setSo2(double so2) {
		this.so2 = so2;
	}
	public double getNo2() {
		return no2;
	}
	public void setNo2(double no2) {
		this.no2 = no2;
	}
	public String getLongDate() {
		return longDate;
	}
	public void setLongDate(String longDate) {
		this.longDate = longDate;
	}
	public String getShortDate() {
		return shortDate;
	}
	public void setShortDate(String shortDate) {
		this.shortDate = shortDate;
	}
	public String getEquipmentNO() {
		return equipmentNO;
	}
	public void setEquipmentNO(String equipmentNO) {
		this.equipmentNO = equipmentNO;
	}
	
}
