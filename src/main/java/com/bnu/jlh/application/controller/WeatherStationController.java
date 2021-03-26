package com.bnu.jlh.application.controller;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.bnu.jlh.application.model.ResponseWrapper;
import com.bnu.jlh.application.model.SoilStation;
import com.bnu.jlh.application.model.WeatherStation;
import com.bnu.jlh.application.service.WeatherStationService;
import com.github.pagehelper.PageHelper;

@RestController
public class WeatherStationController {
	@Autowired
	private WeatherStationService weatherService;
	
	/**
	 * 实时数据
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	@RequestMapping(value = "/weatherOnTime",method=RequestMethod.POST)
	public Object findWeatherByEquipmentNOAndData(@RequestBody String reqJson) throws UnsupportedEncodingException{
		ResponseWrapper res = new ResponseWrapper();
		try {
			String equipmentNO = JSONObject.parseObject(reqJson).getString("equipmentNO");
			if (equipmentNO==null || equipmentNO.isEmpty()) {
				res.setReturnCode("502");
				res.setReturnMsg("设备编号不能为空");
				return res;
			}
			List<WeatherStation> list = weatherService.findDataOneDay(equipmentNO);
			if(list!=null && list.size()>0){
				res.setReturnObj(list);
			}
		} catch (Exception e) {
			e.getMessage();
			res.setReturnCode("502");
			res.setReturnMsg("未知异常请与管理员联系");
		}
		return res;
	}

	/**
	 * 查询最近一条数据
	 * @param model
	 * @param request
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping(value = "/getWeatherOne",method=RequestMethod.POST)
	public Object getWeatherOne() throws UnsupportedEncodingException{
		ResponseWrapper res = new ResponseWrapper();
		try {
			WeatherStation weather = weatherService.findCurrentData();
			if(weather!=null){
				res.setReturnObj(weather);
			}
		} catch (Exception e) {
			e.getMessage();
			res.setReturnCode("502");
			res.setReturnMsg("未知异常请与管理员联系");
		}
		return res;
	}
	
	/**
	 * 根据起止时间查询历史数据
	 * @param model
	 * @param equipmentType
	 * @param equipmentNO
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	@RequestMapping(value = "/queryWeatherHistory",method=RequestMethod.POST)
	@ResponseBody
	public Object findhistory(@RequestBody String reqJson,HttpServletRequest request){
		ResponseWrapper res = new ResponseWrapper();
		String str = null;
		try {
			String beginTime=JSON.parseObject(reqJson).getString("beginTime");
			beginTime = beginTime==null?"":beginTime;
			String endTime=JSON.parseObject(reqJson).getString("endTime");
			endTime = endTime==null?"":endTime;
			String equipmentNO=JSON.parseObject(reqJson).getString("equipmentNO");
			equipmentNO = equipmentNO==null?"":equipmentNO;
			int pageNum = JSONObject.parseObject(reqJson).getIntValue("pageNum");
			PageHelper.startPage(pageNum, 10);
			res = weatherService.queryHistoryByDataAndNo(beginTime, endTime, equipmentNO);
//			str = JSONObject.toJSONString(res);
		} catch (Exception e) {
			e.printStackTrace();
			res.setReturnCode("502");
			res.setReturnMsg("未知异常请与管理员联系");
		}
		return res;
	}
	
	
	
	/**
	 * 导出日数据
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	@RequestMapping(value = "/exportWeatherByDay",method=RequestMethod.POST)
	public Object exportByDay(@RequestParam(value="beginTime",required=false)String beginTime,
			@RequestParam(value="endTime",required=false)String endTime,
			HttpServletResponse response) throws UnsupportedEncodingException{
		ResponseWrapper res = new ResponseWrapper();
		try {
			weatherService.exportByDay(beginTime,endTime,"",response);
			
		} catch (Exception e) {
			e.getMessage();
			res.setReturnCode("502");
			res.setReturnMsg("未知异常请与管理员联系");
		}
		return res;
	}
	
	/**
	 * 导出小时数据
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	@RequestMapping(value = "/exportWeatherByHour",method=RequestMethod.POST)
	public Object exportByHour(@RequestParam(value="beginTime",required=false)String beginTime,
			@RequestParam(value="endTime",required=false)String endTime,
			HttpServletResponse response) throws UnsupportedEncodingException{
		ResponseWrapper res = new ResponseWrapper();
		try {
			weatherService.exportByHour(beginTime,endTime,"",response);
			
		} catch (Exception e) {
			e.getMessage();
			res.setReturnCode("502");
			res.setReturnMsg("未知异常请与管理员联系");
		}
		return res;
	}
	
	/**
	 * 导出月数据
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	@RequestMapping(value = "/exportWeatherByMonth",method=RequestMethod.POST)
	public Object exportByMonth(@RequestParam(value="beginTime",required=false)String beginTime,
			@RequestParam(value="endTime",required=false)String endTime,
			HttpServletResponse response) throws UnsupportedEncodingException{
		ResponseWrapper res = new ResponseWrapper();
		try {
			
			weatherService.exportByMonth(beginTime,endTime,"",response);
			
		} catch (Exception e) {
			e.getMessage();
			res.setReturnCode("502");
			res.setReturnMsg("未知异常请与管理员联系");
		}
		return res;
	}
}
