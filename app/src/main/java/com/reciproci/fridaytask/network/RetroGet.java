package com.reciproci.fridaytask.network;

import com.reciproci.fridaytask.models.PotosModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface RetroGet {
    @GET("/photos")
    Call<List<PotosModel>> getUrl();
}
