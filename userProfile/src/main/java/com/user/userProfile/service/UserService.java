package com.user.userProfile.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.user.userProfile.entity.User;
import com.user.userProfile.util.BaseResponse;

@Service
public interface UserService{

	BaseResponse<User> insertJSON(HttpServletRequest request) throws CloneNotSupportedException;

	User updateRecord(HttpServletRequest request, long id) throws CloneNotSupportedException;

	BaseResponse<User> delete(HttpServletRequest request, long id) throws Exception;

	BaseResponse<User> addRecord(HttpServletRequest request, User user) throws Exception;

	List<User> getCityList(HttpServletRequest request) throws CloneNotSupportedException;

}