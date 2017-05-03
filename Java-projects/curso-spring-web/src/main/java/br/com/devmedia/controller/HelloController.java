package br.com.devmedia.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/devmedia")
public class HelloController {

	@RequestMapping
	public String hello() {

		return "Index";
	}

}
