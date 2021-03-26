package com.bnu.jlh.application.service.Impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bnu.jlh.application.common.ExcelRow;
import com.bnu.jlh.application.common.ExcelSheet;
import com.bnu.jlh.application.common.ExcelUtils;
import com.bnu.jlh.application.dao.HydrologicalWaterQualityDao;
import com.bnu.jlh.application.model.ExportHydrologicalWaterQuality;
import com.bnu.jlh.application.model.HydrologicalWaterQuality;
import com.bnu.jlh.application.model.ResSoil;
import com.bnu.jlh.application.model.ResWater;
import com.bnu.jlh.application.model.ResponseWrapper;
import com.bnu.jlh.application.service.HydrologicalWaterQualityService;
import com.github.pagehelper.PageInfo;

@Service
public class HydrologicalWaterQualityServiceImpl implements HydrologicalWaterQualityService{
	@Autowired
	private HydrologicalWaterQualityDao hWaterQualityMapper;
	@Override
	public List<HydrologicalWaterQuality> findDataByEquipmentNOAndData(
			String equipmentNO, String data) {
		return hWaterQualityMapper.findDateByEquipmentNOAndData(equipmentNO, data);
	}
	@Override
	public List<HydrologicalWaterQuality> findHistoryOfEquipment(
			String equipmentNO) {
		return hWaterQualityMapper.findHistoryOfEquipment(equipmentNO);
	}
	
	
	@Override
	public List<HydrologicalWaterQuality> findDataOneDay(String equipmentNO) {
		if (equipmentNO.isEmpty()) {
			return null;
		}
		Date dd = new Date();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
		String endDate = df.format(dd);//当前时间
		String startDate = df.format(dd.getTime()-1*24*60*60*1000);//前一天的时间
		
		List<HydrologicalWaterQuality> list = hWaterQualityMapper.findDataOneDay(startDate, endDate, equipmentNO);
		
		return list;
	}
	
	/**
	 * 根据起止时间和设备编号查询水文水质历史数据
	 */
	@Override
	public ResponseWrapper queryHistoryByDataAndNo(String beginTime,String endTime,String equipmentNO) {
		ResponseWrapper res = new ResponseWrapper();
		List<ResWater> resWater = hWaterQualityMapper.queryHistory(beginTime,endTime,equipmentNO);
		if(resWater!=null&&resWater.size()>0){
			PageInfo<ResWater> pageInfo = new PageInfo<ResWater>(resWater,5);
			res.setReturnObj(pageInfo);
			res.setReturnCode("200");
			res.setReturnMsg("查询历史数据成功");
		}else{
			res.setReturnCode("400");
			res.setReturnMsg("未查询到历史数据");
		}
		return res;
	}
	/**
	 * 保存水文水质站数据
	 */
	@Override
	public ResponseWrapper saveWater(HydrologicalWaterQuality water) {
		ResponseWrapper res = new ResponseWrapper();
		int flag = hWaterQualityMapper.saveWater(water);
		if(flag>0){
			res.setReturnCode("200");
			res.setReturnMsg("保存数据成功");
		}else{
			res.setReturnCode("400");
			res.setReturnMsg("保存数据失败");
		}
		return res;
	}
	@Override
	public HydrologicalWaterQuality findCurrentData() {
		return hWaterQualityMapper.findCurrentData();
	}
	@Override
	public void exportByDay(String beginTime, String endTime,
			String equipmentNO, HttpServletResponse response) {
		List<ExportHydrologicalWaterQuality> list = hWaterQualityMapper.exportByDay(beginTime, endTime, equipmentNO);
		ExcelSheet excelSheet = new ExcelSheet();
		excelSheet.setName("水质监测日数据" + new SimpleDateFormat("yyMMddHHmmss").format(new Date()));
		List<ExcelSheet> excelSheets = new ArrayList<>();
        excelSheets.add(excelSheet);
        ExcelRow excelRow = new ExcelRow();
        excelRow.add("序号");
        excelRow.add("水位(cm)");
        excelRow.add("水质溶解氧(mg/L)");
        excelRow.add("水温(℃)");
        excelRow.add("氧化还原电位(mV)");
        excelRow.add("水质电导率(uS/cm2)");
        excelRow.add("水质PH值");
        excelRow.add("时间");
        excelRow.add("设备编号");
        excelSheet.add(excelRow);
        for (int i = 0; i < list.size(); i++) {
        	excelRow = new ExcelRow();
        	excelRow.add(i+1+"");
			excelRow.add(list.get(i).getWaterLevel());
			excelRow.add(list.get(i).getDissolvedOXY());
			excelRow.add(list.get(i).getTemperature());
			excelRow.add(list.get(i).getOxidationReductionPotential());
			excelRow.add(list.get(i).getConductivity());
			excelRow.add(list.get(i).getPh());
			excelRow.add(list.get(i).getLongDate());
			excelRow.add(list.get(i).getEquipmentNO());
			excelSheet.add(excelRow);
		}
        try {
			new ExcelUtils().writeExcel(response.getOutputStream(), excelSheets);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Override
	public void exportByHour(String beginTime, String endTime,
			String equipmentNO, HttpServletResponse response) {
		List<ExportHydrologicalWaterQuality> list = hWaterQualityMapper.exportByHour(beginTime, endTime, equipmentNO);
		ExcelSheet excelSheet = new ExcelSheet();
		excelSheet.setName("水质监测小时数据" + new SimpleDateFormat("yyMMddHHmmss").format(new Date()));
		List<ExcelSheet> excelSheets = new ArrayList<>();
        excelSheets.add(excelSheet);
        ExcelRow excelRow = new ExcelRow();
        excelRow.add("序号");
        excelRow.add("水位(cm)");
        excelRow.add("水质溶解氧(mg/L)");
        excelRow.add("水温(℃)");
        excelRow.add("氧化还原电位(mV)");
        excelRow.add("水质电导率(uS/cm2)");
        excelRow.add("水质PH值");
        excelRow.add("时间");
        excelRow.add("设备编号");
        excelSheet.add(excelRow);
        for (int i = 0; i < list.size(); i++) {
        	excelRow = new ExcelRow();
        	excelRow.add(i+1+"");
			excelRow.add(list.get(i).getWaterLevel());
			excelRow.add(list.get(i).getDissolvedOXY());
			excelRow.add(list.get(i).getTemperature());
			excelRow.add(list.get(i).getOxidationReductionPotential());
			excelRow.add(list.get(i).getConductivity());
			excelRow.add(list.get(i).getPh());
			excelRow.add(list.get(i).getLongDate());
			excelRow.add(list.get(i).getEquipmentNO());
			excelSheet.add(excelRow);
		}
        try {
			new ExcelUtils().writeExcel(response.getOutputStream(), excelSheets);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Override
	public void exportByMonth(String beginTime, String endTime,
			String equipmentNO, HttpServletResponse response) {
		List<ExportHydrologicalWaterQuality> list = hWaterQualityMapper.exportByMonth(beginTime, endTime, equipmentNO);
		ExcelSheet excelSheet = new ExcelSheet();
		excelSheet.setName("水质监测月数据" + new SimpleDateFormat("yyMMddHHmmss").format(new Date()));
		List<ExcelSheet> excelSheets = new ArrayList<>();
        excelSheets.add(excelSheet);
        ExcelRow excelRow = new ExcelRow();
        excelRow.add("序号");
        excelRow.add("水位(cm)");
        excelRow.add("水质溶解氧(mg/L)");
        excelRow.add("水温(℃)");
        excelRow.add("氧化还原电位(mV)");
        excelRow.add("水质电导率(uS/cm2)");
        excelRow.add("水质PH值");
        excelRow.add("时间");
        excelRow.add("设备编号");
        excelSheet.add(excelRow);
        for (int i = 0; i < list.size(); i++) {
        	excelRow = new ExcelRow();
        	excelRow.add(i+1+"");
			excelRow.add(list.get(i).getWaterLevel());
			excelRow.add(list.get(i).getDissolvedOXY());
			excelRow.add(list.get(i).getTemperature());
			excelRow.add(list.get(i).getOxidationReductionPotential());
			excelRow.add(list.get(i).getConductivity());
			excelRow.add(list.get(i).getPh());
			excelRow.add(list.get(i).getLongDate());
			excelRow.add(list.get(i).getEquipmentNO());
			excelSheet.add(excelRow);
		}
        try {
			new ExcelUtils().writeExcel(response.getOutputStream(), excelSheets);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
