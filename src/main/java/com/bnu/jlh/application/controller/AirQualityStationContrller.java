package com.bnu.jlh.application.controller;

import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.bnu.jlh.application.model.AirQualityStation;
import com.bnu.jlh.application.model.ResponseWrapper;
import com.bnu.jlh.application.service.AirQualityService;
import com.github.pagehelper.PageHelper;

@RestController
public class AirQualityStationContrller {
	@Autowired
	private AirQualityService airQualityService;
	/**
	 * 实时数据展示
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	@RequestMapping(value = "/airQualityOnTime",method=RequestMethod.POST)
	public Object findweatherByEquipmentNOAndData(@RequestBody String reqJson) throws UnsupportedEncodingException{
		ResponseWrapper res = new ResponseWrapper();
		try {
			String equipmentNO = JSONObject.parseObject(reqJson).getString("equipmentNO");
			if (equipmentNO==null || equipmentNO.isEmpty()) {
				res.setReturnCode("502");
				res.setReturnMsg("设备编号不能为空");
				return res;
			}
			List<AirQualityStation> list = airQualityService.findDataOneDay(equipmentNO);
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
	 * @return
	 */
	@RequestMapping(value = "/getAirOne",method=RequestMethod.POST)
	public Object getAirOne(){
		ResponseWrapper res = new ResponseWrapper();
		try {
			AirQualityStation air = airQualityService.findCurrentData();
			if(air!=null){
				res.setReturnObj(air);
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
	@RequestMapping(value = "/queryAirHistory",method=RequestMethod.POST)
	@ResponseBody
	public Object findhistory(@RequestBody String reqJson,HttpServletRequest request){
		ResponseWrapper res = new ResponseWrapper();
		try {
			String beginTime=JSON.parseObject(reqJson).getString("beginTime");
			beginTime = beginTime==null?"":beginTime;
			String endTime=JSON.parseObject(reqJson).getString("endTime");
			endTime = endTime==null?"":endTime;
			String equipmentNO=JSON.parseObject(reqJson).getString("equipmentNO");
			equipmentNO = equipmentNO==null?"":equipmentNO;
			int pageNum = JSONObject.parseObject(reqJson).getIntValue("pageNum");
			PageHelper.startPage(pageNum, 10);
			res = airQualityService.queryHistoryByDataAndNo(beginTime, endTime, equipmentNO);
		} catch (Exception e) {
			e.getMessage();
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
	@RequestMapping(value = "/exportAirByDay",method=RequestMethod.POST)
	public Object exportByDay(@RequestParam(value="beginTime",required=false)String beginTime,
			@RequestParam(value="endTime",required=false)String endTime,
			HttpServletResponse response) throws UnsupportedEncodingException{
		ResponseWrapper res = new ResponseWrapper();
		try {
			
			airQualityService.exportByDay(beginTime,endTime,"",response);
			
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
	@RequestMapping(value = "/exportAirByHour",method=RequestMethod.POST)
	public Object exportByHour(@RequestParam(value="beginTime",required=false)String beginTime,
			@RequestParam(value="endTime",required=false)String endTime,
			HttpServletResponse response) throws UnsupportedEncodingException{
		ResponseWrapper res = new ResponseWrapper();
		try {
			airQualityService.exportByHour(beginTime,endTime,"",response);
			
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
	@RequestMapping(value = "/exportAirByMonth",method=RequestMethod.POST)
	public Object exportByMonth(@RequestParam(value="beginTime",required=false)String beginTime,
			@RequestParam(value="endTime",required=false)String endTime,
			HttpServletResponse response) throws UnsupportedEncodingException{
		ResponseWrapper res = new ResponseWrapper();
		try {
			airQualityService.exportByMonth(beginTime,endTime,"",response);
			
		} catch (Exception e) {
			e.getMessage();
			res.setReturnCode("502");
			res.setReturnMsg("未知异常请与管理员联系");
		}
		return res;
	}
}
