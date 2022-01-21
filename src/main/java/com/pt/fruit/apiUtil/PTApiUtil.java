package com.pt.fruit.apiUtil;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.pt.fruit.apiUtil.service.PTApiService;
import com.pt.fruit.em.Category;
import com.pt.fruit.util.NullOnEmptyConverterFactory;

import okhttp3.OkHttpClient;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

@Component
public class PTApiUtil {
	
	private OkHttpClient okHttpClient = new OkHttpClient.Builder()
			.readTimeout(90, TimeUnit.SECONDS)
			.build();
	
	private static final String tailOfApiPath = ".a암호p암호i.p암호o암호s암호t암호y암호p암호e.n암호e암호t/";
	
	/**
	 * BaseUrl 생성 및 retrofit을 이용한 API SERVICE 객체 생성
	 * @param path : 우측에 예시 {path}에 들어갈 string http://{path}.{나머지주소}
	 */
	public PTApiService makeGsonRestService(String path) {
		String baseUrl = "http://"+path+tailOfApiPath.replaceAll("암호", "");
		
		return new Retrofit.Builder()
						   .baseUrl(baseUrl)
						   .client(okHttpClient)
						   .addConverterFactory(GsonConverterFactory.create())
						   .build()
						   .create(PTApiService.class);
	}
	
	private PTApiService makeStringRestService(String path) {
		String baseUrl = "http://"+path+tailOfApiPath.replaceAll("암호", "");
		return new Retrofit.Builder()
						   .baseUrl(baseUrl)
						   .client(okHttpClient)
						   .addConverterFactory(ScalarsConverterFactory.create())
						   .build()
						   .create(PTApiService.class);
	}
	
	/**
	 * 토큰 발급
	 * @param gsonRest : Retrofit을 이용해 생성한 서비스 객체
	 */
	private String getToken(PTApiService gsonRest, Category category) throws Exception{
		
		
		switch (category) {
		case FRUIT:
			Response<Map<String, String>> responseFruit = gsonRest.getToken().execute();
			if(responseFruit.isSuccessful()) return responseFruit.body().get("accessToken");
			else throw new Exception(category + "토큰 발급 실패");
		case VESETABLE:
			PTApiService stringRest = makeStringRestService(category.getPath());
			Response<String> responseVes = stringRest.getVesetableToken().execute();
			if(responseVes.isSuccessful()) return responseVes.headers().get("Set-Cookie").split(";")[0].split("=")[1];
		default:
			throw new Exception("Category에 포함되지 않은 category입니다 : " + category);
		}
		
	}
	
	

	/**
	 * 가격 조회
	 * @param category - 과일 채소 등 검색할 카테고리
	 * @param name - 카테고리에서 검색할 요소의 이름
	 */
	public String getPrice(Category category, String name) throws Exception{
		
		String price = null;
		
//		boolean isJsonResponse = category == Category.FRUIT ? true : false;
		PTApiService ptRest = makeGsonRestService(category.getPath());
		
		String token = getToken(ptRest, category);
		
		Response<Map<String, String>> response = ptRest.getPrice(token, category.getEndPoint(), name).execute();
		System.out.println("response" + response);
		if(response.isSuccessful()) {
			System.out.println("response.body()" + response.body());
			price = response.body().get("price");
		} else {
			throw new Exception("과일 가격 조회 실패");
		}
		
		return price;
		
	}
	
	/**
	 * Category 내부 모든 상품 조회
	 * @param category : 과일 채소 등 검색할 카테고리
	 */
	public List<String> searchProductList(Category category) throws Exception {
		
		PTApiService ptRest = makeGsonRestService(category.getPath());
		String token = getToken(ptRest, category);
		Response<List<String>> response = ptRest.getProductList(token, category.getEndPoint()).execute();
		System.out.println("response : " + response);
		
		if(response.isSuccessful()) {
			System.out.println("[searchProductList] response.body() : " + response.body());
			return response.body();
		} else {
			throw new Exception(category.getTitle() + " 내부 목록 조회 실패");
		}
		
	}
	
	
	
	
}
