package com.reciproci.fridaytask;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.reciproci.fridaytask.adapter.CallBack;
import com.reciproci.fridaytask.adapter.RecyclerViewAdapter;
import com.reciproci.fridaytask.models.PotosModel;
import com.reciproci.fridaytask.network.RetroGet;
import com.reciproci.fridaytask.network.RetrofitInstance;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements CallBack {
    private static final String TAG = "MainActivity";
    ArrayList<String> imageList = new ArrayList<>();
    RecyclerView recyclerView;
    RecyclerViewAdapter recyclerViewAdapter;
    List<PotosModel> resList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recyclerView);

        RetroGet retroGet = RetrofitInstance.getRetrofit().create(RetroGet.class);
        Call<List<PotosModel>> call = retroGet.getUrl();
        call.enqueue(new Callback<List<PotosModel>>() {
            @Override
            public void onResponse(Call<List<PotosModel>> call, Response<List<PotosModel>> response) {
                Log.d(TAG, "onResponse: " + response.body());
                resList = response.body();
                for (int i = 0; i < response.body().size(); i++) {
                    imageList.add(response.body().get(i).getThumbnailUrl());
                }
                initRec();
            }

            @Override
            public void onFailure(Call<List<PotosModel>> call, Throwable t) {
                Log.d(TAG, "onResponse: " + t.getMessage());
            }
        });

    }

    public void initRec() {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(), 3);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerViewAdapter = new RecyclerViewAdapter(this, imageList);
        recyclerView.setAdapter(recyclerViewAdapter);

    }

    @Override
    public void callback(int pos) {
        resList.get(pos).getUrl();
    }
}
