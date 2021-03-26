package com.bnu.jlh.application.model;

public class ExportHydrologicalWaterQuality {

	private String id;
	private String waterLevel;//水位
	private String dissolvedOXY;//溶解氧
	private String temperature;
	private String oxidationReductionPotential;//氧化还原电位
	private String conductivity;//电导率
	private String ph;
	private String transparency;//透明度
	private String flowRate;//流速
	private String nh4;
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
	public String getWaterLevel() {
		return waterLevel==null?"":waterLevel;
	}
	public void setWaterLevel(String waterLevel) {
		this.waterLevel = waterLevel;
	}
	public String getDissolvedOXY() {
		return dissolvedOXY==null?"":dissolvedOXY;
	}
	public void setDissolvedOXY(String dissolvedOXY) {
		this.dissolvedOXY = dissolvedOXY;
	}
	public String getTemperature() {
		return temperature==null?"":temperature;
	}
	public void setTemperature(String temperature) {
		this.temperature = temperature;
	}
	public String getOxidationReductionPotential() {
		return oxidationReductionPotential==null?"":oxidationReductionPotential;
	}
	public void setOxidationReductionPotential(String oxidationReductionPotential) {
		this.oxidationReductionPotential = oxidationReductionPotential;
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
	public String getTransparency() {
		return transparency==null?"":transparency;
	}
	public void setTransparency(String transparency) {
		this.transparency = transparency;
	}
	public String getFlowRate() {
		return flowRate==null?"":flowRate;
	}
	public void setFlowRate(String flowRate) {
		this.flowRate = flowRate;
	}
	public String getNh4() {
		return nh4==null?"":nh4;
	}
	public void setNh4(String nh4) {
		this.nh4 = nh4;
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
