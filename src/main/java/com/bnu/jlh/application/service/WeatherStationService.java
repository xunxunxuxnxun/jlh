package com.bnu.jlh.application.service;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.bnu.jlh.application.model.ResponseWrapper;
import com.bnu.jlh.application.model.WeatherStation;

public interface WeatherStationService {
	/**
	 * 查询气象站所有信息
	 * 
	 * @return 返回气象站所有信息的对象集
	 */
	List<WeatherStation> findAllWeatherStations();

	/**
	 * 查询当前时间往前一天每个小时的平均数据
	 * 
	 * @param startDate
	 *            起始时间
	 * @param endDate
	 *            结束时间
	 * @param equipmentNO
	 *            设备编号
	 * @return
	 */
	List<WeatherStation> findDataOneDay(String equipmentNO);
	
	/**
	 * 根据起止时间和设备编号查询气象历史数据
	 * @param beginTime
	 * @param endTime
	 * @param equipmentNO
	 * @return
	 */
	ResponseWrapper queryHistoryByDataAndNo(String beginTime,String endTime,String equipmentNO);
	
	/**
	 * 保存气象站数据
	 * @param weather
	 * @return
	 */
	ResponseWrapper saveWeather(WeatherStation weather);
	/**
	 * 查询离当前时间最近的一条数据，实时数据展示
	 * @param currentTime
	 * @return
	 */
	WeatherStation findCurrentData();
	
	void exportByDay(String beginTime,String endTime,String equipmentNO,HttpServletResponse response);
	
	void exportByHour(String beginTime,String endTime,String equipmentNO,HttpServletResponse response);
	
	void exportByMonth(String beginTime,String endTime,String equipmentNO,HttpServletResponse response);
}
