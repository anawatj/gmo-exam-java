package com.gmo.exam.controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gmo.exam.models.UserModel;
import com.gmo.exam.services.UserService;

@Controller
public class UserController {
	@Autowired
	private UserService userService;
	//@RequestMapping(method=RequestMethod.GET,path="/api/usergroup")
	@GetMapping(value = "/api/usergroup")
	public @ResponseBody Map<String, Object> getAllUserGroup() throws Exception{
		return userService.getAllUserGroup();
	}
	//@RequestMapping(method=RequestMethod.POST,path="/api/register")
	@PostMapping(value = "/api/register")
	public @ResponseBody Map<String,Object> register(@RequestBody UserModel model) throws Exception {
		try {
			return userService.register(model);
		}catch(Exception ex) {
			Map<String,Object> map = new HashMap<>();
			map.put("status",HttpStatus.INTERNAL_SERVER_ERROR);
			map.put("error",ex.getMessage());
			return map;
		}
		
	}
}
