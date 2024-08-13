package com.akademicu.tattooii.view_model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.akademicu.tattooii.model.PexelsResponse;
import com.akademicu.tattooii.repository.PhotoRepository;

public class ActivityViewModel extends AndroidViewModel {
   // LiveData<PexelsResponse> pexelsResponseLiveData;

    PhotoRepository photoRepository;

    public ActivityViewModel(@NonNull Application application) {
        super(application);
        this.photoRepository = new PhotoRepository(application);
    }


    public LiveData<PexelsResponse> getPexelsResponseLiveData(String query, int perPage, int currentPage) {
        return photoRepository.getMutableDataFromPexels(query,perPage,currentPage);
    }
}
