package com.bnu.jlh.application.service;

import java.util.List;

import com.bnu.jlh.application.model.Equipment;
import com.bnu.jlh.application.model.ReqEquipment;
import com.bnu.jlh.application.model.ResAlarm;


public interface EquipmentService {
	/*
	 * 查询所有设备信息
	 */
	public List<Equipment> findAllEquipments();
	/**
	 * 查询所有坐标点
	 */
	List<Equipment> queryAllPoint();
	/**
	 * 添加设备
	 * @return
	 */
	int addEquipment(ReqEquipment eq);
	/**
	 * 查询设备列表
	 */
	List<Equipment> getEquipmentList();
	/**
	 * 查询设备报警信息
	 */
	List<ResAlarm> getEquipmentAlarm(String equipmentNumber,String equipmentType,String beginTime,String endTime,String alarmType);
}
