package com.pt.fruit.controller;

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
	

	//메인뷰로 이동
	@GetMapping("/main")
	public String main() {
		System.out.println("main 접속");
		return "main/main";
	}
	
	
	/**
	 * Category 리스트를 불러옵니다.
	 */
	@GetMapping("/getCategoryList") 
	@ResponseBody
	public List<String> getCategory(){
		System.out.println("getCategory 접속");
		return Category.getCategoryList().stream()
										 .filter(cate -> cate != null)
										 .collect(Collectors.toList());
	}
	
	
	/**
	 * 상품의 가격정보를 불러옵니다.
	 * @param category - 웹에서 선택한 카테고리
	 * @param name - 웹에서 선택한 상품명
	 * @return - 상품의 가격정보
	 */
	@GetMapping("/getPrice")
	@ResponseBody
	public Map<String, Object> getPrice(String category, String name) {
		System.out.println("getPrice 접속");
		Map<String, Object> result = new HashMap<>();
		
		// 카테고리체크
		if(Category.isNull(category)) {
			result.put("error","그런 카테고리는 없습니다");
			return result;
		}
		
		// 통신메서드 호출
		try {
			String price = ptApiUtil.getPrice(Category.titleToCategory(category), name);
			result.put("price",price);
		} catch (Exception e) {
			e.printStackTrace();
			result.put("error",e.getMessage());
		}
		return result;
	}
	
	/**
	 * 상품의 리스트를 불러옵니다.
	 * @param category - 웹에서 선택한 카테고리
	 * @return - 상품 리스트
	 */
	@GetMapping("/getProductList")
	@ResponseBody
	public Map<String, Object> getProductList(String category) {
		System.out.println("getProductList 접속");
		Map<String, Object> result = new HashMap<>();
		
		// 카테고리체크
		if(Category.isNull(category)) {
			result.put("error","그런 카테고리는 없습니다");
			return result;
		}
		
		// 통신메서드 호출
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
//		Category a = Category.FRUIT;
//		System.out.println(a == Category.FRUIT);
	}
}
