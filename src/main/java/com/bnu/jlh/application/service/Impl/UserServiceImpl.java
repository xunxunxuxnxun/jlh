package com.bnu.jlh.application.service.Impl;

import com.bnu.jlh.application.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bnu.jlh.application.service.UserService;
import com.bnu.jlh.application.dao.UserLoginDao;
@Service
public class UserServiceImpl implements UserService {
	
	@Autowired 
	private UserLoginDao ULM;

	@Override
	public user login(String username, String password) {
		// TODO Auto-generated method stub
		user UseRE = ULM.select(username,password);
        if(UseRE!=null&&UseRE.getPassword().equals(password)){
            return  UseRE;
        }
        return null;
	}


	@Override
	public int add(user use) {
		// TODO Auto-generated method stub
//		return ULM.add(use);		
		return ULM.add(use);
	}

}
