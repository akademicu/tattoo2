package com.akademicu.tattooii.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.akademicu.tattooii.model.PexelsResponse;
import com.akademicu.tattooii.model.Photo;
import com.akademicu.tattooii.retrofit.ApiRequest;
import com.akademicu.tattooii.retrofit.RetrofitRequest;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PhotoRepository {
    Application application;
    private MutableLiveData<PexelsResponse> data = new MutableLiveData<>();


    public PhotoRepository(Application application) {
        this.application = application;
    }

    public MutableLiveData<PexelsResponse> getMutableDataFromPexels(String query, int perPage, int page){
        ApiRequest apiRequest = RetrofitRequest.getRetrofitInstance();
        Call<PexelsResponse> call = apiRequest.searchPhoto(query,perPage,page);
        call.enqueue(new Callback<PexelsResponse>() {
            @Override
            public void onResponse(Call<PexelsResponse> call, Response<PexelsResponse> response) {
                if (response.isSuccessful() && response.body() != null){
                    data.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<PexelsResponse> call, Throwable t) {
                data.setValue(null);

            }
        });
        return data;

//        apiRequest.searchPhoto(query,perPage, page)
//                .enqueue(new Callback<PexelsResponse>() {
//                    @Override
//                    public void onResponse(Call<PexelsResponse> call, Response<PexelsResponse> response) {
//                        if (response.isSuccessful() && response.body() != null){
//                            data.setValue(response.body());
//                        }
//                    }
//
//                    @Override
//                    public void onFailure(Call<PexelsResponse> call, Throwable t) {
//                        data.setValue(null);
//                    }
//                });
//        return data;
    }


}
