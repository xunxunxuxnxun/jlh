package com.bnu.jlh.application.service.Impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;









import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bnu.jlh.application.dao.EquipmentDao;
import com.bnu.jlh.application.model.AirQualityStation;
import com.bnu.jlh.application.model.Equipment;
import com.bnu.jlh.application.model.HydrologicalWaterQuality;
import com.bnu.jlh.application.model.ReqEquipment;
import com.bnu.jlh.application.model.ResAlarm;
import com.bnu.jlh.application.model.SoilStation;
import com.bnu.jlh.application.model.WeatherStation;
import com.bnu.jlh.application.service.EquipmentService;

@Service
public class EquipmentServiceImpl implements EquipmentService {
	@Autowired
	private EquipmentDao equipmentMapper;
	/**
	 * 查询所有设备信息
	 */
	@Override
	public List<Equipment> findAllEquipments() {
		
		return equipmentMapper.getEquipmentList();
	}
	/**
	 * 查询所有坐标点
	 */
	@Override
	public List<Equipment> queryAllPoint() {
		List<Equipment> list = equipmentMapper.getEquipmentList();
		for (Equipment equipment : list) {
			//将数字转成汉字
			if(equipment.getErrorflag()==1){
				equipment.setErrorFlagName("预警");
			}else if(equipment.getErrorflag()==2){
				equipment.setErrorFlagName("警报");
			}else if(equipment.getErrorflag()==3){
				equipment.setErrorFlagName("异常");
			}else{
				equipment.setErrorFlagName("正常");
			}
			//气象站
			if("1".equals(equipment.getTypeId())){
				WeatherStation bean = equipmentMapper.queryweather(equipment.getEquipmentNO());
				equipment.setData(bean);
			}else if("2".equals(equipment.getTypeId())){//空气质量监测站
				AirQualityStation bean = equipmentMapper.queryAirQuality(equipment.getEquipmentNO());
				equipment.setData(bean);
			}else if("3".equals(equipment.getTypeId())){//水文水质
				HydrologicalWaterQuality bean = equipmentMapper.queryWater(equipment.getEquipmentNO());
				equipment.setData(bean);
			}else if("4".equals(equipment.getTypeId())){//土壤
				SoilStation bean = equipmentMapper.querySoil(equipment.getEquipmentNO());
				equipment.setData(bean);
			}
		}
		return list;
	}
	/**
	 * 添加设备
	 */
	@Override
	public int addEquipment(ReqEquipment eq) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String time = df.format(new Date());
		eq.setCreateTime(time);
		String uuid = UUID.randomUUID().toString();
        uuid = uuid.replace("-","").toUpperCase();
		eq.setId(uuid);
		int i = equipmentMapper.addEquipment(eq);
		return i;
	}
	/**
	 * 查询设备列表
	 */
	@Override
	public List<Equipment> getEquipmentList() {
		List<Equipment> list = equipmentMapper.getEquipmentList();
		
		return list;
	}
	/**
	 * 查询设备报警信息
	 */
	@Override
	public List<ResAlarm> getEquipmentAlarm(String equipmentNumber, String equipmentType,
			String beginTime, String endTime, String alarmType) {
		List<ResAlarm> list = new ArrayList<>();
		if("1".equals(equipmentType)){//气象
			list = equipmentMapper.queryweatherAlarm(alarmType, equipmentNumber, beginTime, endTime);
		}else if("2".equals(equipmentType)){//空气
			list = equipmentMapper.queryAirQualityAlarm(alarmType, equipmentNumber, beginTime, endTime);
		}else if("3".equals(equipmentType)){//水文
			list = equipmentMapper.queryWaterAlarm(alarmType, equipmentNumber, beginTime, endTime);
		}else if("4".equals(equipmentType)){//土壤
			list = equipmentMapper.querySoilAlarm(alarmType, equipmentNumber, beginTime, endTime);
		}
		return list;
	}

}
