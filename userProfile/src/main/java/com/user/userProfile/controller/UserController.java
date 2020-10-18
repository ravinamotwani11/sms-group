package com.user.userProfile.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.QueryParam;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.user.userProfile.entity.User;
import com.user.userProfile.repository.UserRepository;
import com.user.userProfile.service.UserService;
import com.user.userProfile.util.BaseResponse;
import com.user.userProfile.util.CommonConstants;

@RestController
public class UserController {

	@Autowired
	UserRepository userRepository;

	@Autowired
	UserService userService;

	@PostMapping("/insertJSON")
	public ResponseEntity<BaseResponse<User>> insertJSON(HttpServletRequest request) throws Exception {

		BaseResponse<User> br = new BaseResponse<>();
		ResponseEntity<BaseResponse<User>> userResponse = null;

		br = userService.insertJSON(request);

		userResponse = new ResponseEntity<BaseResponse<User>>(br, null, HttpStatus.OK);
		return userResponse;

	}

	@CrossOrigin(origins = "*")
	@GetMapping("/fetchCities")
	public ResponseEntity<BaseResponse<User>> getCityList(HttpServletRequest request, @QueryParam("id") Long id)
			throws CloneNotSupportedException {

		List<User> user = new ArrayList<>();
		User userData = new User();
		BaseResponse<User> br = new BaseResponse<>();
		ResponseEntity<BaseResponse<User>> userResponse = null;

		user = userService.getCityList(request);

		if (!user.isEmpty()) {
			br.setStatus(CommonConstants.SUCCESS);
			br.setReasonCode("200");
			br.setResponseListObject(user);
			br.setTotalRecords(String.valueOf(user.size()));

		} else {
			br.setStatus(CommonConstants.SUCCESS);
			br.setReasonText(CommonConstants.NO_RECORD_FOUND);

		}

		if (id != null) {
			userData = userRepository.findById((long) id);
			if (userData != null) {
				br.setStatus(CommonConstants.SUCCESS);
				br.setResponseObject(userData);
				br.setResponseListObject(null);
				br.setTotalRecords(CommonConstants.ONE);
			}
		}
		userResponse = new ResponseEntity<>(br, null, HttpStatus.OK);

		return userResponse;
	}

	@CrossOrigin(origins = "*")
	@PutMapping("/update")
	public ResponseEntity<BaseResponse<User>> updateRecord(HttpServletRequest request, @RequestBody User user)
			throws Exception {

		ResponseEntity<BaseResponse<User>> userResponse = null;

		BaseResponse<User> br = new BaseResponse<>();

			try {
				List<String> responseList = new ArrayList<>();
				String erroResponse = null;
				User userDetails = new User();

				// check id is null.
				if ((Long) user.getId() == null || user.getId() == 0) {

					erroResponse = CommonConstants.ID_NOT_NULL;
					br.setStatus(CommonConstants.FAIL);
					br.setReasonCode("202");
					br.setReasonText(CommonConstants.ID_NOT_NULL);
					responseList.add(erroResponse);
				}
				if (responseList.isEmpty()) {
					User userRecord;
					
					//service call to check whether id is present in the database or not
					userRecord = userService.updateRecord(request, user.getId());
					if (userRecord != null) {

						userDetails.setId(user.getId());

						if (user.getCity() != null) {
							userDetails.setCity(user.getCity());
						} else {
							userDetails.setCity(userRecord.getCity());
						}
						if (user.getColor() != null) {
							userDetails.setColor(user.getColor());
						} else {
							userDetails.setColor(userRecord.getColor());
						}
						if (user.getEnd_date() != null) {
							userDetails.setEnd_date(user.getEnd_date());
						} else {
							userDetails.setEnd_date(userRecord.getEnd_date());
						}
						if (user.getPrice() != null) {
							userDetails.setPrice(user.getPrice());
						} else {
							userDetails.setPrice(userRecord.getPrice());
						}
						if (user.getStart_date() != null) {
							userDetails.setStart_date(user.getStart_date());
						} else {
							userDetails.setStart_date(userRecord.getStart_date());
						}
						if (user.getStatus() != null) {
							userDetails.setStatus(user.getStatus());
						} else {
							userDetails.setStatus(userRecord.getStatus());
						}
						BeanUtils.copyProperties(userDetails, userRecord);
						userDetails = userRepository.save(userRecord);

						if (userDetails != null) {

							br.setStatus(CommonConstants.SUCCESS);
							br.setReasonCode("200");
							br.setReasonText(CommonConstants.DETAILS_UPDATED_SUCCESSFULLY);
						} else {
							br.setStatus(CommonConstants.FAIL);
							br.setReasonCode("202");
							br.setReasonText(CommonConstants.ERROR_IN_UPDATING);
						}
					} else {
						br.setStatus(CommonConstants.FAIL);
						br.setReasonCode("202");
						br.setReasonText(CommonConstants.PROVIDE_VALID_ID);
					}
				}
			}

			catch (Exception e) {

				br.setStatus(CommonConstants.FAIL);
				br.setReasonCode("202");
				e.printStackTrace();

			}
		
		userResponse = new ResponseEntity<BaseResponse<User>>(br, null, HttpStatus.OK);
		return userResponse;

	}

	@CrossOrigin(origins = "*")
	@DeleteMapping("/delete")
	public ResponseEntity<BaseResponse<User>> deleteRecord(HttpServletRequest request, HttpServletResponse response,
			@RequestBody User user) throws Exception {

		ResponseEntity<BaseResponse<User>> userResponse = null;
		BaseResponse<User> br = new BaseResponse<>();
		BaseResponse<User> br1 = new BaseResponse<>();

		try {

			// check id is null or empty
			if (user.getId() == 0) {
				br.setStatus(CommonConstants.FAIL);
				br.setReasonText(CommonConstants.ID_NOT_NULL);
				br.setReasonCode("202");

			} else {
				// call to serviseImpl to delete record by id
				br1 = userService.delete(request, (user.getId()));
				if (br1 != null) {

					br.setStatus(br1.getStatus());
					br.setReasonCode(br1.getReasonCode());
					br.setReasonText(br1.getReasonText());
				}
			}

		} catch (Exception e) {

			br.setStatus(CommonConstants.FAIL);
			br.setReasonCode("202");
			e.printStackTrace();

		}
		userResponse = new ResponseEntity<BaseResponse<User>>(br, null, HttpStatus.OK);
		return userResponse;
	}

	@CrossOrigin(origins = "*")
	@PostMapping("/add")
	public ResponseEntity<BaseResponse<User>> addRecord(HttpServletRequest request, HttpServletResponse response,
			@RequestBody List<User> user) throws Exception {

		ResponseEntity<BaseResponse<User>> userResponse = null;
		BaseResponse<User> br = new BaseResponse<>();
		BaseResponse<User> br1 = new BaseResponse<>();
//		User userData = new User();

		try {

			for (User userData : user) {
				// check id is empty or not
				if (userData.getId() != 0) {
					br.setStatus(CommonConstants.FAIL);
					br.setReasonText(CommonConstants.ID_NULL);
					br.setReasonCode("202");
				} else {
					// call to serviseImpl to add record
					br1 = userService.addRecord(request, userData);
					if (br1 != null) {

						br.setStatus(br1.getStatus());
						br.setReasonCode(br1.getReasonCode());
						br.setReasonText(br1.getReasonText());
					}
				}
			}

		} catch (Exception e) {

			br.setStatus(CommonConstants.FAIL);
			br.setReasonCode("202");
			e.printStackTrace();

		}
		userResponse = new ResponseEntity<BaseResponse<User>>(br, null, HttpStatus.OK);
		return userResponse;
	}
}