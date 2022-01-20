package com.pt.fruit.controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pt.fruit.apiUtil.PTApiUtil;
import com.pt.fruit.em.Category;

@Controller
public class TaskController {
	
	@Autowired
	PTApiUtil ptApiUtil;
	

	@GetMapping("/main")
	public String main() {
		System.out.println("main 접속");
		return "main/main";
	}
	
	@GetMapping("/getCategoryList") 
	@ResponseBody
	public List<String> getCategory(){
		System.out.println("getCategory 접속");
		return Category.getCategoryList().stream()
										 .filter(cate -> !cate.equals("NULL"))
										 .collect(Collectors.toList());
	}
	
	@GetMapping("/getPrice")
	@ResponseBody
	public Map<String, Object> getPrice(String category, String name) {
		System.out.println("getPrice 접속");
		Map<String, Object> result = new HashMap<>();
		
		try {
			String price = ptApiUtil.getPrice(Category.titleToCategory(category), name);
			result.put("price",price);
		} catch (Exception e) {
			e.printStackTrace();
			result.put("error",e.getMessage());
		}
		
		return result;
		
	}
	
	@GetMapping("/getProductList")
	@ResponseBody
	public Map<String, Object> getProductList(String category) {
		System.out.println("getProductList 접속");
		Map<String, Object> result = new HashMap<>();
		try {
			List<String> productList = ptApiUtil.searchProductList(Category.titleToCategory(category));
			result.put("productList", productList);
		} catch (Exception e) {
			e.printStackTrace();
			result.put("error", e.getMessage());
		}

		return result;
		
	}
	
	public static void main(String[] args) {
//		System.out.println(Category.FRUIT.getPath());
//		Arrays.stream(Category.values())
//			  .forEach(item -> {
//				  System.out.println(item.name());
//			  });
		Category a = Category.FRUIT;
		System.out.println(a == Category.FRUIT);
	}
}
