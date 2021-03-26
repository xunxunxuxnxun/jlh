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
import com.bnu.jlh.application.dao.SoilStationDao;
import com.bnu.jlh.application.model.ResSoil;
import com.bnu.jlh.application.model.ResponseWrapper;
import com.bnu.jlh.application.model.SoilStation;
import com.bnu.jlh.application.model.WeatherStation;
import com.bnu.jlh.application.service.SoilStationService;
import com.github.pagehelper.PageInfo;

@Service
public class SoilStationServiceImpl implements SoilStationService{
	@Autowired
	private SoilStationDao soilStationMapper;
	@Override
	public List<SoilStation> findDataByEquipmentNOAndData(String equipmentNO,
			String data) {
		return soilStationMapper.findDateByEquipmentNOAndData(equipmentNO, data);
	}
	@Override
	public List<SoilStation> findHistoryOfEquipment(String equipmentNO) {
		return soilStationMapper.findHistoryOfEquipment(equipmentNO);
	}
	
	
	@Override
	public List<SoilStation> findDataOneDay(String equipmentNO) {
		if (equipmentNO.isEmpty()) {
			return null;
		}
		Date dd = new Date();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
		String endDate = df.format(dd);//当前时间
		String startDate = df.format(dd.getTime()-1*24*60*60*1000);//前一天的时间
		
		List<SoilStation> list= soilStationMapper.findDataOneDay(startDate, endDate, equipmentNO);
		
		return list;
	}
	
	/**
	 * 根据起止时间和设备编号查询土壤历史数据
	 */
	@Override
	public ResponseWrapper queryHistoryByDataAndNo(String beginTime,String endTime,String equipmentNO) {
		ResponseWrapper res = new ResponseWrapper();
		List<ResSoil> resSoil = soilStationMapper.queryHistory(beginTime,endTime,equipmentNO);
		if(resSoil!=null&&resSoil.size()>0){
			PageInfo<ResSoil> pageInfo = new PageInfo<ResSoil>(resSoil,5);
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
	 * 保存土壤监测站数据
	 */
	@Override
	public ResponseWrapper saveSoil(SoilStation soil) {
		ResponseWrapper res = new ResponseWrapper();
		int flag = soilStationMapper.saveSoil(soil);
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
	public SoilStation findCurrentData() {
		
		return soilStationMapper.findCurrentData();
	}
	
	
	@Override
	public void exportByDay(String beginTime, String endTime,
			String equipmentNO, HttpServletResponse response) {
		List<ResSoil> list = soilStationMapper.exportByDay(beginTime, endTime, equipmentNO);
		ExcelSheet excelSheet = new ExcelSheet();
		excelSheet.setName("土壤监测日数据" + new SimpleDateFormat("yyMMddHHmmss").format(new Date()));
		List<ExcelSheet> excelSheets = new ArrayList<>();
        excelSheets.add(excelSheet);
        ExcelRow excelRow = new ExcelRow();
        excelRow.add("序号");
        excelRow.add("土壤温度(℃)");
        excelRow.add("土壤湿度(%)");
        excelRow.add("土壤电导率(us/cm)");
        excelRow.add("土壤PH(Lux)");
        excelRow.add("时间");
        excelRow.add("设备编号");
        excelSheet.add(excelRow);
        for (int i = 0; i < list.size(); i++) {
        	excelRow = new ExcelRow();
        	excelRow.add(i+1+"");
			excelRow.add(list.get(i).getTemperature());
			excelRow.add(list.get(i).getHumidity());
			excelRow.add(list.get(i).getConductivity());
			excelRow.add(list.get(i).getPh());
			excelRow.add(list.get(i).getLongDate());
			if(list.get(i).getEquipmentNO()!=null && !list.get(i).getEquipmentNO().isEmpty()){
				excelRow.add(list.get(i).getEquipmentNO());
			}else{
				excelRow.add("");
			}
			
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
		List<ResSoil> list = soilStationMapper.exportByHour(beginTime, endTime, equipmentNO);
		ExcelSheet excelSheet = new ExcelSheet();
		excelSheet.setName("土壤监测日数据" + new SimpleDateFormat("yyMMddHHmmss").format(new Date()));
		List<ExcelSheet> excelSheets = new ArrayList<>();
        excelSheets.add(excelSheet);
        ExcelRow excelRow = new ExcelRow();
        excelRow.add("序号");
        excelRow.add("土壤温度(℃)");
        excelRow.add("土壤湿度(%)");
        excelRow.add("土壤电导率(us/cm)");
        excelRow.add("土壤PH(Lux)");
        excelRow.add("时间");
        excelRow.add("设备编号");
        excelSheet.add(excelRow);
        for (int i = 0; i < list.size(); i++) {
        	excelRow = new ExcelRow();
        	excelRow.add(i+1+"");
			excelRow.add(list.get(i).getTemperature());
			excelRow.add(list.get(i).getHumidity());
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
		List<ResSoil> list = soilStationMapper.exportByMonth(beginTime, endTime, equipmentNO);
		ExcelSheet excelSheet = new ExcelSheet();
		excelSheet.setName("土壤监测日数据" + new SimpleDateFormat("yyMMddHHmmss").format(new Date()));
		List<ExcelSheet> excelSheets = new ArrayList<>();
        excelSheets.add(excelSheet);
        ExcelRow excelRow = new ExcelRow();
        excelRow.add("序号");
        excelRow.add("土壤温度(℃)");
        excelRow.add("土壤湿度(%)");
        excelRow.add("土壤电导率(us/cm)");
        excelRow.add("土壤PH(Lux)");
        excelRow.add("时间");
        excelRow.add("设备编号");
        excelSheet.add(excelRow);
        for (int i = 0; i < list.size(); i++) {
        	excelRow = new ExcelRow();
        	excelRow.add(i+1+"");
			excelRow.add(list.get(i).getTemperature());
			excelRow.add(list.get(i).getHumidity());
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
