package org.joedayz.corespringtest.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class ProductController {

	
	@RequestMapping("/inicio.htm")
	public void inicio() {
		System.out.println("Estamos en inicio.htm");
	}
}
