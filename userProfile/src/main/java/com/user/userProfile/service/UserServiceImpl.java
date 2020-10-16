package com.user.userProfile.service;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.user.userProfile.entity.User;
import com.user.userProfile.repository.UserRepository;
import com.user.userProfile.util.BaseResponse;
import com.user.userProfile.util.CommonConstants;

import io.jsonwebtoken.io.IOException;

@Service("userService")
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;

	@Override
	public List<User> getCityList(HttpServletRequest request) throws CloneNotSupportedException {
		List<User> users = null;

		users = userRepository.findAll();

		return users;
	}

	@Override
	public BaseResponse<User> insertJSON(HttpServletRequest request) throws CloneNotSupportedException {

		BaseResponse<User> br = new BaseResponse<>();

		JSONParser jsonParser = new JSONParser();
		try {
			// Parsing the contents of the JSON file

			JSONArray jsonArray = (JSONArray) jsonParser.parse(new FileReader("data.json"));

			for (Object o : jsonArray) {
				User user = new User();
				JSONObject record = (JSONObject) o;

				Long id = (Long) record.get("id");
				String city = (String) record.get("city");
				String start_date = (String) record.get("start_date");
				String end_date = (String) record.get("end_date");
				String price = (String) record.get("price");
				String status = (String) record.get("status");
				String color = (String) record.get("color");

				user.setId(id);
				user.setCity(city);
				user.setStart_date(start_date);
				user.setEnd_date(end_date);
				user.setPrice(price);
				user.setStatus(status);
				user.setColor(color);
				userRepository.save(user);
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (Exception e) {

			e.printStackTrace();
		}

		br.setStatus(CommonConstants.SUCCESS);
		br.setReasonCode("200");
		br.setReasonText(CommonConstants.INSERTED_SUCCESSFULLY);
		return br;
	}

	@Override
	public User updateRecord(HttpServletRequest request, long id) throws CloneNotSupportedException {

		User user;
		user = userRepository.findById(id);
		return user;
	}

	@Override
	public BaseResponse<User> delete(HttpServletRequest request, long id) throws Exception {
		User user = null;
		BaseResponse<User> br = new BaseResponse<>();
		user = userRepository.findById(id);
		if (user != null) {
			user = userRepository.deleteById(id);

			br.setStatus(CommonConstants.SUCCESS);
			br.setReasonCode("200");
			br.setReasonText(CommonConstants.DELETED_SUCCESSFULLY);

		} else {
			br.setStatus(CommonConstants.FAIL);
			br.setReasonCode("202");
			br.setReasonText(CommonConstants.PROVIDE_VALID_ID);
		}
		return br;
	}

	@Override
	public BaseResponse<User> addRecord(HttpServletRequest request, User user) throws Exception {

		User userData = new User();
		BaseResponse<User> br = new BaseResponse<>();

		try {

			userData.setId(user.getId());
			userData.setCity(user.getCity());
			userData.setColor(user.getColor());
			userData.setEnd_date(user.getEnd_date());
			userData.setPrice(user.getPrice());
			userData.setStart_date(user.getStart_date());
			userData.setStatus(user.getStatus());
			userRepository.save(userData);

			br.setStatus(CommonConstants.SUCCESS);
			br.setReasonCode("200");
			br.setReasonText(CommonConstants.Added_SUCCESSFULLY);

		} catch (Exception e) {

			e.printStackTrace();
			br.setStatus(CommonConstants.FAIL);
			br.setReasonCode("202");
			br.setReasonText(CommonConstants.ERROR_IN_ADDING);
		}
		return br;
	}

}