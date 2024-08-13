package com.akademicu.tattooii.model;

import java.util.List;

public class PexelsResponse {

    private int total_results;
    private List<Photo> photos;

    public int getTotal_results() {
        return total_results;
    }

    public List<Photo> getPhotos() {
        return photos;
    }
}
