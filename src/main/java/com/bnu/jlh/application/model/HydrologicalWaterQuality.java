package com.bnu.jlh.application.model;

/**
 * 水文水质监测站
 * @author Administrator
 *
 */
public class HydrologicalWaterQuality {
	private String id;
	private double waterLevel;//水位
	private double dissolvedOXY;//溶解氧
	private double temperature;
	private double oxidationReductionPotential;//氧化还原电位
	private double conductivity;//电导率
	private double ph;
	private double transparency;//透明度
	private double flowRate;//流速
	private double nh4;
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
	public double getWaterLevel() {
		return waterLevel;
	}
	public void setWaterLevel(double waterLevel) {
		this.waterLevel = waterLevel;
	}
	public double getDissolvedOXY() {
		return dissolvedOXY;
	}
	public void setDissolvedOXY(double dissolvedOXY) {
		this.dissolvedOXY = dissolvedOXY;
	}
	public double getTemperature() {
		return temperature;
	}
	public void setTemperature(double temperature) {
		this.temperature = temperature;
	}
	public double getOxidationReductionPotential() {
		return oxidationReductionPotential;
	}
	public void setOxidationReductionPotential(double oxidationReductionPotential) {
		this.oxidationReductionPotential = oxidationReductionPotential;
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
	public double getTransparency() {
		return transparency;
	}
	public void setTransparency(double transparency) {
		this.transparency = transparency;
	}
	public double getFlowRate() {
		return flowRate;
	}
	public void setFlowRate(double flowRate) {
		this.flowRate = flowRate;
	}
	public double getNh4() {
		return nh4;
	}
	public void setNh4(double nh4) {
		this.nh4 = nh4;
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
		return "HydrologicalWaterQuality [conductivity=" + conductivity
				+ ", dissolvedOXY=" + dissolvedOXY + ", equipmentNO="
				+ equipmentNO + ", flowRate=" + flowRate + ", id=" + id
				+ ", longDate=" + longDate + ", nh4=" + nh4
				+ ", oxidationReductionPotential="
				+ oxidationReductionPotential + ", ph=" + ph + ", shortDate="
				+ shortDate + ", temperature=" + temperature
				+ ", transparency=" + transparency + ", waterLevel="
				+ waterLevel + "]";
	}

}
