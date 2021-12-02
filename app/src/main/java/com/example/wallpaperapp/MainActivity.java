package com.example.wallpaperapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

//get data from api and pass it to adapter
public class MainActivity extends AppCompatActivity {

    private ArrayList<ImageModel> modelArrayList;
    private RecyclerView recyclerView;
    Adapter adapter;
    CardView mNature, mCar, mBus, mTrain, mTrending;
    EditText editText;
    ImageButton search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        recyclerView = findViewById(R.id.recyclerView);
        mNature = findViewById(R.id.nature);
        mCar = findViewById(R.id.car);
        mTrain = findViewById(R.id.train);
        mBus = findViewById(R.id.bus);
        mTrending = findViewById(R.id.trending);
        editText = findViewById(R.id.searchET);
        search = findViewById(R.id.searchBtn);

        modelArrayList = new ArrayList<>();
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        recyclerView.setHasFixedSize(true);
        adapter = new Adapter(this, modelArrayList);
        recyclerView.setAdapter(adapter);
        findPhotos();

        mNature.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String query = "nature";
                getSearchImage(query);
            }
        });

        mCar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String query = "car";
                getSearchImage(query);
            }
        });

        mTrain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String query = "train";
                getSearchImage(query);
            }
        });

        mBus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String query = "bus";
                getSearchImage(query);
            }
        });

        mTrending.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                findPhotos();
            }
        });

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String query = editText.getText().toString().trim().toLowerCase();
                if (query.isEmpty()){
                    Toast.makeText(getApplicationContext(), "Enter something", Toast.LENGTH_SHORT).show();
                } else {
                    getSearchImage(query);
                }
            }
        });
    }

    private void getSearchImage(String query) {
        ApiUtils.getApiInterface().getSearchImage(query, 1, 80).enqueue(new Callback<SearchModel>() {
            @Override
            public void onResponse(Call<SearchModel> call, Response<SearchModel> response) {
                modelArrayList.clear();
                if (response.isSuccessful()){
                    modelArrayList.addAll(response.body().getPhotos());
                    adapter.notifyDataSetChanged();
                }
                else {
                    Toast.makeText(getApplicationContext(), "Not able to get photos", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<SearchModel> call, Throwable t) {
            }
        });
    }

    private void findPhotos() {
        ApiUtils.getApiInterface().getImage(1, 80).enqueue(new Callback<SearchModel>() {
            @Override
            public void onResponse(Call<SearchModel> call, Response<SearchModel> response) {
                modelArrayList.clear();
                if (response.isSuccessful()){
                    modelArrayList.addAll(response.body().getPhotos());
                    adapter.notifyDataSetChanged();
                }
                else {
                    Toast.makeText(getApplicationContext(), "Not able to get photos", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<SearchModel> call, Throwable t) {

            }
        });
    }
}