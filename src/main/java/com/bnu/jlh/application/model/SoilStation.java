package com.bnu.jlh.application.model;

/**
 * 土壤监测站
 * @author Administrator
 *
 */
public class SoilStation {
	private String id;
	private double temperature;//温度
	private double humidity;//湿度
	private double conductivity;//电导率
	private double ph;
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

	public double getConductivity() {
		return conductivity;
	}

	public void setConductivity(double conductivity) {
		this.conductivity = conductivity;
	}

	public double getPh() {
		return ph;
	}

	public void setPh(double ph) {
		this.ph = ph;
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

	@Override
	public String toString() {
		return "SoilStation [conductivity=" + conductivity + ", equipmentNO="
				+ equipmentNO + ", humidity=" + humidity + ", id=" + id
				+ ", longDate=" + longDate + ", ph=" + ph + ", shortDate="
				+ shortDate + ", temperature=" + temperature + "]";
	}
	
	
}
