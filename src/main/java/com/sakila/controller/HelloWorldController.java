package com.sakila.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/helloworld")
public class HelloWorldController {
	@RequestMapping("/name/{name}")
	public @ResponseBody String namePathVariable(@PathVariable String name) {
		return "Hello " + name;
	}
	
	@RequestMapping("/name")
	public @ResponseBody String nameRequestParameter(@RequestParam(value = "name", required = false) String name) {
		return "Hello " + ( name == null || name.length() == 0 ? "World" : name) ;
	}
	
	@RequestMapping("/name/")
	public @ResponseBody String nameRequestRequestBody(@RequestBody String name) {
		return "Hello " + name;
	}
}
