package com.bnu.jlh.application.model;

public class ResSoil {
	private String id;
	private String temperature;//温度
	private String humidity;//湿度
	private String conductivity;//电导率
	private String ph;
	private String longDate;//(yyyy-mm-dd:ss:mm:ss)
	private String shortDate; //短时间（yyyy-mm-dd）
	private String equipmentNO;//设备编号
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
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
	public String getConductivity() {
		return conductivity==null?"":conductivity;
	}
	public void setConductivity(String conductivity) {
		this.conductivity = conductivity;
	}
	public String getPh() {
		return ph==null?"":ph;
	}
	public void setPh(String ph) {
		this.ph = ph;
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
	
}
