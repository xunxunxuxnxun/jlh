package com.bnu.jlh.application.model;
/**
 * 返回报警信息
 * @author Administrator
 *
 */
public class ResAlarm {
	private String equipmentNumber;
	private String equipmentType;
	private String property;
	private String alarmTime;
	private String content;
	public String getEquipmentNumber() {
		return equipmentNumber==null?"":equipmentNumber;
	}
	public void setEquipmentNumber(String equipmentNumber) {
		this.equipmentNumber = equipmentNumber;
	}
	public String getEquipmentType() {
		return equipmentType==null?"":equipmentType;
	}
	public void setEquipmentType(String equipmentType) {
		this.equipmentType = equipmentType;
	}
	public String getProperty() {
		return property==null?"":property;
	}
	public void setProperty(String property) {
		this.property = property;
	}
	public String getAlarmTime() {
		return alarmTime==null?"":alarmTime;
	}
	public void setAlarmTime(String alarmTime) {
		this.alarmTime = alarmTime;
	}
	public String getContent() {
		return content==null?"":content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
}
