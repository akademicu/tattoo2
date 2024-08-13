package com.akademicu.tattooii;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.akademicu.tattooii.adapter.PhotoAdapter;
import com.akademicu.tattooii.model.Photo;
import com.akademicu.tattooii.view_model.ActivityViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;

    private final ArrayList<Photo> photoArrayList = new ArrayList<>();
    private PhotoAdapter photoAdapter;
    ActivityViewModel viewModel;
    private ProgressBar progressBar;
    private int curentPage = 1;
    private boolean isLoading = false;

    String val = BuildConfig.API_KEY;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        initApp();
        getPhotos("tattoo", 10, curentPage);

        recyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
                if (!isLoading){
                    if (linearLayoutManager != null && linearLayoutManager.findLastCompletelyVisibleItemPosition() == photoArrayList.size()-1){
                        curentPage++;
                        getPhotos("tattoo",80, curentPage);
                        isLoading = true;
                    }
                }
            }
        });
    }

    @SuppressLint("NotifyDataSetChanged")
    private void getPhotos(String query, int perPage, int page) {
        viewModel.getPexelsResponseLiveData(query,perPage,page).observe(this, pexelsResponse -> {
            if (pexelsResponse != null && pexelsResponse.getPhotos() != null && !pexelsResponse.getPhotos().isEmpty()){
                progressBar.setVisibility(View.GONE);
                List<Photo> photoList  =  pexelsResponse.getPhotos();
                photoArrayList.addAll(photoList);
                photoAdapter.notifyDataSetChanged();
                isLoading = false;

            }
        });
    }

    private void initApp() {
        recyclerView = findViewById(R.id.recyclerView);
        progressBar = findViewById(R.id.progressBar);
        linearLayoutManager = new LinearLayoutManager(MainActivity.this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);
        photoAdapter = new PhotoAdapter(photoArrayList, MainActivity.this);
        recyclerView.setAdapter(photoAdapter);
        viewModel = new ViewModelProvider(this).get(ActivityViewModel.class);
    }
}