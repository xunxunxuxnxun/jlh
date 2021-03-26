package com.bnu.jlh.application.dao;
import org.apache.ibatis.annotations.Param;
import com.bnu.jlh.application.model.*;
import org.apache.ibatis.annotations.Mapper;

import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Insert;
@Mapper
public interface UserLoginDao {
	@Select("SELECT * FROM user WHERE username = #{username} AND password = #{password}")
	@Results({
		@Result(property="userid",column="userid"),
		@Result(property="username",column="username"),
		@Result(property="password",column="password"),
		@Result(property="userlevel",column="userlevel"),
	})
	public user select(@Param("username") String username,String password);
	@Insert("insert into user(username,password,userlevel) values(#{username},#{password},#{userlevel})")
	@Results({
		@Result(property="userid",column="userid"),
		@Result(property="username",column="username"),
		@Result(property="password",column="password"),
		@Result(property="userlevel",column="userlevel"),
	})
	public int add(user use);

}
