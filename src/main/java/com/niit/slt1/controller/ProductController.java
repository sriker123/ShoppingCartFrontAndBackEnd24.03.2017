package com.niit.slt1.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.niit.shoppingcart.dao.ProductDAO;
import com.niit.shoppingcart.domain.Product;

@Controller
public class ProductController {
	@Autowired
	private ProductDAO productDAO;
	@Autowired
	private Product product;

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	// add Product
	// method 1 for add Product
	@RequestMapping("/manage_product_create")
	public ModelAndView createProductForm() {
		System.out.println("createProductForm called***********");
		ModelAndView mv = new ModelAndView("createProductForm");// there is a
																// jsp file
																// created just
																// to create the
																// product by
																// the admin
		mv.addObject("createProductObj", product);
		return mv;

	}

	// add Product
	// method 2 for add Product
	@RequestMapping(value = "/manage_product_create", method = RequestMethod.POST)
	public ModelAndView createProduct(@ModelAttribute(value = "createProductObj") Product product) {
		System.out.println("createProduct called****");
		ModelAndView mv = new ModelAndView("Product");
		if (productDAO.save(product)) {
			mv.addObject("message", "Successfully created the product");
			List<Product> productList = productDAO.list();
			mv.addObject("productList", productList);
			mv.addObject("product", product);

		} else {
			mv.addObject("message", "Not able to create Product.Please contact Administrator");

		}
		mv.addObject("isUserClickedProducts", "true");
		return mv;

	}

	// delete Product
	@RequestMapping(value = "/manage_product_delete/{id}", method = RequestMethod.GET)
	public ModelAndView deleteProduct(@PathVariable(value = "id") String id) {
		System.out.println("deleteProduct called****");
		ModelAndView mv = new ModelAndView("AdminHome");
		Product product = (Product) productDAO.getProductById(id);
		if (productDAO.delete(product)) {
			mv.addObject("messsage", "Successfully deleted the product");
			List<Product> productList = productDAO.list();
			mv.addObject("productList", productList);
			mv.addObject("product", product);
		} else {
			mv.addObject("messsage", "Not able to delete the  product,so please contact administrator");
		}

		// model.addAttribute("productList", productDAO.list());
		// mv.addObject("isUserClickedProducts","true");
		return mv;

	}

	// edit Product
	// method 1 for edit Product
	@RequestMapping(value = "/manage_product_edit/{id}", method = RequestMethod.GET)
	public ModelAndView getEditProductForm(@PathVariable(value = "id") String id) {
		System.out.println("getEditProductForm called******");

		Product product = (Product) this.productDAO.getProductById(id);
		ModelAndView mv = new ModelAndView("editProductForm");
		// return new ModelAndView("editProductForm", "editProductObj",
		// product);
		mv.addObject("editProductObj", "product");
		mv.addObject("product", product);
		return mv;
	}

	// edit Product
	// method 2 for edit Product
	@RequestMapping(value = "/manage_product_edit", method = RequestMethod.POST)
	public ModelAndView editProduct(@ModelAttribute(value = "editProductObj") Product product, Model model) {
		System.out.println("editProduct  called****");

		// this.productDAO.Update(product);
		ModelAndView mv = new ModelAndView("/Admin/Product");
		if (productDAO.Update(product)) {
			mv.addObject("message", "Updated Succesfully");
			List<Product> productList = productDAO.list();
			mv.addObject("productList", productList);
			mv.addObject("product", product);
		}
		model.addAttribute("productList", productDAO.list());
		return mv;

	}
}
