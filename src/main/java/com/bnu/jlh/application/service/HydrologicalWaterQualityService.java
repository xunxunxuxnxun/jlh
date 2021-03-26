package com.bnu.jlh.application.service;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.bnu.jlh.application.model.HydrologicalWaterQuality;
import com.bnu.jlh.application.model.ResponseWrapper;

/**
 * 水文水质
 * @author Administrator
 *
 */
public interface HydrologicalWaterQualityService {
	/**
	 * 实时数据（根据设备编号和日期查询数据）
	 * @param equipmentNO 设备编号
	 * @param data 短日期（yyyy-mm-dd）
	 * @return
	 */
	public List<HydrologicalWaterQuality> findDataByEquipmentNOAndData(String equipmentNO,String data);
	
	/**
	 * 根据设备编号查询设备历史信息
	 * @param equipmentNO
	 * @return
	 */
	List<HydrologicalWaterQuality> findHistoryOfEquipment(String equipmentNO);
	
	/**
	 * 查询当前时间往前一天每个小时的平均数据
	 * @param equipmentNO
	 *            设备编号
	 * @return
	 */
	public List<HydrologicalWaterQuality> findDataOneDay(String equipmentNO);
	
	/**
	 * 根据起止时间和设备编号查询水文水质历史数据
	 * @param beginTime
	 * @param endTime
	 * @param equipmentNO
	 * @return
	 */
	ResponseWrapper queryHistoryByDataAndNo(String beginTime,String endTime,String equipmentNO);
	
	/**
	 * 保存水文水质站数据
	 * @param weather
	 * @return
	 */
	ResponseWrapper saveWater(HydrologicalWaterQuality water);
	/**
	 * 查询离当前时间最近的一条数据，实时数据展示
	 * @param currentTime
	 * @return
	 */
	HydrologicalWaterQuality findCurrentData();
	
	void exportByDay(String beginTime,String endTime,String equipmentNO,HttpServletResponse response);
	
	void exportByHour(String beginTime,String endTime,String equipmentNO,HttpServletResponse response);
	
	void exportByMonth(String beginTime,String endTime,String equipmentNO,HttpServletResponse response);
}
