package com.akademicu.tattooii.retrofit;


import com.akademicu.tattooii.BuildConfig;
import com.akademicu.tattooii.model.PexelsResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface ApiRequest {



    @Headers(BuildConfig.API_KEY)
    @GET("search")
    Call<PexelsResponse> searchPhoto(
            @Query("query") String query,
            @Query("per_page") int perPage,
            @Query("page") int page);
}
