package org.joedayz.corespringtest.web.controller;

import java.math.BigDecimal;
import java.util.List;

import org.apache.log4j.Logger;
import org.joedayz.corespringtest.model.Category;
import org.joedayz.corespringtest.model.Product;
import org.joedayz.corespringtest.service.ProductManager;
import org.joedayz.corespringtest.web.validator.ProductValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomNumberEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;

@Controller
public class ProductController {
	
	Logger logger = Logger.getLogger(ProductController.class);
	
	@Autowired
	private ProductManager productManager;
	
	@Autowired
	private ProductValidator productValidator;
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(BigDecimal.class, new CustomNumberEditor(BigDecimal.class, true));
	}
	
	@RequestMapping("/inicio.htm")
	public void inicio() {

	}

	@RequestMapping(value = "/productList.htm")
	public void productList(Model model, @ModelAttribute("product") Product product) {
		List<Product> products = this.productManager.getProductsList(product);
		model.addAttribute("products", products);
		model.addAttribute("product", product);
	}

	@RequestMapping(value = "/productView.htm", method = RequestMethod.GET)
	public void productView(@RequestParam(value="idProduct",required=false) Integer idProduct, Model model) {
		Product product;
		if (idProduct != null) {
			product = this.productManager.getProductById(idProduct);
		}else{
			product = new Product();
		}
		model.addAttribute("product", product);
	}
	
	@RequestMapping(value = "/productView.htm", method = RequestMethod.POST)
	public String saveProduct(@ModelAttribute("product")Product product,
			BindingResult result, SessionStatus status) {
		try {
			
			productValidator.validate(product, result);
			if(result.hasErrors()){
				return "productView";
			}
			this.productManager.saveProduct(product);
			return "redirect:/productList.htm";
		} catch (Exception e) {
			e.printStackTrace();
			return "productView";
		}
	}

	@RequestMapping(value = "/delete.htm", method = RequestMethod.GET)
	public String deleteProduct(@RequestParam(value="idProduct", required=false) Integer idProduct) {
		this.productManager.deleteProduct(idProduct);
		return "redirect:/productList.htm";
	}
	
	@ModelAttribute("categoriesTypes")
	public List<Category> categoryList() {
		List<Category> categoriesTypes = this.productManager.getCategoriesTypesList();
		return categoriesTypes;
	}
}
