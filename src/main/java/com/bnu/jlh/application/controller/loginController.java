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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import com.bnu.jlh.application.service.*;
import com.bnu.jlh.application.model.*;
import javax.servlet.http.HttpServletRequest;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestParam;
import javax.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.ResponseBody;
@Controller
@ResponseBody
public class loginController {
	@Autowired
	private UserService useS;
	user URe =new user();
	@RequestMapping(value="/to_login.action",produces = "text/plain;charset=utf-8")
	public String LoginUser(HttpServletRequest request) {
//		String username = request.getParameter("username");
//		String password = request.getParameter("password");
//		System.out.println(username+"    "+password);
//		// 数据的封装:
//		User user = new User();
//		user.setUsername(username);
//		user.setPassword(password);
//		URe=useS.login(username,password);
//		System.out.println("执行正常");
		return "login";

		
	}
	@ResponseBody
    @RequestMapping("/login.action")
    public Object login(HttpSession session, @RequestBody String reqJson){
		System.out.println(reqJson);
		String username = JSONObject.parseObject(reqJson).getString("username");
		String password = JSONObject.parseObject(reqJson).getString("password");
        user user= useS.login(username,password);
        ResponseWrapper res = new ResponseWrapper();
        if (user == null) {
        	res.setReturnMsg("failed");
            return res;
        }
        

        session.setAttribute("user",user);
        res.setReturnMsg("success");
        return res;

    }
    @RequestMapping("/to_adduser.action")
    public Object adduser(HttpSession session, @RequestBody String reqJson){
    	user Useadd =new user();
    	ResponseWrapper res = new ResponseWrapper();
		String username = JSONObject.parseObject(reqJson).getString("username");
		String password = JSONObject.parseObject(reqJson).getString("password");
    	Useadd.setPassword(password);
    	Useadd.setUsername(username);
    	if(password==null||username==null||password==""||username=="") {
        	res.setReturnMsg("failed");
            return res;
        }
    	int userlevel=2;
    	Useadd.setUserlevel(userlevel);
    	session.setAttribute("user", Useadd);
       int re = useS.add(Useadd);
       if(re>0)
       {
       	res.setReturnMsg("success");
           return res;
       }
       res.setReturnMsg("failed");
       return res;
    }
    

	@RequestMapping(value = "/to_checklevel.action",method=RequestMethod.POST)
	@ResponseBody
    public Object checklevel(HttpSession session) {
    	ResponseWrapper res = new ResponseWrapper();
    	if(session.getAttribute("user")==null)
    	{
    		res.setReturnMsg("NullUser");
    		return res;
    	}
    		
    	user SeUser =(user)session.getAttribute("user");
//    	if (SeUser.getUserlevel() == 2||SeUser.getUserlevel() == null)
    	if(SeUser.getUserlevel()== 1)
    	{
    		res.setReturnMsg("success");
    		return res;
    	}
    	res.setReturnMsg("failed");
    	return res;
    	
    }
	@ResponseBody
    @RequestMapping("/hello")
    public Object hello(){

       return "hellsdvo";
    }

}




