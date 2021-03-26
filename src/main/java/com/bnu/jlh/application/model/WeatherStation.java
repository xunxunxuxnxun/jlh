package com.bnu.jlh.application.model;

/**
 * 气象站
 * @author Administrator
 *
 */
public class WeatherStation {
	private String id;
	private String windSpeed;//风速
	private String windDirection;//风向
	private String temperature; //温度
	private String humidity;//湿度
	private String noise;//噪声
	private String gasPressure;//气压
	private String beam;//光照
	private String rainfall;//雨量
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
	public String getHumidity() {
		return humidity==null?"":humidity;
	}
	public void setHumidity(String humidity) {
		this.humidity = humidity;
	}
	public String getNoise() {
		return noise==null?"":noise;
	}
	public void setNoise(String noise) {
		this.noise = noise;
	}
	public String getGasPressure() {
		return gasPressure==null?"":gasPressure;
	}
	public void setGasPressure(String gasPressure) {
		this.gasPressure = gasPressure;
	}
	public String getBeam() {
		return beam==null?"":beam;
	}
	public void setBeam(String beam) {
		this.beam = beam;
	}
	public String getRainfall() {
		return rainfall==null?"":rainfall;
	}
	public void setRainfall(String rainfall) {
		this.rainfall = rainfall;
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
	public void setTemperature(String temperature) {
		this.temperature = temperature;
	}
	public String getTemperature() {
		return temperature==null?"":temperature;
	}
	public String getWindDirection() {
		return windDirection==null?"":windDirection;
	}
	public void setWindDirection(String windDirection) {
		this.windDirection = windDirection;
	}
	@Override
	public String toString() {
		return "WeatherStation [beam=" + beam + ", equipmentNO=" + equipmentNO
				+ ", gasPressure=" + gasPressure + ", humidity=" + humidity
				+ ", id=" + id + ", longDate=" + longDate + ", noise=" + noise
				+ ", rainfall=" + rainfall + ", shortDate=" + shortDate
				+ ", temperature=" + temperature + ", windDirection="
				+ windDirection + ", windSpeed=" + windSpeed + "]";
	}

	
	
}
