package com.bnu.jlh.application.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import com.bnu.jlh.application.model.ResSoil;
import com.bnu.jlh.application.model.SoilStation;
@Mapper
public interface SoilStationDao {
	public List<SoilStation> findDateByEquipmentNOAndData(@Param("equipmentNO")String equipmentNO,@Param("shortDate")String data);
	
	public List<SoilStation> findHistoryOfEquipment(@Param("equipmentNO")String equipmentNO);
	
	/**
	 * 实时数据
	 * @param startDate
	 * @param endDate
	 * @param equipmentNO
	 * @return
	 */
	@Select("SELECT DATE_FORMAT(so.longDate,'%Y-%m-%d %H')date,"
			+ "AVG(so.temperature) as temperature,AVG(so.humidity) as humidity,"
			+ "AVG(so.conductivity) as conductivity,AVG(so.ph) as ph "
			+ "FROM soilstation so "
			+ "WHERE so.longDate &gt; #{startDate} AND so.longDate &lt;= #{endDate} AND so.equipmentNO=#{equipmentNO} "
			+ "GROUP BY date")
	@Results({
		@Result(property="id",column="id"),
		@Result(property="conductivity",column="conductivity"),
		@Result(property="humidity",column="humidity"),
		@Result(property="temperature",column="temperature"),
		@Result(property="ph",column="ph"),
		@Result(property="longDate",column="date"),
	})
	public List<SoilStation> findDataOneDay(
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
			+ "SELECT * FROM soilstation WHERE isdelete='0'"
			+ "<if test=\"equipmentNO!=null and equipmentNO!='' \"> and equipmentNO=#{equipmentNO}</if>"
			+ "<if test=\"endTime!=null and endTime!='' \"> and longDate &gt;= #{endTime}</if>"
			+ "<if test=\"beginTime!=null and beginTime!='' \"> and longDate &lt;= #{beginTime}</if>"
			+ "</script>")
	@Results({
		@Result(property="id",column="id"),
		@Result(property="conductivity",column="conductivity"),
		@Result(property="humidity",column="humidity"),
		@Result(property="temperature",column="temperature"),
		@Result(property="ph",column="ph"),
		@Result(property="longDate",column="longDate"),
		@Result(property="shortDate",column="shortDate"),
		@Result(property="equipmentNO",column="equipmentNO")
	})
	public List<ResSoil> queryHistory(
			@Param("beginTime")String beginTime,
			@Param("endTime")String endTime,
			@Param("equipmentNO")String equipmentNO);
	
	/**
	 * 保存土壤监测站数据
	 * @param weather
	 * @return
	 */
	int saveSoil(SoilStation soil);
	
	/**
	 * 查询离当前时间最近的一条数据，实时数据展示
	 * @param currentTime
	 * @return
	 */
	@Select("SELECT * FROM soilstation WHERE isdelete='0' order by longDate DESC limit 0,1 ")
	@Results({
		@Result(property="id",column="id"),
		@Result(property="conductivity",column="conductivity"),
		@Result(property="humidity",column="humidity"),
		@Result(property="temperature",column="temperature"),
		@Result(property="ph",column="ph"),
		@Result(property="longDate",column="longDate"),
		@Result(property="shortDate",column="shortDate"),
		@Result(property="equipmentNO",column="equipmentNO")
	})
	SoilStation findCurrentData();
	
	
	/**
	 * 查询导出日数据
	 * @param startDate
	 * @param endDate
	 * @param equipmentNO
	 * @return
	 */
	
	@Select("<script>"
			+ "SELECT DATE_FORMAT(so.longDate,'%Y-%m-%d')date,"
			+ "ROUND(AVG(so.temperature),2) as temperature,ROUND(AVG(so.humidity),2) as humidity,"
			+ "ROUND(AVG(so.conductivity),2) as conductivity,ROUND(AVG(so.ph),2) as ph,equipmentNO "
			+ " FROM soilstation so "
			+ " WHERE isdelete='0' "
			+ "<if test=\"equipmentNO!=null and equipmentNO!='' \"> and equipmentNO=#{equipmentNO}</if>"
			+ "<if test=\"endTime!=null and endTime!='' \"> and longDate &lt;= #{endTime}</if>"
			+ "<if test=\"beginTime!=null and beginTime!='' \"> and longDate &gt;= #{beginTime}</if>"
			+ "GROUP BY date"
			+ "</script>")
	@Results({
		@Result(property="id",column="id"),
		@Result(property="conductivity",column="conductivity"),
		@Result(property="humidity",column="humidity"),
		@Result(property="temperature",column="temperature"),
		@Result(property="ph",column="ph"),
		@Result(property="longDate",column="date"),
		@Result(property="equipmentNO",column="equipmentNO"),
	})
	public List<ResSoil> exportByDay(
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
			+ "SELECT DATE_FORMAT(so.longDate,'%Y-%m-%d %H')date,"
			+ "ROUND(AVG(so.temperature),2) as temperature,ROUND(AVG(so.humidity),2) as humidity,"
			+ "ROUND(AVG(so.conductivity),2) as conductivity,ROUND(AVG(so.ph),2) as ph,equipmentNO "
			+ " FROM soilstation so "
			+ " WHERE isdelete='0' "
			+ "<if test=\"equipmentNO!=null and equipmentNO!='' \"> and equipmentNO=#{equipmentNO}</if>"
			+ "<if test=\"endTime!=null and endTime!='' \"> and longDate &lt;= #{endTime}</if>"
			+ "<if test=\"beginTime!=null and beginTime!='' \"> and longDate &gt;= #{beginTime}</if>"
			+ "GROUP BY date"
			+ "</script>")
	@Results({
		@Result(property="id",column="id"),
		@Result(property="conductivity",column="conductivity"),
		@Result(property="humidity",column="humidity"),
		@Result(property="temperature",column="temperature"),
		@Result(property="ph",column="ph"),
		@Result(property="longDate",column="date"),
		@Result(property="equipmentNO",column="equipmentNO"),
	})
	public List<ResSoil> exportByHour(
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
			+ "SELECT DATE_FORMAT(so.longDate,'%Y-%m')date,"
			+ "ROUND(AVG(so.temperature),2) as temperature,ROUND(AVG(so.humidity),2) as humidity,"
			+ "ROUND(AVG(so.conductivity),2) as conductivity,ROUND(AVG(so.ph),2) as ph,equipmentNO "
			+ " FROM soilstation so "
			+ " WHERE isdelete='0' "
			+ "<if test=\"equipmentNO!=null and equipmentNO!='' \"> and equipmentNO=#{equipmentNO}</if>"
			+ "<if test=\"endTime!=null and endTime!='' \"> and longDate &lt;= #{endTime}</if>"
			+ "<if test=\"beginTime!=null and beginTime!='' \"> and longDate &gt;= #{beginTime}</if>"
			+ "GROUP BY date"
			+ "</script>")
	@Results({
		@Result(property="id",column="id"),
		@Result(property="conductivity",column="conductivity"),
		@Result(property="humidity",column="humidity"),
		@Result(property="temperature",column="temperature"),
		@Result(property="ph",column="ph"),
		@Result(property="longDate",column="date"),
		@Result(property="equipmentNO",column="equipmentNO"),
	})
	public List<ResSoil> exportByMonth(
			@Param("beginTime") String beginTime,
			@Param("endTime") String endTime,
			@Param("equipmentNO") String equipmentNO);
}
