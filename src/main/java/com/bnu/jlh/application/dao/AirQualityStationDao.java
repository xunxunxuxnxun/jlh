package com.bnu.jlh.application.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import com.bnu.jlh.application.model.AirQualityStation;
import com.bnu.jlh.application.model.ExportAirQualityStation;
import com.bnu.jlh.application.model.ResAir;
import com.bnu.jlh.application.model.ResSoil;

@Mapper
public interface AirQualityStationDao {
	public List<AirQualityStation> findDateByEquipmentNOAndData(@Param("equipmentNO")String equipmentNO,@Param("shortDate")String data);
	public List<AirQualityStation> findHistoryOfEquipment(@Param("equipmentNO")String equipmentNO);
	
	/**
	 * 实时数据
	 * @param startDate
	 * @param endDate
	 * @param equipmentNO
	 * @return
	 */
	@Select("<script>"
			+ "SELECT DATE_FORMAT(A.longDate,'%Y-%m-%d %H')date,"
			+ "AVG(A.windSpeed) as windSpeed,"
			+ "AVG(A.windDirection) as windDirection,AVG(A.PM25) as pm25,"
			+ "AVG(A.PM10) as pm10,AVG(A.temperature) as temperature,"
			+ "AVG(A.humidity) as humidity,AVG(A.O3) as o3,AVG(A.CO) as co,"
			+ "AVG(A.SO2) as so2,AVG(A.NO2) as no2 FROM airqualitystation A "
			+ "WHERE A.longDate &gt; #{startDate} AND A.longDate &lt;= #{endDate} AND A.equipmentNO=#{equipmentNO}"
			+ " GROUP BY date"
			+ "</script>")
	@Results({
		@Result(property="id",column="id"),
		@Result(property="windSpeed",column="windSpeed"),
		@Result(property="windDirection",column="windDirection"),
		@Result(property="pm25",column="PM25"),
		@Result(property="pm10",column="PM10"),
		@Result(property="temperature",column="temperature"),
		@Result(property="humidity",column="humidity"),
		@Result(property="o3",column="O3"),
		@Result(property="co",column="CO"),
		@Result(property="so2",column="SO2"),
		@Result(property="no2",column="NO2"),
		@Result(property="longDate",column="date")
	})
	public List<AirQualityStation> findDataOneDay(
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
			+ "SELECT * FROM airqualitystation WHERE isdelete='0'"
			+ "<if test=\"equipmentNO!=null and equipmentNO!='' \"> and equipmentNO=#{equipmentNO}</if>"
			+ "<if test=\"endTime!=null and endTime!='' \"> and longDate &gt;= #{endTime}</if>"
			+ "<if test=\"beginTime!=null and beginTime!='' \"> and longDate &lt;= #{beginTime}</if>"
			+ "</script>")
	@Results({
		@Result(property="id",column="id"),
		@Result(property="windSpeed",column="windSpeed"),
		@Result(property="windDirection",column="windDirection"),
		@Result(property="pm25",column="PM25"),
		@Result(property="pm10",column="PM10"),
		@Result(property="temperature",column="temperature"),
		@Result(property="humidity",column="humidity"),
		@Result(property="o3",column="O3"),
		@Result(property="co",column="CO"),
		@Result(property="so2",column="SO2"),
		@Result(property="no2",column="NO2"),
		@Result(property="longDate",column="longDate"),
		@Result(property="shortDate",column="shortDate"),
		@Result(property="equipmentNO",column="equipmentNO")
	})
	public List<ResAir> queryHistory(
			@Param("beginTime")String beginTime,
			@Param("endTime")String endTime,
			@Param("equipmentNO")String equipmentNO);
	
	/**
	 * 保存空气质量监测站数据
	 * @param weather
	 * @return
	 */
	int saveAir(AirQualityStation air);
	/**
	 * 查询离当前时间最近的一条数据，实时数据展示
	 * @param currentTime
	 * @return
	 */
	
	@Select("SELECT * FROM airqualitystation WHERE isdelete='0' order by longDate DESC limit 0,1 ")
	@Results({
		@Result(property="id",column="id"),
		@Result(property="windSpeed",column="windSpeed"),
		@Result(property="windDirection",column="windDirection"),
		@Result(property="pm25",column="PM25"),
		@Result(property="pm10",column="PM10"),
		@Result(property="temperature",column="temperature"),
		@Result(property="humidity",column="humidity"),
		@Result(property="o3",column="O3"),
		@Result(property="co",column="CO"),
		@Result(property="so2",column="SO2"),
		@Result(property="no2",column="NO2"),
		@Result(property="longDate",column="longDate"),
		@Result(property="shortDate",column="shortDate"),
		@Result(property="equipmentNO",column="equipmentNO")
	})
	AirQualityStation findCurrentData();
	
	/**
	 * 查询导出日数据
	 * @param startDate
	 * @param endDate
	 * @param equipmentNO
	 * @return
	 */
	@Select("<script>"
			+ "SELECT DATE_FORMAT(longDate,'%Y-%m-%d')date,"
			+ "ROUND(AVG(windSpeed),2) as windSpeed,"
			+ "ROUND(AVG(windDirection),2) as windDirection,ROUND(AVG(PM25),2) as pm25,"
			+ "ROUND(AVG(PM10),2) as pm10,ROUND(AVG(temperature),2) as temperature,"
			+ "ROUND(AVG(humidity),2) as humidity,ROUND(AVG(O3),2) as o3,ROUND(AVG(CO),2) as co,"
			+ "ROUND(AVG(SO2),2) as so2,ROUND(AVG(NO2),2) as no2 FROM airqualitystation "
			+ "WHERE isdelete='0' "
			+ "<if test=\"equipmentNO!=null and equipmentNO!='' \"> and equipmentNO=#{equipmentNO}</if>"
			+ "<if test=\"endTime!=null and endTime!='' \"> and longDate &gt;= #{endTime}</if>"
			+ "<if test=\"beginTime!=null and beginTime!='' \"> and longDate &lt;= #{beginTime}</if>"
			+ " GROUP BY date"
			+ "</script>")
	@Results({
		@Result(property="id",column="id"),
		@Result(property="windSpeed",column="windSpeed"),
		@Result(property="windDirection",column="windDirection"),
		@Result(property="pm25",column="PM25"),
		@Result(property="pm10",column="PM10"),
		@Result(property="temperature",column="temperature"),
		@Result(property="humidity",column="humidity"),
		@Result(property="o3",column="O3"),
		@Result(property="co",column="CO"),
		@Result(property="so2",column="SO2"),
		@Result(property="no2",column="NO2"),
		@Result(property="longDate",column="date")
	})
	public List<ExportAirQualityStation> exportByDay(
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
			+ "SELECT DATE_FORMAT(longDate,'%Y-%m-%d %H')date,"
			+ "ROUND(AVG(windSpeed),2) as windSpeed,"
			+ "ROUND(AVG(windDirection),2) as windDirection,ROUND(AVG(PM25),2) as pm25,"
			+ "ROUND(AVG(PM10),2) as pm10,ROUND(AVG(temperature),2) as temperature,"
			+ "ROUND(AVG(humidity),2) as humidity,ROUND(AVG(O3),2) as o3,ROUND(AVG(CO),2) as co,"
			+ "ROUND(AVG(SO2),2) as so2,ROUND(AVG(NO2),2) as no2 FROM airqualitystation "
			+ "WHERE isdelete='0' "
			+ "<if test=\"equipmentNO!=null and equipmentNO!='' \"> and equipmentNO=#{equipmentNO}</if>"
			+ "<if test=\"endTime!=null and endTime!='' \"> and longDate &gt;= #{endTime}</if>"
			+ "<if test=\"beginTime!=null and beginTime!='' \"> and longDate &lt;= #{beginTime}</if>"
			+ " GROUP BY date"
			+ "</script>")
	@Results({
		@Result(property="id",column="id"),
		@Result(property="windSpeed",column="windSpeed"),
		@Result(property="windDirection",column="windDirection"),
		@Result(property="pm25",column="PM25"),
		@Result(property="pm10",column="PM10"),
		@Result(property="temperature",column="temperature"),
		@Result(property="humidity",column="humidity"),
		@Result(property="o3",column="O3"),
		@Result(property="co",column="CO"),
		@Result(property="so2",column="SO2"),
		@Result(property="no2",column="NO2"),
		@Result(property="longDate",column="date")
	})
	public List<ExportAirQualityStation> exportByHour(
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
			+ "SELECT DATE_FORMAT(longDate,'%Y-%m')date,"
			+ "ROUND(AVG(windSpeed),2) as windSpeed,"
			+ "ROUND(AVG(windDirection),2) as windDirection,ROUND(AVG(PM25),2) as pm25,"
			+ "ROUND(AVG(PM10),2) as pm10,ROUND(AVG(temperature),2) as temperature,"
			+ "ROUND(AVG(humidity),2) as humidity,ROUND(AVG(O3),2) as o3,ROUND(AVG(CO),2) as co,"
			+ "ROUND(AVG(SO2),2) as so2,ROUND(AVG(NO2),2) as no2 FROM airqualitystation "
			+ "WHERE isdelete='0' "
			+ "<if test=\"equipmentNO!=null and equipmentNO!='' \"> and equipmentNO=#{equipmentNO}</if>"
			+ "<if test=\"endTime!=null and endTime!='' \"> and longDate &gt;= #{endTime}</if>"
			+ "<if test=\"beginTime!=null and beginTime!='' \"> and longDate &lt;= #{beginTime}</if>"
			+ " GROUP BY date"
			+ "</script>")
	@Results({
		@Result(property="id",column="id"),
		@Result(property="windSpeed",column="windSpeed"),
		@Result(property="windDirection",column="windDirection"),
		@Result(property="pm25",column="PM25"),
		@Result(property="pm10",column="PM10"),
		@Result(property="temperature",column="temperature"),
		@Result(property="humidity",column="humidity"),
		@Result(property="o3",column="O3"),
		@Result(property="co",column="CO"),
		@Result(property="so2",column="SO2"),
		@Result(property="no2",column="NO2"),
		@Result(property="longDate",column="date")
	})
	public List<ExportAirQualityStation> exportByMonth(
			@Param("beginTime") String beginTime,
			@Param("endTime") String endTime,
			@Param("equipmentNO") String equipmentNO);
}
