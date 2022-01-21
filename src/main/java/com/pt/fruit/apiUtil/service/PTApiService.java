package com.pt.fruit.apiUtil.service;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface PTApiService {

	//토큰발급
	@GET("token")
	public Call<Map<String, String>> getToken();
	
	@GET("token")
	public Call<String> getVesetableToken();

	//가격조회
	@GET("{endPoint}")
	public Call<Map<String, String>> getPrice(@Header("Authorization") String token, @Path("endPoint") String endPoint, @Query("name") String name);
	
	//목록조회
	@GET("{endPoint}")
	public Call<List<String>> getProductList(@Header("Authorization") String token, @Path("endPoint") String endPoint);

}
