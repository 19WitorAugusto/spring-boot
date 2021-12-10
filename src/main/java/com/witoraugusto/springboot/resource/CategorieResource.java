package com.witoraugusto.springboot.resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/categories")
public class CategorieResource {
	
	@RequestMapping(method = RequestMethod.GET)
	public String find() {
		return "REST teste";
	}

}
