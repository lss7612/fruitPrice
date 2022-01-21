package com.pt.fruit.apiUtil.service;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;
import retrofit2.http.Query;

//문서주소
public interface PTApiService {

	/**
	 * 과일가게 토큰발급
	 * https://documenter.getpostman.com/view/12988282/TWDXoHAW#db76fb68-15cf-4f15-8caf-d6ebe2f78443
	 * @return 과일가게 토큰
	 */
	@GET("token")
	public Call<Map<String, String>> getFruitToken();
	
	/**
	 * 채소가게 토큰발급
	 * https://documenter.getpostman.com/view/12988282/TWDXoHAW#e99df327-a30f-44c5-8bc6-03302519d70b
	 * @return
	 */
	@GET("token")
	public Call<String> getVesetableToken();

	/**
	 * 가게 상품 가격 조회
	 * 채소 : https://documenter.getpostman.com/view/12988282/TWDXoHAW#e99df327-a30f-44c5-8bc6-03302519d70b
	 * 과일 : https://documenter.getpostman.com/view/12988282/TWDXoHAW#4d2cdf6b-ed5d-4df3-ac0a-97bd8dd8d1b0
	 * @param token 토큰
	 * @param endPoint API 엔드포인트
	 * @param name 상품이름
	 * @return 상품의 가격
	 */
	@GET("{endPoint}")
	public Call<Map<String, String>> getPrice(@Header("Authorization") String token, @Path("endPoint") String endPoint, @Query("name") String name);
	
	/**
	 * 가게 상품 목록 조회
	 * 채소 : https://documenter.getpostman.com/view/12988282/TWDXoHAW#c3e9fc0c-f03b-42ad-84b3-9fe3575ce9d3
	 * 과일 : https://documenter.getpostman.com/view/12988282/TWDXoHAW#3d9ae165-a04f-46c3-a215-afb9c59682aa
	 * @param token 토큰
	 * @param endPoint API 엔드포인트
	 * @return 상품 목록
	 */
	@GET("{endPoint}")
	public Call<List<String>> getProductList(@Header("Authorization") String token, @Path("endPoint") String endPoint);

}
