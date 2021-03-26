package com.bnu.jlh.application.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import com.bnu.jlh.application.model.ResWeather;
import com.bnu.jlh.application.model.WeatherStation;
import com.github.pagehelper.Page;
@Mapper
public interface WeatherStationDao {
	/**
	 * 实时数据
	 * @param startDate
	 * @param endDate
	 * @param equipmentNO
	 * @return
	 */
	public List<WeatherStation> findAllWeatherStations();

	/**
	 * 实时数据
	 * @param startDate
	 * @param endDate
	 * @param equipmentNO
	 * @return
	 */
	@Select("SELECT DATE_FORMAT(we.longDate,'%Y-%m-%d %H')date,"
			+ "AVG(we.windSpeed) as windSpeed,AVG(we.temperature) as temperature,"
			+ "AVG(we.humidity) as humidity,AVG(we.noise) as noise,"
			+ "AVG(we.gasPressure) as gasPressure,AVG(we.beam) as beam,"
			+ "AVG(we.rainfall) as rainfall"
			+ " FROM weatherstation we"
			+ " WHERE we.longDate &gt; #{startDate} AND we.longDate &lt;= #{endDate} AND we.equipmentNO=#{equipmentNO}"
			+ " GROUP BY date")
	@Results({
		@Result(property="windSpeed",column="windSpeed"),
		@Result(property="humidity",column="humidity"),
		@Result(property="temperature",column="temperature"),
		@Result(property="windDirection",column="windDirection"),
		@Result(property="noise",column="noise"),
		@Result(property="gasPressure",column="gasPressure"),
		@Result(property="beam",column="beam"),
		@Result(property="rainfall",column="rainfall"),
		@Result(property="longDate",column="date"),
	})
	public List<WeatherStation> findDataOneDay(
			@Param("startDate") String startDate,
			@Param("endDate") String endDate,
			@Param("equipmentNO") String equipmentNO);
	
	/**
	 * 根据起止时间查询历史数据
	 * @param beginTime
	 * @param endTime
	 * @param equipmentNO
	 * @return
	 */
	@Select("<script>"
			+ "SELECT * FROM weatherstation WHERE isdelete='0'"
			+ "<if test=\"equipmentNO!=null and equipmentNO!='' \"> and equipmentNO=#{equipmentNO}</if>"
			+ "<if test=\"endTime!=null and endTime!='' \"> and longDate &gt;= #{endTime}</if>"
			+ "<if test=\"beginTime!=null and beginTime!='' \"> and longDate &lt;= #{beginTime}</if>"
			+ "</script>")
	@Results({
		@Result(property="id",column="id"),
		@Result(property="windSpeed",column="windSpeed"),
		@Result(property="humidity",column="humidity"),
		@Result(property="temperature",column="temperature"),
		@Result(property="windDirection",column="windDirection"),
		@Result(property="noise",column="noise"),
		@Result(property="gasPressure",column="gasPressure"),
		@Result(property="beam",column="beam"),
		@Result(property="rainfall",column="rainfall"),
		@Result(property="longDate",column="longDate"),
		@Result(property="shortDate",column="shortDate"),
		@Result(property="equipmentNO",column="equipmentNO")
	})
	public List<ResWeather> queryHistory(
			@Param("beginTime")String beginTime,
			@Param("endTime")String endTime,
			@Param("equipmentNO")String equipmentNO);
	
	/**
	 * 保存气象站数据
	 * @param weather
	 * @return
	 */
	int saveWeather(WeatherStation weather);
	
	/**
	 * 查询离当前时间最近的一条数据，实时数据展示
	 * @param currentTime
	 * @return
	 */
	@Select("SELECT * FROM weatherstation WHERE isdelete='0' order by longDate DESC limit 0,1 ")
	@Results({
		@Result(property="id",column="id"),
		@Result(property="windSpeed",column="windSpeed"),
		@Result(property="humidity",column="humidity"),
		@Result(property="temperature",column="temperature"),
		@Result(property="windDirection",column="windDirection"),
		@Result(property="noise",column="noise"),
		@Result(property="gasPressure",column="gasPressure"),
		@Result(property="beam",column="beam"),
		@Result(property="rainfall",column="rainfall"),
		@Result(property="longDate",column="longDate"),
		@Result(property="shortDate",column="shortDate"),
		@Result(property="equipmentNO",column="equipmentNO")
	})
	WeatherStation findCurrentData();
	
	/**
	 * 查询导出日数据
	 * @param startDate
	 * @param endDate
	 * @param equipmentNO
	 * @return
	 */
	@Select("<script>"
			+ "SELECT DATE_FORMAT(we.longDate,'%Y-%m-%d')date,"
			+ "ROUND(AVG(we.windSpeed),2) as windSpeed,ROUND(AVG(we.temperature),2) as temperature,"
			+ "ROUND(AVG(we.humidity),2) as humidity,ROUND(AVG(we.noise),2) as noise,"
			+ "ROUND(AVG(we.gasPressure),2) as gasPressure,ROUND(AVG(we.beam),2) as beam,"
			+ "ROUND(AVG(we.rainfall),2) as rainfall,windDirection,equipmentNO"
			+ " FROM weatherstation we "
			+ " WHERE isdelete='0' "
			+ "<if test=\"equipmentNO!=null and equipmentNO!='' \"> and equipmentNO=#{equipmentNO}</if>"
			+ "<if test=\"endTime!=null and endTime!='' \"> and longDate &lt;= #{endTime}</if>"
			+ "<if test=\"beginTime!=null and beginTime!='' \"> and longDate &gt;= #{beginTime}</if>"
			+ " GROUP BY date"
			+ "</script>")
	@Results({
		@Result(property="windSpeed",column="windSpeed"),
		@Result(property="humidity",column="humidity"),
		@Result(property="temperature",column="temperature"),
		@Result(property="windDirection",column="windDirection"),
		@Result(property="noise",column="noise"),
		@Result(property="gasPressure",column="gasPressure"),
		@Result(property="beam",column="beam"),
		@Result(property="rainfall",column="rainfall"),
		@Result(property="longDate",column="date"),
		@Result(property="equipmentNO",column="equipmentNO"),
	})
	public List<WeatherStation> exportByDay(
			@Param("beginTime") String beginTime,
			@Param("endTime") String endTime,
			@Param("equipmentNO") String equipmentNO);
	
	
	/**
	 * 查询导出小时数据
	 * @param startDate
	 * @param endDate
	 * @param equipmentNO
	 * @return
	 */
	@Select("<script>"
			+ "SELECT DATE_FORMAT(we.longDate,'%Y-%m-%d %H')date,"
			+ "ROUND(AVG(we.windSpeed),2) as windSpeed,ROUND(AVG(we.temperature),2) as temperature,"
			+ "ROUND(AVG(we.humidity),2) as humidity,ROUND(AVG(we.noise),2) as noise,"
			+ "ROUND(AVG(we.gasPressure),2) as gasPressure,ROUND(AVG(we.beam),2) as beam,"
			+ "ROUND(AVG(we.rainfall),2) as rainfall,windDirection,equipmentNO"
			+ " FROM weatherstation we "
			+ " WHERE isdelete='0' "
			+ "<if test=\"equipmentNO!=null and equipmentNO!='' \"> and equipmentNO=#{equipmentNO}</if>"
			+ "<if test=\"endTime!=null and endTime!='' \"> and longDate &lt;= #{endTime}</if>"
			+ "<if test=\"beginTime!=null and beginTime!='' \"> and longDate &gt;= #{beginTime}</if>"
			+ " GROUP BY date"
			+ "</script>")
	@Results({
		@Result(property="windSpeed",column="windSpeed"),
		@Result(property="humidity",column="humidity"),
		@Result(property="temperature",column="temperature"),
		@Result(property="windDirection",column="windDirection"),
		@Result(property="noise",column="noise"),
		@Result(property="gasPressure",column="gasPressure"),
		@Result(property="beam",column="beam"),
		@Result(property="rainfall",column="rainfall"),
		@Result(property="longDate",column="date"),
		@Result(property="equipmentNO",column="equipmentNO"),
	})
	public List<WeatherStation> exportByHour(
			@Param("beginTime") String beginTime,
			@Param("endTime") String endTime,
			@Param("equipmentNO") String equipmentNO);
	
	/**
	 * 查询导出月数据
	 * @param startDate
	 * @param endDate
	 * @param equipmentNO
	 * @return
	 */
	@Select("<script>"
			+ "SELECT DATE_FORMAT(we.longDate,'%Y-%m')date,"
			+ "ROUND(AVG(we.windSpeed),2) as windSpeed,ROUND(AVG(we.temperature),2) as temperature,"
			+ "ROUND(AVG(we.humidity),2) as humidity,ROUND(AVG(we.noise),2) as noise,"
			+ "ROUND(AVG(we.gasPressure),2) as gasPressure,ROUND(AVG(we.beam),2) as beam,"
			+ "ROUND(AVG(we.rainfall),2) as rainfall,windDirection,equipmentNO"
			+ " FROM weatherstation we "
			+ " WHERE isdelete='0' "
			+ "<if test=\"equipmentNO!=null and equipmentNO!='' \"> and equipmentNO=#{equipmentNO}</if>"
			+ "<if test=\"endTime!=null and endTime!='' \"> and longDate &lt;= #{endTimebeginTime}</if>"
			+ "<if test=\"beginTime!=null and beginTime!='' \"> and longDate &gt;= #{beginTime}</if>"
			+ " GROUP BY date"
			+ "</script>")
	@Results({
		@Result(property="windSpeed",column="windSpeed"),
		@Result(property="humidity",column="humidity"),
		@Result(property="temperature",column="temperature"),
		@Result(property="windDirection",column="windDirection"),
		@Result(property="noise",column="noise"),
		@Result(property="gasPressure",column="gasPressure"),
		@Result(property="beam",column="beam"),
		@Result(property="rainfall",column="rainfall"),
		@Result(property="longDate",column="date"),
		@Result(property="equipmentNO",column="equipmentNO"),
	})
	public List<WeatherStation> exportByMonth(
			@Param("beginTime") String beginTime,
			@Param("endTime") String endTime,
			@Param("equipmentNO") String equipmentNO);
}
