package com.example.wallpaperapp;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.WallpaperManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.io.IOException;
import java.util.Arrays;

public class SetWallpaper extends AppCompatActivity {

    Intent intent;
    ImageView imageView;
    android.widget.Button set;
    String[] options = new String[]{
            "Home Screen", "Lock Screen", "Both"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_wallpaper);
        getSupportActionBar().hide();
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        final WallpaperManager wallpaperManager = WallpaperManager.getInstance(getApplicationContext());

        set = findViewById(R.id.set);
        imageView = findViewById(R.id.finalImage);
        intent = getIntent();

        String url = intent.getStringExtra("image");
        Glide.with(getApplicationContext()).load(url).into(imageView);

        set.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bitmap bitmap = ((BitmapDrawable)imageView.getDrawable()).getBitmap();
                final AlertDialog.Builder builder = new AlertDialog.Builder(SetWallpaper.this);
                builder.setTitle("Set wallpaper");
                builder.setItems(options, new DialogInterface.OnClickListener() {
                    @RequiresApi(api = Build.VERSION_CODES.N)
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String selected = Arrays.asList(options).get(which);
                        if (selected.equals(options[0])){
                            try {
                                wallpaperManager.setBitmap(bitmap, null, false, WallpaperManager.FLAG_SYSTEM);
                                Toast.makeText(getApplicationContext(), "SET SUCCESSFULLY", Toast.LENGTH_SHORT).show();
                                dialog.dismiss();
                            } catch (IOException e){
                                e.printStackTrace();
                            }
                        } else if (selected.equals(options[1])){
                            try {
                                wallpaperManager.setBitmap(bitmap, null, false, WallpaperManager.FLAG_LOCK);
                                Toast.makeText(getApplicationContext(), "SET SUCCESSFULLY", Toast.LENGTH_SHORT).show();
                                dialog.dismiss();
                            } catch (IOException e){
                                e.printStackTrace();
                            }
                        } else {
                            try {
                                wallpaperManager.setBitmap(bitmap, null, false, WallpaperManager.FLAG_SYSTEM
                                | WallpaperManager.FLAG_LOCK);
                                Toast.makeText(getApplicationContext(), "SET SUCCESSFULLY", Toast.LENGTH_SHORT).show();
                                dialog.dismiss();
                            } catch (IOException e){
                                e.printStackTrace();
                            }
                        }
                    }
                });
                builder.create();
                builder.show();
            }
        });
    }
}