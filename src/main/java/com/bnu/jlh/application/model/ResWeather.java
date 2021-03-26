package com.bnu.jlh.application.model;

public class ResWeather {
	private String id;
	private double windSpeed;//风速
	private String windDirection;//风向
	private double temperature; //温度
	private double humidity;//湿度
	private double noise;//噪声
	private double gasPressure;//气压
	private double beam;//光照
	private double rainfall;//雨量
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
	public double getNoise() {
		return noise;
	}
	public void setNoise(double noise) {
		this.noise = noise;
	}
	public double getGasPressure() {
		return gasPressure;
	}
	public void setGasPressure(double gasPressure) {
		this.gasPressure = gasPressure;
	}
	public double getBeam() {
		return beam;
	}
	public void setBeam(double beam) {
		this.beam = beam;
	}
	public double getRainfall() {
		return rainfall;
	}
	public void setRainfall(double rainfall) {
		this.rainfall = rainfall;
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
