package com.bnu.jlh.application.service;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.bnu.jlh.application.model.ResponseWrapper;
import com.bnu.jlh.application.model.SoilStation;

/**
 * 土壤
 * @author Administrator
 *
 */
public interface SoilStationService {
	/**
	 * 实时数据（根据设备编号和日期查询数据）
	 * @param equipmentNO 设备编号
	 * @param data 短日期（yyyy-mm-dd）
	 * @return
	 */
	public List<SoilStation> findDataByEquipmentNOAndData(String equipmentNO,String data);
	
	/**
	 * 根据设备编号查询设备历史信息
	 * @param equipmentNO
	 * @return
	 */
	List<SoilStation> findHistoryOfEquipment(String equipmentNO);
	
	/**
	 * 查询当前时间往前一天每个小时的平均数据
	 * @param equipmentNO
	 *            设备编号
	 * @return
	 */
	public List<SoilStation> findDataOneDay(String equipmentNO);
	
	/**
	 * 根据起止时间和设备编号查询土壤历史数据
	 * @param beginTime
	 * @param endTime
	 * @param equipmentNO
	 * @return
	 */
	ResponseWrapper queryHistoryByDataAndNo(String beginTime,String endTime,String equipmentNO);
	
	/**
	 * 保存土壤监测站数据
	 * @param weather
	 * @return
	 */
	ResponseWrapper saveSoil(SoilStation soil);
	/**
	 * 查询离当前时间最近的一条数据，实时数据展示
	 * @param currentTime
	 * @return
	 */
	SoilStation findCurrentData();
	
	
	void exportByDay(String beginTime,String endTime,String equipmentNO,HttpServletResponse response);
	
	void exportByHour(String beginTime,String endTime,String equipmentNO,HttpServletResponse response);
	
	void exportByMonth(String beginTime,String endTime,String equipmentNO,HttpServletResponse response);
}
