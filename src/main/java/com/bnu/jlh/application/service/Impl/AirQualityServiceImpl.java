package com.bnu.jlh.application.service.Impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bnu.jlh.application.common.ExcelRow;
import com.bnu.jlh.application.common.ExcelSheet;
import com.bnu.jlh.application.common.ExcelUtils;
import com.bnu.jlh.application.dao.AirQualityStationDao;
import com.bnu.jlh.application.model.AirQualityStation;
import com.bnu.jlh.application.model.ExportAirQualityStation;
import com.bnu.jlh.application.model.ResAir;
import com.bnu.jlh.application.model.ResSoil;
import com.bnu.jlh.application.model.ResponseWrapper;
import com.bnu.jlh.application.service.AirQualityService;
import com.github.pagehelper.PageInfo;

@Service
public class AirQualityServiceImpl implements AirQualityService{
	@Autowired
	private AirQualityStationDao airQualityMapper;
	@Override
	public List<AirQualityStation> findDataByEquipmentNOAndData(
			String equipmentNO, String data) {
		return airQualityMapper.findDateByEquipmentNOAndData(equipmentNO, data);
	}
	@Override
	public List<AirQualityStation> findHistoryOfEquipment(String equipmentNO) {
		return airQualityMapper.findHistoryOfEquipment(equipmentNO);
	}
	
	
	@Override
	public List<AirQualityStation> findDataOneDay(String equipmentNO) {

		if (equipmentNO.isEmpty()) {
			return null;
		}
		Date dd = new Date();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
		String endDate = df.format(dd);//当前时间
		String startDate = df.format(dd.getTime()-1*24*60*60*1000);//前一天的时间
		
		List<AirQualityStation> list = airQualityMapper.findDataOneDay(startDate, endDate, equipmentNO);
		
		return list;
	}

	/**
	 * 根据起止时间和设备编号查询空气质量历史数据
	 */
	@Override
	public ResponseWrapper queryHistoryByDataAndNo(String beginTime,String endTime,String equipmentNO) {
		ResponseWrapper res = new ResponseWrapper();
		List<ResAir> resAir = airQualityMapper.queryHistory(beginTime,endTime,equipmentNO);
		if(resAir!=null&&resAir.size()>0){
			PageInfo<ResAir> pageInfo = new PageInfo<ResAir>(resAir,5);
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
	 * 保存空气质量监测站数据
	 */
	@Override
	public ResponseWrapper saveAir(AirQualityStation air) {
		ResponseWrapper res = new ResponseWrapper();
		int flag = airQualityMapper.saveAir(air);
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
	public AirQualityStation findCurrentData() {
		return airQualityMapper.findCurrentData();
	}
	@Override
	public void exportByDay(String beginTime, String endTime,
			String equipmentNO, HttpServletResponse response) {
		List<ExportAirQualityStation> list = airQualityMapper.exportByDay(beginTime, endTime, equipmentNO);
		String name="空气质量日数据";
		exportExcel(list, name, response);
	}
	@Override
	public void exportByHour(String beginTime, String endTime,
			String equipmentNO, HttpServletResponse response) {
		List<ExportAirQualityStation> list = airQualityMapper.exportByHour(beginTime, endTime, equipmentNO);
		
		String name="空气质量小时数据";
		exportExcel(list, name, response);
	}
	@Override
	public void exportByMonth(String beginTime, String endTime,
			String equipmentNO, HttpServletResponse response) {
		List<ExportAirQualityStation> list = airQualityMapper.exportByMonth(beginTime, endTime, equipmentNO);
		String name="空气质量月数据";
		exportExcel(list, name, response);
	}
	
	public void exportExcel(List<ExportAirQualityStation> list,String name, HttpServletResponse response){
		ExcelSheet excelSheet = new ExcelSheet();
		excelSheet.setName(name + new SimpleDateFormat("yyMMddHHmmss").format(new Date()));
		List<ExcelSheet> excelSheets = new ArrayList<>();
        excelSheets.add(excelSheet);
        ExcelRow excelRow = new ExcelRow();
        excelRow.add("序号");
        excelRow.add("空气质量-风速(m/s)");
        excelRow.add("空气质量风向");
        excelRow.add("pm2.5(ug/m³)");
        excelRow.add("pm10(ug/m³)");
        excelRow.add("O3(ug/m³)");
        excelRow.add("CO(ppm)");
        excelRow.add("SO2(mg/m³)");
        excelRow.add("NO2(mg/m³)");
        excelRow.add("时间");
        excelRow.add("设备编号");
        excelSheet.add(excelRow);
        for (int i = 0; i < list.size(); i++) {
        	excelRow = new ExcelRow();
        	excelRow.add(i+1+"");
        	if(list.get(i).getWindSpeed()!=null && !list.get(i).getWindSpeed().isEmpty()){
        		excelRow.add(list.get(i).getWindSpeed());
        	}else{
        		excelRow.add("");
        	}
        	if(list.get(i).getWindDirection()!=null && !list.get(i).getWindDirection().isEmpty()){
        		excelRow.add(list.get(i).getWindDirection());
        	}else{
        		excelRow.add("");
        	}
        	if(list.get(i).getPm25()!=null && !list.get(i).getPm25().isEmpty()){
        		excelRow.add(list.get(i).getPm25());
        	}else{
        		excelRow.add("");
        	}
        	if(list.get(i).getPm10()!=null && !list.get(i).getPm10().isEmpty()){
        		excelRow.add(list.get(i).getPm10());
        	}else{
        		excelRow.add("");
        	}
        	if(list.get(i).getO3()!=null && !list.get(i).getO3().isEmpty()){
        		excelRow.add(list.get(i).getO3());
        	}else{
        		excelRow.add("");
        	}
        	if(list.get(i).getCo()!=null && !list.get(i).getCo().isEmpty()){
        		excelRow.add(list.get(i).getCo());
        	}else{
        		excelRow.add("");
        	}
        	if(list.get(i).getSo2()!=null && !list.get(i).getSo2().isEmpty()){
        		excelRow.add(list.get(i).getSo2());
        	}else{
        		excelRow.add("");
        	}
        	if(list.get(i).getNo2()!=null && !list.get(i).getNo2().isEmpty()){
        		excelRow.add(list.get(i).getNo2());
        	}else{
        		excelRow.add("");
        	}
        	if(list.get(i).getLongDate()!=null && !list.get(i).getLongDate().isEmpty()){
        		excelRow.add(list.get(i).getLongDate());
        	}else{
        		
        	}
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
	
}
