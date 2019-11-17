package com.reciproci.fridaytask;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class DisplayImage extends AppCompatActivity {
    private static final String TAG = "DisplayImage";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_image);
        IncomingIntent();


    }
    public void IncomingIntent(){
        Log.d(TAG, "IncomingIntent:checking for intents ");
        if(getIntent().hasExtra("KEY")){
            String imgUrl = getIntent().getStringExtra("KEY");
            SetImage(imgUrl);
        }
    }

    private void SetImage(String imgUrl){
        Log.d(TAG, "SetImage: setImage");
        ImageView imageView = findViewById(R.id.BigImage);
        Glide.with(this)
                .load(imgUrl)
                .into(imageView);
    }
}
