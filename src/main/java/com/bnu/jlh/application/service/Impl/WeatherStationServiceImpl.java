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
import com.bnu.jlh.application.dao.WeatherStationDao;
import com.bnu.jlh.application.model.ResWeather;
import com.bnu.jlh.application.model.ResponseWrapper;
import com.bnu.jlh.application.model.WeatherStation;
import com.bnu.jlh.application.service.WeatherStationService;
import com.github.pagehelper.PageInfo;

@Service
public class WeatherStationServiceImpl implements WeatherStationService{
	@Autowired
	private WeatherStationDao weatherStationMapper;

	/**
	 * ��ѯ����վ������Ϣ��������
	 */
	public List<WeatherStation> findAllWeatherStations() {
		return weatherStationMapper.findAllWeatherStations();
	}
	
	@Override
	public List<WeatherStation> findDataOneDay(String equipmentNO) {
		if (equipmentNO.isEmpty()) {
			return null;
		}
		Date dd = new Date();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
		String endDate = df.format(dd);//结束时间
		String startDate = df.format(dd.getTime()-1*24*60*60*1000);//开始时间
		
		List<WeatherStation> list = weatherStationMapper.findDataOneDay(startDate, endDate, equipmentNO);
		
		return list;
	}

	/**
	 * 根据起止时间和设备编号查询气象站历史数据
	 */
	@Override
	public ResponseWrapper queryHistoryByDataAndNo(String beginTime,String endTime,String equipmentNO) {
		ResponseWrapper res = new ResponseWrapper();
		List<ResWeather> resWeather = weatherStationMapper.queryHistory(beginTime,endTime,equipmentNO);
		if(resWeather!=null&&resWeather.size()>0){
			PageInfo<ResWeather> pageInfo = new PageInfo<ResWeather>(resWeather,5);
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
	 * 气象站数据存入数据库
	 */
	@Override
	public ResponseWrapper saveWeather(WeatherStation weather) {
		ResponseWrapper res = new ResponseWrapper();
		int flag = weatherStationMapper.saveWeather(weather);
		if(flag>0){
			res.setReturnCode("200");
			res.setReturnMsg("保存数据成功");
		}else{
			res.setReturnCode("400");
			res.setReturnMsg("保存数据失败");
		}
		return res;
	}
	
	/**
	 * 查询离当前时间最近的一条数据，实时数据展示
	 * @param currentTime
	 * @return
	 */
	@Override
	public WeatherStation findCurrentData() {
		WeatherStation weather = weatherStationMapper.findCurrentData();
		
		return weather;
	}
	/**
	 * 导出日数据
	 */
	@Override
	public void exportByDay(String beginTime, String endTime,String equipmentNO,HttpServletResponse response) {
		List<WeatherStation> list = weatherStationMapper.exportByDay(beginTime, endTime, equipmentNO);
		ExcelSheet excelSheet = new ExcelSheet();
		excelSheet.setName("气象站日数据" + new SimpleDateFormat("yyMMddHHmmss").format(new Date()));
		List<ExcelSheet> excelSheets = new ArrayList<>();
        excelSheets.add(excelSheet);
        ExcelRow excelRow = new ExcelRow();
        excelRow.add("序号");
        excelRow.add("风速(m/s)");
        excelRow.add("风向");
        excelRow.add("噪音(db)");
        excelRow.add("温度(℃)");
        excelRow.add("湿度(%)");
        excelRow.add("气压(Kpa)");
        excelRow.add("光照(Lux)");
        excelRow.add("雨量(cm)");
        excelRow.add("时间");
        excelRow.add("设备编号");
        excelSheet.add(excelRow);
        for (int i = 0; i < list.size(); i++) {
        	excelRow = new ExcelRow();
        	excelRow.add(i+1+"");
			excelRow.add(list.get(i).getWindSpeed());
			excelRow.add(list.get(i).getWindDirection());
			excelRow.add(list.get(i).getNoise());
			excelRow.add(list.get(i).getTemperature());
			excelRow.add(list.get(i).getHumidity());
			excelRow.add(list.get(i).getGasPressure());
			excelRow.add(list.get(i).getBeam());
			excelRow.add(list.get(i).getRainfall());
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
		List<WeatherStation> list = weatherStationMapper.exportByHour(beginTime, endTime, equipmentNO);
		ExcelSheet excelSheet = new ExcelSheet();
		excelSheet.setName("气象站小时数据" + new SimpleDateFormat("yyMMddHHmmss").format(new Date()));
		List<ExcelSheet> excelSheets = new ArrayList<>();
        excelSheets.add(excelSheet);
        ExcelRow excelRow = new ExcelRow();
        excelRow.add("序号");
        excelRow.add("风速(m/s)");
        excelRow.add("风向");
        excelRow.add("噪音(db)");
        excelRow.add("温度(℃)");
        excelRow.add("湿度(%)");
        excelRow.add("气压(Kpa)");
        excelRow.add("光照(Lux)");
        excelRow.add("雨量(cm)");
        excelRow.add("时间");
        excelRow.add("设备编号");
        excelSheet.add(excelRow);
        for (int i = 0; i < list.size(); i++) {
        	excelRow = new ExcelRow();
        	excelRow.add(i+1+"");
			excelRow.add(list.get(i).getWindSpeed());
			excelRow.add(list.get(i).getWindDirection());
			excelRow.add(list.get(i).getNoise());
			excelRow.add(list.get(i).getTemperature());
			excelRow.add(list.get(i).getHumidity());
			excelRow.add(list.get(i).getGasPressure());
			excelRow.add(list.get(i).getBeam());
			excelRow.add(list.get(i).getRainfall());
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
		List<WeatherStation> list = weatherStationMapper.exportByMonth(beginTime, endTime, equipmentNO);
		ExcelSheet excelSheet = new ExcelSheet();
		excelSheet.setName("气象站月数据" + new SimpleDateFormat("yyMMddHHmmss").format(new Date()));
		List<ExcelSheet> excelSheets = new ArrayList<>();
        excelSheets.add(excelSheet);
        ExcelRow excelRow = new ExcelRow();
        excelRow.add("序号");
        excelRow.add("风速(m/s)");
        excelRow.add("风向");
        excelRow.add("噪音(db)");
        excelRow.add("温度(℃)");
        excelRow.add("湿度(%)");
        excelRow.add("气压(Kpa)");
        excelRow.add("光照(Lux)");
        excelRow.add("雨量(cm)");
        excelRow.add("时间");
        excelRow.add("设备编号");
        excelSheet.add(excelRow);
        for (int i = 0; i < list.size(); i++) {
        	excelRow = new ExcelRow();
        	excelRow.add(i+1+"");
			excelRow.add(list.get(i).getWindSpeed());
			excelRow.add(list.get(i).getWindDirection());
			excelRow.add(list.get(i).getNoise());
			excelRow.add(list.get(i).getTemperature());
			excelRow.add(list.get(i).getHumidity());
			excelRow.add(list.get(i).getGasPressure());
			excelRow.add(list.get(i).getBeam());
			excelRow.add(list.get(i).getRainfall());
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
