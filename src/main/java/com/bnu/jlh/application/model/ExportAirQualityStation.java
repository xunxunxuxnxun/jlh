package com.bnu.jlh.application.model;

public class ExportAirQualityStation {
	private String id;
	private String windSpeed;//风速
	private String windDirection;//风向
	private String pm25;
	private String pm10;
	private String temperature;//温度
	private String humidity;//湿度
	private String o3;
	private String co;
	private String so2;
	private String no2;
	private String longDate;//(yyyy-mm-dd:ss:mm:ss)
	private String shortDate; //短时间（yyyy-mm-dd）
	private String equipmentNO;//设备编号
	private String alarmFlag;
	private String alarmType;
	private String alarmProperty;
	public String getAlarmFlag() {
		return alarmFlag==null?"":alarmFlag;
	}
	public void setAlarmFlag(String alarmFlag) {
		this.alarmFlag = alarmFlag;
	}
	public String getAlarmType() {
		return alarmType==null?"":alarmType;
	}
	public void setAlarmType(String alarmType) {
		this.alarmType = alarmType;
	}
	public String getAlarmProperty() {
		return alarmProperty==null?"":alarmProperty;
	}
	public void setAlarmProperty(String alarmProperty) {
		this.alarmProperty = alarmProperty;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getWindSpeed() {
		return windSpeed==null?"":windSpeed;
	}
	public void setWindSpeed(String windSpeed) {
		this.windSpeed = windSpeed;
	}
	public String getWindDirection() {
		return windDirection==null?"":windDirection;
	}
	public void setWindDirection(String windDirection) {
		this.windDirection = windDirection;
	}
	public String getPm25() {
		return pm25==null?"":pm25;
	}
	public void setPm25(String pm25) {
		this.pm25 = pm25;
	}
	public String getPm10() {
		return pm10==null?"":pm10;
	}
	public void setPm10(String pm10) {
		this.pm10 = pm10;
	}
	public String getTemperature() {
		return temperature==null?"":temperature;
	}
	public void setTemperature(String temperature) {
		this.temperature = temperature;
	}
	public String getHumidity() {
		return humidity==null?"":humidity;
	}
	public void setHumidity(String humidity) {
		this.humidity = humidity;
	}
	public String getO3() {
		return o3==null?"":o3;
	}
	public void setO3(String o3) {
		this.o3 = o3;
	}
	public String getCo() {
		return co==null?"":co;
	}
	public void setCo(String co) {
		this.co = co;
	}
	public String getSo2() {
		return so2==null?"":so2;
	}
	public void setSo2(String so2) {
		this.so2 = so2;
	}
	public String getNo2() {
		return no2==null?"":no2;
	}
	public void setNo2(String no2) {
		this.no2 = no2;
	}
	public String getLongDate() {
		return longDate==null?"":longDate;
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
		return equipmentNO==null?"":equipmentNO;
	}
	public void setEquipmentNO(String equipmentNO) {
		this.equipmentNO = equipmentNO;
	}
	@Override
	public String toString() {
		return "AirQualityStation [co=" + co + ", equipmentNO=" + equipmentNO
				+ ", humidity=" + humidity + ", id=" + id + ", longDate="
				+ longDate + ", no2=" + no2 + ", o3=" + o3 + ", pm10=" + pm10
				+ ", pm25=" + pm25 + ", shortDate=" + shortDate + ", so2="
				+ so2 + ", temperature=" + temperature + ", windDirection="
				+ windDirection + ", windSpeed=" + windSpeed + "]";
	}
	

}
