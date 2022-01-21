package com.pt.fruit.em;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum Category {
	NULL(null,null,null),
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
	
	/**
	 * @return Category의 value를 리스트로 반환
	 */
	public static List<String> getCategoryList() {
		
		List<String> result = new ArrayList<String>();
		for(int i = 0; i < Category.values().length; i++) {
			String title = Category.values()[i].getTitle();
			result.add(title);
		}
		return result;
	}
	
	/**
	 * title에 해당하는 Category enum 반환
	 * @param title - enum의 타이틀
	 * @return
	 */
	public static Category titleToCategory(String title) {
		return Arrays.stream(Category.values())
					 .filter(category -> category.getTitle() != null && category.getTitle().equals(title))
				     .findAny()
				     .orElse(NULL);
	}
	
	/**
	 * title에 해당하는 ENUM이 NULL인가?
	 * @param title - enum의 타이틀
	 * @return 맞으면 true 아니면 false
	 */
	public static boolean isNull(String title) {
		return titleToCategory(title) == Category.NULL;
	}
	
	
}
