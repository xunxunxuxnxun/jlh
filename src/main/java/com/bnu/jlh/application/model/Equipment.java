package com.bnu.jlh.application.model;

/**
 * �豸ʵ����
 * @author Administrator
 *
 */
public class Equipment {
	private String id;
	private String equipmentNO;//�豸���
	private String siteNO;//վ����
	private String equipmentType;//�豸����
	private String typeId;
	private int onlineOrNot;//�Ƿ����ߣ�1���ߡ�0���ߣ�
	private int errorflag;//�쳣��־��0������1Ԥ����2������3�쳣��
	private String errorFlagName;//异常标识名称
	private int warning;//Ԥ����
	private int alarm; //������
	private int error; //�쳣��
	private String longitude;//经度
	private String latitude;//纬度
	private Object data;//设备对应的数据
	private String createTime;
	private String createUser;
	private String phoneNumber;
	private String message;
	
	public String getCreateTime() {
		return createTime==null?"":createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getCreateUser() {
		return createUser==null?"":createUser;
	}
	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}
	public String getPhoneNumber() {
		return phoneNumber==null?"":phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getMessage() {
		return message==null?"":message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getId() {
		return id==null?"":id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getEquipmentNO() {
		return equipmentNO==null?"":equipmentNO;
	}
	public void setEquipmentNO(String equipmentNO) {
		this.equipmentNO = equipmentNO;
	}
	public String getSiteNO() {
		return siteNO==null?"":siteNO;
	}
	public void setSiteNO(String siteNO) {
		this.siteNO = siteNO;
	}
	public String getEquipmentType() {
		return equipmentType==null?"":equipmentType;
	}
	public void setEquipmentType(String equipmentType) {
		this.equipmentType = equipmentType;
	}
	public int getOnlineOrNot() {
		return onlineOrNot;
	}
	public void setOnlineOrNot(int onlineOrNot) {
		this.onlineOrNot = onlineOrNot;
	}
	public int getErrorflag() {
		return errorflag;
	}
	public void setErrorflag(int errorflag) {
		this.errorflag = errorflag;
	}
	public int getWarning() {
		return warning;
	}
	public void setWarning(int warning) {
		this.warning = warning;
	}
	public int getAlarm() {
		return alarm;
	}
	public void setAlarm(int alarm) {
		this.alarm = alarm;
	}
	public int getError() {
		return error;
	}
	public void setError(int error) {
		this.error = error;
	}
	public String getLongitude() {
		return longitude==null?"":longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	public String getLatitude() {
		return latitude==null?"":latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	public String getTypeId() {
		return typeId==null?"":typeId;
	}
	public void setTypeId(String typeId) {
		this.typeId = typeId;
	}
	public String getErrorFlagName() {
		return errorFlagName==null?"":errorFlagName;
	}
	public void setErrorFlagName(String errorFlagName) {
		this.errorFlagName = errorFlagName;
	}
	
}
