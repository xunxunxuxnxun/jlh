package com.bnu.jlh.application.controller;



import java.net.URLEncoder;

import org.json.JSONObject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.bind.annotation.RequestMethod;
import javax.servlet.http.*;

import com.bnu.jlh.application.utils.Base64Util;
import com.bnu.jlh.application.utils.HttpUtil;
@RestController
public class IdiController {
	
	private String accessToken = "";



    @RequestMapping(value="/animal",method=RequestMethod.POST)

    @ResponseBody

    public String animal(@RequestParam MultipartFile file,HttpServletRequest request, HttpServletResponse response) {
        // 注意这里仅为了简化编码每一次请求都去获取access_token，线上环境access_token有过期时间， 客户端可自行缓存，过期后重新获取。
        accessToken = AuthService.getToken();
//        String File = file.getOriginalFilename();
        String url = "https://aip.baidubce.com/rest/2.0/image-classify/v1/animal";
        try {
            // 本地文件路径
//            String filePath = "F:/Google下载/aaa.jpg";
//            byte[] imgData = FileUtil.readFileByBytes(file);
            String imgStr = Base64Util.encode(file.getBytes());
            String imgParam = URLEncoder.encode(imgStr, "UTF-8");
            String param = "image=" + imgParam + "&top_num=" + 6;
            String result = HttpUtil.post(url, accessToken, param);
            JSONObject json = new JSONObject(result);
            JSONObject answer = (JSONObject) json.getJSONArray("result").get(0); 
//            return answer.getString("name");
            System.out.println(answer.toString());
            return json.toString();

        } catch (Exception e) {

            e.printStackTrace();

        }

        return null;

    }
    @RequestMapping(value="/plant",method=RequestMethod.POST)

    @ResponseBody

    public String plant(@RequestParam MultipartFile file,HttpServletRequest request, HttpServletResponse response) {
        accessToken = AuthService.getToken();
        String url = "https://aip.baidubce.com/rest/2.0/image-classify/v1/plant";
        try {
            String imgStr = Base64Util.encode(file.getBytes());
            String imgParam = URLEncoder.encode(imgStr, "UTF-8");
            String param = "image=" + imgParam + "&top_num=" + 6;
            String result = HttpUtil.post(url, accessToken, param);
            JSONObject json = new JSONObject(result);
            JSONObject answer = (JSONObject) json.getJSONArray("result").get(0);
            System.out.println(answer.toString());
            return json.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }


}