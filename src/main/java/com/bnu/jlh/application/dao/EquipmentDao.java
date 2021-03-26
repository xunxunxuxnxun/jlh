package com.bnu.jlh.application.dao;

import java.util.List;











import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import com.bnu.jlh.application.model.AirQualityStation;
import com.bnu.jlh.application.model.Equipment;
import com.bnu.jlh.application.model.HydrologicalWaterQuality;
import com.bnu.jlh.application.model.ReqEquipment;
import com.bnu.jlh.application.model.ResAlarm;
import com.bnu.jlh.application.model.SoilStation;
import com.bnu.jlh.application.model.WeatherStation;


@Mapper
public interface EquipmentDao {
	/**
	 * 查询所有设备信息
	 * @return
	 */
//	public List<Equipment> findAllEquipments();
	/**
	 * 查询最近的一条空气质量监控
	 * @return
	 */
	@Select("select * from airqualitystation where isdelete='0' and equipmentNO=#{equipmentNO}"
			+ " order by longDate DESC limit 0,1")
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
	AirQualityStation queryAirQuality(@Param("equipmentNO")String equipmentNO);
	
	/**
	 * 查询最近的一条水文水质
	 * @return
	 */
	@Select("select * from hydrologicalwaterquality where isdelete='0' and equipmentNO=#{equipmentNO} order by longDate DESC limit 0,1 ")
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
	HydrologicalWaterQuality queryWater(@Param("equipmentNO")String equipmentNO);
	/**
	 * 查询最近的一条土壤监控数据
	 * @return
	 */
	@Select("select * from soilstation where isdelete='0' and equipmentNO=#{equipmentNO} order by longDate DESC limit 0,1")
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
	SoilStation querySoil(@Param("equipmentNO")String equipmentNO);
	/**
	 * 查询最近的一条气象站数据
	 * @return
	 */
	@Select("select * from weatherstation where isdelete='0' and equipmentNO=#{equipmentNO} order by longDate DESC limit 0,1")
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
	WeatherStation queryweather(@Param("equipmentNO")String equipmentNO);
	/**
	 * 添加设备
	 * @param eq
	 * @return
	 */
	@Insert("INSERT INTO equipment(id,equipmentNO,siteNO,typeId,longitude,latitude,create_time,create_user,phone_number,message) "
			+ "VALUES(#{eq.id},#{eq.equipmentNumber},#{eq.siteId},#{eq.equipmentType},#{eq.longitude},#{eq.latitude},#{eq.createTime},"
			+ "#{eq.createUser},#{eq.createUser},#{eq.message})")
	int addEquipment(@Param("eq")ReqEquipment eq);
	/**
	 * 查询所有设备列表
	 * @return
	 */
	@Select("select eq.*,eqt.type_name from equipment eq LEFT JOIN equipment_type eqt "
			+ "ON eq.typeId=eqt.id ORDER BY eq.create_time DESC")
	@Results({
		@Result(property="id",column="id"),
		@Result(property="equipmentNO",column="equipmentNO"),
		@Result(property="siteNO",column="siteNO"),
		@Result(property="equipmentType",column="type_name"),
		@Result(property="typeId",column="typeId"),
		@Result(property="longitude",column="longitude"),
		@Result(property="latitude",column="latitude"),
		@Result(property="createTime",column="create_time"),
		@Result(property="createUser",column="create_user"),
		@Result(property="phoneNumber",column="phone_number"),
		@Result(property="message",column="message"),
		@Result(property="warning",column="warning"),
		@Result(property="alarm",column="alarm"),
		@Result(property="error",column="error")
	})
	List<Equipment> getEquipmentList();
	/**
	 * 查询土壤报警
	 */
	@Select("<script>"
			+ "SELECT obj.equipmentNO,obj.alarm_type,obj.alarm_property,obj.longDate,eqt.type_name "
			+ "FROM soilstation obj "
			+ "LEFT JOIN equipment eq ON obj.equipmentNO=eq.equipmentNO "
			+ "LEFT JOIN equipment_type eqt ON eq.typeId=eqt.id "
			+ "WHERE obj.equipmentNO=#{equipmentNO} "
			+ "<if test=\"alarmType!=null and alarmType!='' \"> and obj.alarm_flag=#{alarmType}</if>"
			+ "<if test=\"beginTime!=null and beginTime!='' \"> and obj.longDate &gt;= #{beginTime}</if>"
			+ "<if test=\"endTime!=null and endTime!='' \"> and obj.longDate &lt;= #{endTime}</if>"
			+ "ORDER BY obj.longDate DESC "
			+ "</script>")
	@Results({
		@Result(property="equipmentNumber",column="equipmentNO"),
		@Result(property="equipmentType",column="type_name"),
		@Result(property="property",column="alarm_property"),
		@Result(property="alarmTime",column="longDate"),
		@Result(property="content",column="alarm_type")
	})
	List<ResAlarm> querySoilAlarm(@Param("alarmType")String alarmType,
			@Param("equipmentNO")String equipmentNO,
			@Param("beginTime")String beginTime,
			@Param("endTime")String endTime);
	/**
	 * 查询空气质量报警
	 * @return
	 */
	@Select("<script>"
			+ "SELECT obj.equipmentNO,obj.alarm_type,obj.alarm_property,obj.longDate,eqt.type_name "
			+ "FROM airqualitystation obj "
			+ "LEFT JOIN equipment eq ON obj.equipmentNO=eq.equipmentNO "
			+ "LEFT JOIN equipment_type eqt ON eq.typeId=eqt.id "
			+ "WHERE obj.equipmentNO=#{equipmentNO} "
			+ "<if test=\"alarmType!=null and alarmType!='' \"> and obj.alarm_flag=#{alarmType}</if>"
			+ "<if test=\"beginTime!=null and beginTime!='' \"> and obj.longDate &gt;= #{beginTime}</if>"
			+ "<if test=\"endTime!=null and endTime!='' \"> and obj.longDate &lt;= #{endTime}</if>"
			+ "ORDER BY obj.longDate DESC "
			+ "</script>")
	@Results({
		@Result(property="equipmentNumber",column="equipmentNO"),
		@Result(property="equipmentType",column="type_name"),
		@Result(property="property",column="alarm_property"),
		@Result(property="alarmTime",column="longDate"),
		@Result(property="content",column="alarm_type")
	})
	List<ResAlarm> queryAirQualityAlarm(@Param("alarmType")String alarmType,
			@Param("equipmentNO")String equipmentNO,
			@Param("beginTime")String beginTime,
			@Param("endTime")String endTime);
	
	/**
	 * 查询水文水质报警
	 * @return
	 */
	@Select("<script>"
			+ "SELECT obj.equipmentNO,obj.alarm_type,obj.alarm_property,obj.longDate,eqt.type_name "
			+ "FROM hydrologicalwaterquality obj "
			+ "LEFT JOIN equipment eq ON obj.equipmentNO=eq.equipmentNO "
			+ "LEFT JOIN equipment_type eqt ON eq.typeId=eqt.id "
			+ "WHERE obj.equipmentNO=#{equipmentNO} "
			+ "<if test=\"alarmType!=null and alarmType!='' \"> and obj.alarm_flag=#{alarmType}</if>"
			+ "<if test=\"beginTime!=null and beginTime!='' \"> and obj.longDate &gt;= #{beginTime}</if>"
			+ "<if test=\"endTime!=null and endTime!='' \"> and obj.longDate &lt;= #{endTime}</if>"
			+ "ORDER BY obj.longDate DESC "
			+ "</script>")
	@Results({
		@Result(property="equipmentNumber",column="equipmentNO"),
		@Result(property="equipmentType",column="type_name"),
		@Result(property="property",column="alarm_property"),
		@Result(property="alarmTime",column="longDate"),
		@Result(property="content",column="alarm_type")
	})
	List<ResAlarm> queryWaterAlarm(@Param("alarmType")String alarmType,
			@Param("equipmentNO")String equipmentNO,
			@Param("beginTime")String beginTime,
			@Param("endTime")String endTime);
	
	/**
	 * 查询气象报警
	 * @return
	 */
	@Select("<script>"
			+ "SELECT obj.equipmentNO,obj.alarm_type,obj.alarm_property,obj.longDate,eqt.type_name "
			+ "FROM weatherstation obj "
			+ "LEFT JOIN equipment eq ON obj.equipmentNO=eq.equipmentNO "
			+ "LEFT JOIN equipment_type eqt ON eq.typeId=eqt.id "
			+ "WHERE obj.equipmentNO=#{equipmentNO} "
			+ "<if test=\"alarmType!=null and alarmType!='' \"> and obj.alarm_flag=#{alarmType}</if>"
			+ "<if test=\"beginTime!=null and beginTime!='' \"> and obj.longDate &gt;= #{beginTime}</if>"
			+ "<if test=\"endTime!=null and endTime!='' \"> and obj.longDate &lt;= #{endTime}</if>"
			+ "ORDER BY obj.longDate DESC "
			+ "</script>")
	@Results({
		@Result(property="equipmentNumber",column="equipmentNO"),
		@Result(property="equipmentType",column="type_name"),
		@Result(property="property",column="alarm_property"),
		@Result(property="alarmTime",column="longDate"),
		@Result(property="content",column="alarm_type")
	})
	List<ResAlarm> queryweatherAlarm(@Param("alarmType")String alarmType,
			@Param("equipmentNO")String equipmentNO,
			@Param("beginTime")String beginTime,
			@Param("endTime")String endTime);
}
