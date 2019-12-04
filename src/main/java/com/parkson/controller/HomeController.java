package com.parkson.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
//@RequestMapping("/")
public class HomeController {
	  @GetMapping("/home")
	  public String index() {
	    return "index";
	  }
}
