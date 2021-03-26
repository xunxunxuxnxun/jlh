package com.bnu.jlh.application.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import com.bnu.jlh.application.model.ExportHydrologicalWaterQuality;
import com.bnu.jlh.application.model.HydrologicalWaterQuality;
import com.bnu.jlh.application.model.ResWater;
@Mapper
public interface HydrologicalWaterQualityDao {
	public List<HydrologicalWaterQuality> findDateByEquipmentNOAndData(@Param("equipmentNO")String equipmentNO,@Param("shortDate")String data);
	
	public List<HydrologicalWaterQuality> findHistoryOfEquipment(@Param("equipmentNO")String equipmentNO);
	
	/**
	 * 实时数据
	 * @param startDate
	 * @param endDate
	 * @param equipmentNO
	 * @return
	 */
	@Select("SELECT DATE_FORMAT(hy.longDate,'%Y-%m-%d %H')date,"
			+ "AVG(hy.waterLevel) as waterLevel,"
			+ "AVG(hy.dissolvedOXY) as dissolvedOXY,"
			+ "AVG(hy.temperature) as temperature,"
			+ "AVG(hy.oxidationReductionPotential) as oxidationReductionPotential,"
			+ "AVG(hy.Conductivity) as conductivity,AVG(hy.PH) as ph,"
			+ "AVG(hy.transparency) as transparency,AVG(hy.flowRate) as flowRate,"
			+ "AVG(hy.NH4) as nh4"
			+ " FROM hydrologicalwaterquality hy"
			+ " WHERE hy.longDate &gt; #{startDate} AND hy.longDate &lt;= #{endDate} AND hy.equipmentNO=#{equipmentNO}"
			+ " GROUP BY date")
	@Results({
		@Result(property="waterLevel",column="waterLevel"),
		@Result(property="dissolvedOXY",column="dissolvedOXY"),
		@Result(property="temperature",column="temperature"),
		@Result(property="oxidationReductionPotential",column="oxidationReductionPotential"),
		@Result(property="conductivity",column="Conductivity"),
		@Result(property="ph",column="PH"),
		@Result(property="transparency",column="transparency"),
		@Result(property="flowRate",column="flowRate"),
		@Result(property="nh4",column="NH4"),
		@Result(property="longDate",column="date"),
	})
	public List<HydrologicalWaterQuality> findDataOneDay(
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
			+ "SELECT * FROM hydrologicalwaterquality WHERE isdelete='0'"
			+ "<if test=\"equipmentNO!=null and equipmentNO!='' \"> and equipmentNO=#{equipmentNO}</if>"
			+ "<if test=\"endTime!=null and endTime!='' \"> and longDate &gt;= #{endTime}</if>"
			+ "<if test=\"beginTime!=null and beginTime!='' \"> and longDate &lt;= #{beginTime}</if>"
			+ "</script>")
	@Results({
		@Result(property="id",column="id"),
		@Result(property="waterLevel",column="waterLevel"),
		@Result(property="dissolvedOXY",column="dissolvedOXY"),
		@Result(property="temperature",column="temperature"),
		@Result(property="oxidationReductionPotential",column="oxidationReductionPotential"),
		@Result(property="conductivity",column="Conductivity"),
		@Result(property="ph",column="PH"),
		@Result(property="transparency",column="transparency"),
		@Result(property="flowRate",column="flowRate"),
		@Result(property="nh4",column="NH4"),
		@Result(property="longDate",column="longDate"),
		@Result(property="shortDate",column="shortDate"),
		@Result(property="equipmentNO",column="equipmentNO")
	})
	public List<ResWater> queryHistory(
			@Param("beginTime")String beginTime,
			@Param("endTime")String endTime,
			@Param("equipmentNO")String equipmentNO);
	
	/**
	 * 保存水文水质站数据
	 * @param weather
	 * @return
	 */
	int saveWater(HydrologicalWaterQuality water);
	/**
	 * 查询离当前时间最近的一条数据，实时数据展示
	 * @param currentTime
	 * @return
	 */
	@Select("SELECT * FROM hydrologicalwaterquality WHERE isdelete='0' order by longDate DESC limit 0,1 ")
	@Results({
		@Result(property="id",column="id"),
		@Result(property="waterLevel",column="waterLevel"),
		@Result(property="dissolvedOXY",column="dissolvedOXY"),
		@Result(property="temperature",column="temperature"),
		@Result(property="oxidationReductionPotential",column="oxidationReductionPotential"),
		@Result(property="conductivity",column="Conductivity"),
		@Result(property="ph",column="PH"),
		@Result(property="transparency",column="transparency"),
		@Result(property="flowRate",column="flowRate"),
		@Result(property="nh4",column="NH4"),
		@Result(property="longDate",column="longDate"),
		@Result(property="shortDate",column="shortDate"),
		@Result(property="equipmentNO",column="equipmentNO")
	})
	HydrologicalWaterQuality findCurrentData();
	
	
	/**
	 * 查询导出日数据
	 * @param startDate
	 * @param endDate
	 * @param equipmentNO
	 * @return
	 */
	@Select("<script>"
			+ "SELECT DATE_FORMAT(hy.longDate,'%Y-%m-%d')date,"
			+ "ROUND(AVG(hy.waterLevel),2) as waterLevel,"
			+ "ROUND(AVG(hy.dissolvedOXY),2) as dissolvedOXY,"
			+ "ROUND(AVG(hy.temperature),2) as temperature,"
			+ "ROUND(AVG(hy.oxidationReductionPotential),2) as oxidationReductionPotential,"
			+ "ROUND(AVG(hy.Conductivity),2) as conductivity,ROUND(AVG(hy.PH),2) as ph,"
			+ "ROUND(AVG(hy.transparency),2) as transparency,ROUND(AVG(hy.flowRate),2) as flowRate,"
			+ "ROUND(AVG(hy.NH4),2) as nh4,equipmentNO"
			+ " FROM hydrologicalwaterquality hy"
			+ " WHERE isdelete='0' "
			+ "<if test=\"equipmentNO!=null and equipmentNO!='' \"> and equipmentNO=#{equipmentNO}</if>"
			+ "<if test=\"endTime!=null and endTime!='' \"> and longDate &lt;= #{endTime}</if>"
			+ "<if test=\"beginTime!=null and beginTime!='' \"> and longDate &gt;= #{beginTime}</if>"
			+ " GROUP BY date"
			+ "</script>")
	@Results({
		@Result(property="waterLevel",column="waterLevel"),
		@Result(property="dissolvedOXY",column="dissolvedOXY"),
		@Result(property="temperature",column="temperature"),
		@Result(property="oxidationReductionPotential",column="oxidationReductionPotential"),
		@Result(property="conductivity",column="Conductivity"),
		@Result(property="ph",column="PH"),
		@Result(property="transparency",column="transparency"),
		@Result(property="flowRate",column="flowRate"),
		@Result(property="nh4",column="NH4"),
		@Result(property="longDate",column="date"),
		@Result(property="equipmentNO",column="equipmentNO"),
	})
	public List<ExportHydrologicalWaterQuality> exportByDay(
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
			+ "SELECT DATE_FORMAT(hy.longDate,'%Y-%m-%d %H')date,"
			+ "ROUND(AVG(hy.waterLevel),2) as waterLevel,"
			+ "ROUND(AVG(hy.dissolvedOXY),2) as dissolvedOXY,"
			+ "ROUND(AVG(hy.temperature),2) as temperature,"
			+ "ROUND(AVG(hy.oxidationReductionPotential),2) as oxidationReductionPotential,"
			+ "ROUND(AVG(hy.Conductivity),2) as conductivity,ROUND(AVG(hy.PH),2) as ph,"
			+ "ROUND(AVG(hy.transparency),2) as transparency,ROUND(AVG(hy.flowRate),2) as flowRate,"
			+ "ROUND(AVG(hy.NH4),2) as nh4,equipmentNO"
			+ " FROM hydrologicalwaterquality hy"
			+ " WHERE isdelete='0' "
			+ "<if test=\"equipmentNO!=null and equipmentNO!='' \"> and equipmentNO=#{equipmentNO}</if>"
			+ "<if test=\"endTime!=null and endTime!='' \"> and longDate &lt;= #{endTime}</if>"
			+ "<if test=\"beginTime!=null and beginTime!='' \"> and longDate &gt;= #{beginTime}</if>"
			+ " GROUP BY date"
			+ "</script>")
	@Results({
		@Result(property="waterLevel",column="waterLevel"),
		@Result(property="dissolvedOXY",column="dissolvedOXY"),
		@Result(property="temperature",column="temperature"),
		@Result(property="oxidationReductionPotential",column="oxidationReductionPotential"),
		@Result(property="conductivity",column="Conductivity"),
		@Result(property="ph",column="PH"),
		@Result(property="transparency",column="transparency"),
		@Result(property="flowRate",column="flowRate"),
		@Result(property="nh4",column="NH4"),
		@Result(property="longDate",column="date"),
		@Result(property="equipmentNO",column="equipmentNO"),
	})
	public List<ExportHydrologicalWaterQuality> exportByHour(
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
			+ "SELECT DATE_FORMAT(hy.longDate,'%Y-%m')date,"
			+ "ROUND(AVG(hy.waterLevel),2) as waterLevel,"
			+ "ROUND(AVG(hy.dissolvedOXY),2) as dissolvedOXY,"
			+ "ROUND(AVG(hy.temperature),2) as temperature,"
			+ "ROUND(AVG(hy.oxidationReductionPotential),2) as oxidationReductionPotential,"
			+ "ROUND(AVG(hy.Conductivity),2) as conductivity,ROUND(AVG(hy.PH),2) as ph,"
			+ "ROUND(AVG(hy.transparency),2) as transparency,ROUND(AVG(hy.flowRate),2) as flowRate,"
			+ "ROUND(AVG(hy.NH4),2) as nh4,equipmentNO"
			+ " FROM hydrologicalwaterquality hy"
			+ " WHERE isdelete='0' "
			+ "<if test=\"equipmentNO!=null and equipmentNO!='' \"> and equipmentNO=#{equipmentNO}</if>"
			+ "<if test=\"endTime!=null and endTime!='' \"> and longDate &lt;= #{endTime}</if>"
			+ "<if test=\"beginTime!=null and beginTime!='' \"> and longDate &gt;= #{beginTime}</if>"
			+ " GROUP BY date"
			+ "</script>")
	@Results({
		@Result(property="waterLevel",column="waterLevel"),
		@Result(property="dissolvedOXY",column="dissolvedOXY"),
		@Result(property="temperature",column="temperature"),
		@Result(property="oxidationReductionPotential",column="oxidationReductionPotential"),
		@Result(property="conductivity",column="Conductivity"),
		@Result(property="ph",column="PH"),
		@Result(property="transparency",column="transparency"),
		@Result(property="flowRate",column="flowRate"),
		@Result(property="nh4",column="NH4"),
		@Result(property="longDate",column="date"),
		@Result(property="equipmentNO",column="equipmentNO"),
	})
	public List<ExportHydrologicalWaterQuality> exportByMonth(
			@Param("beginTime") String beginTime,
			@Param("endTime") String endTime,
			@Param("equipmentNO") String equipmentNO);
}
