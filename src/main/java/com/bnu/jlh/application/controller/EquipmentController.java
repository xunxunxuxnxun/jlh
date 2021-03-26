package com.bnu.jlh.application.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.bnu.jlh.application.model.Equipment;
import com.bnu.jlh.application.model.ReqEquipment;
import com.bnu.jlh.application.model.ResAlarm;
import com.bnu.jlh.application.model.ResponseWrapper;
import com.bnu.jlh.application.service.EquipmentService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@RestController
public class EquipmentController {
	@Autowired
	private EquipmentService equipmentService;
	
	@RequestMapping(value="/hello",method=RequestMethod.POST)
	public Object test(){
		ResponseWrapper res = new ResponseWrapper();
		res.setReturnMsg("hello");
		return res;
	}
	/**
	 * 设备信息
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/EquipmentInfo",method=RequestMethod.POST)
	public Object findAllEquipment(Model model){
		ResponseWrapper res = new ResponseWrapper();
		try {
			List<Equipment> list = equipmentService.findAllEquipments();
			if(list!=null&& list.size()>0){
				res.setReturnObj(list);
			}
		} catch (Exception e) {
			res.setReturnCode("502");
			res.setReturnMsg("未知异常请与管理员联系");
		}
		return res;
	}
	/**
	 * 查询设备坐标点
	 * @return
	 */
	@RequestMapping(value = "/queryAllPoint",method=RequestMethod.POST)
	public Object queryAllPoint(){
		ResponseWrapper res = new ResponseWrapper();
		try {
			List<Equipment> list = equipmentService.queryAllPoint();
			if(list!=null && list.size()>0){
				res.setReturnObj(list);
			}else{
				res.setReturnCode("400");
				res.setReturnMsg("失败");
				res.setReturnObj("");
			}
		} catch (Exception e) {
			res.setReturnCode("502");
			res.setReturnMsg("未知异常请与管理员联系");
		}
		return res;
	}
	/**
	 * 添加设备
	 * @param reqJson
	 * @return
	 */
	@RequestMapping(value = "/addEquipment",method=RequestMethod.POST)
	public Object addEquipment(@RequestBody String reqJson){
		ResponseWrapper res = new ResponseWrapper();
		try {
			ReqEquipment req = JSONObject.parseObject(reqJson,ReqEquipment.class);
			int flag = equipmentService.addEquipment(req);
			if(flag>0){
				res.setReturnObj("");
			}else{
				res.setReturnCode("400");
				res.setReturnMsg("失败");
			}
		} catch (Exception e) {
			e.printStackTrace();
			res.setReturnCode("502");
			res.setReturnMsg("未知异常请与管理员联系");
			res.setReturnObj("");
		}
		return res;
	}
	
	/**
	 * 查询设备列表
	 * @param reqJson
	 * @return
	 */
	@RequestMapping(value = "/getEquipmentList",method=RequestMethod.POST)
	public Object getEquipmentList(@RequestBody String reqJson){
		ResponseWrapper res = new ResponseWrapper();
		try {
			int pageNum = JSONObject.parseObject(reqJson).getIntValue("pageNum");
			if(pageNum!=0){
				PageHelper.startPage(pageNum, 10);
				List<Equipment> list = equipmentService.getEquipmentList();
				if(list!=null && list.size()>0){
					PageInfo<Equipment> pageInfo = new PageInfo<Equipment>(list,5);
					res.setReturnObj(JSONObject.toJSONString(pageInfo));
				}else{
					res.setReturnCode("400");
					res.setReturnMsg("失败");
				}
			}else{
				List<Equipment> list = equipmentService.getEquipmentList();
				res.setReturnObj(JSONObject.toJSONString(list));
			}
		} catch (Exception e) {
			e.printStackTrace();
			res.setReturnCode("502");
			res.setReturnMsg("未知异常请与管理员联系");
		}
		return res;
	}
	
	/**
	 * 查询设备报警信息
	 * @param reqJson
	 * @return
	 */
	@RequestMapping(value = "/getEquipmentAlarm",method=RequestMethod.POST)
	public Object getEquipmentAlarm(@RequestBody String reqJson){
		ResponseWrapper res = new ResponseWrapper();
		try {
			String beginTime = JSONObject.parseObject(reqJson).getString("beginTime");
			String endTime = JSONObject.parseObject(reqJson).getString("endTime");
			String equipmentType = JSONObject.parseObject(reqJson).getString("equipmentType");
			String equipmentNumber = JSONObject.parseObject(reqJson).getString("equipmentNumber");
			String alarmType = JSONObject.parseObject(reqJson).getString("alarmType");
			int pageNum = JSONObject.parseObject(reqJson).getIntValue("pageNum");
			PageHelper.startPage(pageNum, 10);
			List<ResAlarm> list = equipmentService.getEquipmentAlarm(equipmentNumber, equipmentType, beginTime, endTime, alarmType);
			if(list!=null && list.size()>0){
				PageInfo<ResAlarm> pageInfo = new PageInfo<ResAlarm>(list,5);
				res.setReturnObj(JSONObject.toJSONString(pageInfo));
			}else{
				res.setReturnCode("400");
				res.setReturnMsg("失败");
			}
		} catch (Exception e) {
			e.printStackTrace();
			res.setReturnCode("502");
			res.setReturnMsg("未知异常请与管理员联系");
		}
		return res;
	}
	
	/**
	 * 查询设备信息
	 * @param reqJson
	 * @return
	 */
	@RequestMapping(value = "/getEquipmentInfo",method=RequestMethod.POST)
	public Object getEquipmentInfo(@RequestBody String reqJson){
		ResponseWrapper res = new ResponseWrapper();
		try {
			int pageNum = JSONObject.parseObject(reqJson).getIntValue("pageNum");
			PageHelper.startPage(pageNum, 10);
			List<Equipment> list = equipmentService.getEquipmentList();
			if(list!=null && list.size()>0){
				PageInfo<Equipment> pageInfo = new PageInfo<Equipment>(list,5);
				res.setReturnObj(JSONObject.toJSONString(pageInfo));
			}else{
				res.setReturnCode("400");
				res.setReturnMsg("失败");
			}
		} catch (Exception e) {
			e.printStackTrace();
			res.setReturnCode("502");
			res.setReturnMsg("未知异常请与管理员联系");
		}
		return res;
	}
}
