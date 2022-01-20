package com.pt.fruit.em;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum Category {
	NULL("NULL","NULL","NULL"),
	FRUIT("과일","fruit","product"),
	VESETABLE("채소","vegetable","item")
	;
	
	private String title;
	private String path;
	private String endPoint;
	
	Category(String title, String path, String endPoint) {
		this.title = title;
		this.path = path;
		this.endPoint = endPoint;
	}
	
	public String getTitle() {
		return title;
	}
	public String getPath() {
		return path;
	}
	public String getEndPoint() {
		return endPoint;
	}
	
	public static List<String> getCategoryList() {
		
		List<String> result = new ArrayList<String>();
		for(int i = 0; i < Category.values().length; i++) {
			String title = Category.values()[i].getTitle();
			result.add(title);
		}
		
		return result;
	}
	
	public static Category titleToCategory(String title) {
		return Arrays.stream(Category.values())
				     .filter(category -> category.getTitle().equals(title))
				     .findAny()
				     .orElse(NULL);
	}
	
	
	
}
