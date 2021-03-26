package com.bnu.jlh.application.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bnu.jlh.application.model.*;


public interface UserService {

	public user login(String username, String password);

	public int add(user use);

}
